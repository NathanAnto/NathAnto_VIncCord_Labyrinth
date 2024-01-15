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

  /**
   * Generate all the possible passages
   * for the Kruskal algorithm
   */
  private def createPassages(): Unit = {
    // Get all passages and cell neighbours
    for (cell <- cells; n <- cell.neighbours) {
      connections += Set(cell)
      cell.findNeighbours(this)

      if ((!passages.contains(getPassage(cell, n)) || !passages.contains(getPassage(n, cell))) && n != null) {
        passages += new Passage(cell, n)
      }
    }
  }

  private def getPassage(startCell: Cell, endCell: Cell): Passage = {
    passages.foreach(p => {
      if (p.cells.contains(startCell) && p.cells.contains(endCell))
        return p
    })
    null
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
}
