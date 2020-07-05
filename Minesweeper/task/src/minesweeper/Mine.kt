package minesweeper

class Mine(x: Int, y: Int) : Cell(x, y) {

    override fun setMineCount() {
    }

    override fun checkBeforeMark(): Boolean {
        return true
    }

    override fun explore() {
        throw MineExplodedException()
    }


    override fun toString(): String {
        return if (getMark()) return "*" else "."
    }
}