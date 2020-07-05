package minesweeper

import java.lang.RuntimeException

class MineExplodedException: RuntimeException(){
    init {
        println("You stepped on a mine and failed!")
    }
}