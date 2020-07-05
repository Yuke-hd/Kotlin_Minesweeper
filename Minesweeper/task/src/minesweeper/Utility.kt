package minesweeper

class Utility {
    fun coordinateToIndex(x: Int, y: Int): Int {
        return x + 9 * y
    }
}