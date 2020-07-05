package minesweeper


abstract class Cell(private val x: Int, private val y: Int) {
    private var marked = false
    private var explored = false
    fun getX() = x
    fun getY() = y
    abstract fun setMineCount()
    abstract fun checkBeforeMark(): Boolean
    abstract fun explore()


    fun setExplored() {
        this.explored = true
    }

    fun isExplored(): Boolean {
        return explored
    }

    fun getMark() = marked

    fun mark() {
        marked = true
    }

    fun unmark() {
        marked = false
    }
}