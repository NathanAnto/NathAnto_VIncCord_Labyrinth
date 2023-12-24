package general

import scala.annotation.unused
import scala.util.Random

class Maze {
  private var dimensions: Int = 5
  var cells: Set[Cell] = Set()
  var passages: Set[Passage] = Set()
  var connections: Set[Set[Cell]] = Set()

  def getDimensions = dimensions

  def create(d: Int) {
    dimensions = d

    // Get all cells
    for (y <- 0 until dimensions; x <- 0 until dimensions) {
      val c = new Cell(x, y)
      cells += c
      connections += Set(c)
    }

    // Get all passages and cell neighbours
    for (cell <- cells; n <- cell.neighbours) {
      cell.getNeighbours(this)
      if ((!passages.contains(Passage(cell, n)) || !passages.contains(Passage(n, cell))) && n != null) {
        passages += Passage(cell, n)
      }
    }
  }

  private def isInDimensions(x:Int, y:Int): Boolean =
    x < dimensions && x >= 0 &&
    y < dimensions && y >= 0

  def getCell(x: Int, y: Int): Cell = {
    if (isInDimensions(x, y)) {
      return cells.find(c => c.x == x && c.y == y).get
    }
    null
  }
  @unused
  def getRandomCell: Cell = {
    val num = Random.nextInt(cells.size)
    cells.iterator.drop(num).next
  }

  def getRandomPassage: Passage = {
    val num = Random.nextInt(passages.size)
//    val p = passages.iterator.drop(num).next
    val p = passages.toIndexedSeq(num)
    passages -= p
    p
  }
}
