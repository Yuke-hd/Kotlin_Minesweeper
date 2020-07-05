package minesweeper

object GameData {
    val matrix = ArrayList<Cell>()
    val mineArray = ArrayList<Mine>()

    fun coordinateToIndex(x: Int, y: Int): Int {
        return x + 9 * y
    }
}

