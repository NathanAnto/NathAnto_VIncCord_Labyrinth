package kruskal

import general.{Cell, Passage}

import scala.util.Random

class KruskalMaze(override val dimensions: Int) extends general.Maze {
  private var passages: Set[Passage] = Set()
  var connections: Set[Set[Cell]] = Set()
  override def create(): Unit = {
    super.createCells()
    createPassages()
  }

  private def createPassages(): Unit = {
    // Get all passages and cell neighbours
    for (cell <- cells; n <- cell.neighbours) {
      connections += Set(cell)
      cell.getNeighbours(this)
      if ((!passages.contains(Passage(cell, n)) || !passages.contains(Passage(n, cell))) && n != null) {
        passages += Passage(cell, n)
      }
    }
  }

  /**
   * For Kruskal, return a
   * [[Passage]] not yet visited
   * @return Random [[Passage]]
   */
  def getRandomPassage: Passage = {
    if(passages.size <= 1) return passages.head
    val num = Random.nextInt(passages.size)
    val p = passages.toIndexedSeq(num)
    passages -= p
    p
  }

  def removePassage(cell1: Cell, cell2: Cell): Unit = {
    passages -= Passage(cell1, cell2)
    passages -= Passage(cell2, cell1)
  }
}
