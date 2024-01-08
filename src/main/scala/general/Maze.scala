package general

import scala.annotation.unused
import scala.util.Random

class Maze {

  protected var dimensions: Int = 5
  protected var cells: Set[Cell] = Set()
  private var usablePassages: Set[Passage] = Set()
  def getDimensions(): Int = dimensions

  def create(d: Int): Unit = {
    dimensions = d
    createCells()
  }

  protected def createCells(): Unit = {
    // Get all cells
    for (y <- 0 until dimensions; x <- 0 until dimensions) {
      val c = new Cell(x, y)
      cells += c
    }
    for(c <- cells) c.getNeighbours(this)
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

  def addUsedPassage(passage: Passage): Unit = {
    usablePassages += passage
  }
}
