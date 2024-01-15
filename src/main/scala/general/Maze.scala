package general

import scala.annotation.unused
import scala.util.Random

class Maze(val dimensions: Int = 5) {

  protected var cells: Set[Cell] = Set()
  private var usablePassages: Set[Passage] = Set()

  /**
   * Create the cells for the maze
   */
  def create(): Unit = {
    createCells()
  }

  protected def createCells(): Unit = {
    // Get all cells
    for (y <- 0 until dimensions; x <- 0 until dimensions) {
      val c = new Cell(x, y)
      cells += c
    }
    for(c <- cells) c.findNeighbours(this)
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

  def getUsablePassages: Set[Passage] = usablePassages

  /**
   * Adds a passage to a set to keep track of possible
   * moves. (Used for Game mode)
   * @param passage The usable passage
   */
  def addUsablePassage(startCell: Cell, endCell: Cell): Unit = {
    if(getUsablePassage(startCell, endCell) == null)
      usablePassages += new Passage(startCell, endCell)
  }

  /**
   * Get a usable [[Passage]] by checking if both
   * cells are in the same usable passage
   * @param startCell [[Cell]]
   * @param endCell  [[Cell]]
   * @return Usable [[Passage]]
   */
  def getUsablePassage(startCell: Cell, endCell: Cell): Passage = {
    usablePassages.foreach(p => {
      if(p.cells.contains(startCell) && p.cells.contains(endCell))
        return p
    })
    null
  }
}
