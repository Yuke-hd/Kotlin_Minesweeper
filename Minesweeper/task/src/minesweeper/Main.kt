package minesweeper

import kotlin.random.Random
import minesweeper.GameData.coordinateToIndex
import minesweeper.GameData.matrix
import minesweeper.GameData.mineArray

val scanner = java.util.Scanner(System.`in`)
fun main() {
    print("How many mines do you want on the field? > ")
    var mineCount: Int = scanner.nextInt()
    println()
    val immutableMineCount = mineCount
    var flags = 0

    // initialize grid
    for (y in 0..8) {
        for (x in 0..8)
            matrix.add(Field(x, y))
    }

    // place mines
    while (mineCount > 0) {
        val x: Int = Random.nextInt(0, 9)
        val y: Int = Random.nextInt(0, 9)
        val i = coordinateToIndex(x, y)
        if (matrix[i] !is Mine) {
            matrix[i] = Mine(x, y) // replace safe cell with mine
            mineArray.add(matrix[i] as Mine) // add mine to mineArray
            mineCount--
        }
    }

    // draw first frame
    println(" │123456789│")
    println("—│—————————│")
    for (y in 0..8) {
        print("${y + 1}│")
        for (x in 0..8) {
            val i = coordinateToIndex(x, y)
            //scanMine(matrix[i])
            print(matrix[i])
        }
        println("|")
    }
    println("—│—————————│")
    // =================

    // main game logic
    loop@ while (hasMine() || flags > immutableMineCount) {
        flags = 0
        print("Set/unset mines marks or claim a cell as free: > ")
        val cx = scanner.nextInt() - 1
        val cy = scanner.nextInt() - 1
        val command = scanner.next()

        val i = coordinateToIndex(cx, cy)
        when (command) {
            "free" -> {
                try {
                    matrix[i].explore()
                } catch (e: MineExplodedException) {
                    return
                }
            }
            "mine" -> {
                if (matrix[i].getMark()) // if the cell that user selected is marked
                    matrix[i].unmark() // then remove the flag
                else if (matrix[i].checkBeforeMark()) { // if the cell that user selected does not contain a number
                    matrix[i].mark() // then mark the cell
                } else {
                    println("There is a number here!") // else do nothing and tell user to try again
                    continue@loop
                }
            }
        }


        // draw frame starts
        println(" │123456789│")
        println("—│—————————│")
        for (y in 0..8) {
            print("${y + 1}│")
            for (x in 0..8) {
                val i = coordinateToIndex(x, y)
                if (matrix[i].getMark()) flags++
                print(matrix[i])
            }
            println("|")
        }
        println("—│—————————│")
        // draw frame ends
    }
    print("Congratulations! You found all the mines!")
}


fun hasMine(): Boolean {
    mineArray.forEach {
        if (!it.getMark()) return true
    }
    return false
}

fun scanMine(cell: Cell) {
    val cellx = cell.getX()
    val celly = cell.getY()
    for (y in -1..1) {
        for (x in -1..1) {
            if ((cellx + x) in 0..8)
                try {
                    val i = coordinateToIndex((cellx + x), (celly + y))
                    if (matrix[i] is Mine) cell.setMineCount()
                } catch (e: IndexOutOfBoundsException) {

                }
        }
    }
}