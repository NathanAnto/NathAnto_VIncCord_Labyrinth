package Prims

import general.Cell

import scala.util.Random

class PrimsMaze(override val dimensions: Int) extends general.Maze {

  var frontiers: Set[Cell] = Set()
  override def create(): Unit = {
    super.createCells()
  }

  /**
   * Used to get a frontier of the visited
   * cells, then removes it from the [[Set]]
   * @return a random frontier and removes it
   */
  def getRandomFrontier: Cell = {
    if(frontiers.size <= 0) return null

    val num = Random.nextInt(frontiers.size)
    var f = frontiers.toIndexedSeq(num)
    frontiers -= f
    f
  }
}
