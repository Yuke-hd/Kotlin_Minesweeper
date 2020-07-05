package minesweeper

import minesweeper.GameData.coordinateToIndex
import minesweeper.GameData.matrix
import java.lang.IndexOutOfBoundsException

class Field(x: Int, y: Int) : Cell(x, y) {
    private var mineCount = 0
    fun setMineCount(mineCount: Int) {
        this.mineCount = mineCount
    }

    private fun getMineCount(): Int {
        return mineCount
    }

    override fun setMineCount() {
        this.mineCount++
    }

    override fun checkBeforeMark(): Boolean {
        return (mineCount == 0 && !isExplored())
    }

    override fun explore() {
        if (isExplored()) return

        val cellx = this.getX()
        val celly = this.getY()

        for (y in -1..1) {
            for (x in -1..1) {
                if ((cellx + x) in 0..8) {
                    val i = coordinateToIndex((cellx + x), (celly + y))
                    try {
                        if (matrix[i] is Mine) {
                            this.setMineCount()
                        }
                    } catch (e: IndexOutOfBoundsException) {
                    }

                }
            }
        }
        setExplored()
        if (this.getMineCount() > 0) {
            return
        }
        for (y in -1..1) {
            for (x in -1..1) {
                if ((cellx + x) in 0..8) {
                    val i = coordinateToIndex((cellx + x), (celly + y))
                    try {
                        matrix[i].explore()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return if (mineCount == 0) {
            when {
                isExplored() -> "/"
                getMark() -> "*"
                else -> "."
            }
        } else mineCount.toString()
    }

}