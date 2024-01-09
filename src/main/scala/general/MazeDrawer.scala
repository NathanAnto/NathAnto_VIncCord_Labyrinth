package general

import hevs.graphics.FunGraphics

import java.awt.Color

class MazeDrawer(size: Int, name: String, val maze: Maze) {
  val fg = new FunGraphics(size, size, name)
  private val ratio = size/maze.dimensions
  private val WALLSIZE = 5 // ratio/2

  // Draw black bg
  fg.setColor(Color.black)
  for(y <- 0 until fg.height; x <- 0 until fg.width)
    fg.setPixel(x,y)

  // Draw one cell
  def drawCell(cell: Cell): Unit = {
    val x = cell.x * ratio + WALLSIZE
    val y = cell.y * ratio + WALLSIZE
    fg.setColor(Color.red)
    fg.drawFillRect(x, y, ratio-WALLSIZE, ratio-WALLSIZE)
    //Thread.sleep(300)
  }


  /**
   * Draw passage between the cells passed
   * as parameters.
   * @param startCell cell from which to draw
   * @param endCell cell to draw to
   */
  def drawCells(startCell: Cell, endCell: Cell): Unit = {
    if (startCell.x < endCell.x) {
      maze.addUsedPassage(Passage(endCell, startCell))
      drawCells(startCell, Direction.LEFT)
    } else if (startCell.x > endCell.x) {
      maze.addUsedPassage(Passage(endCell, startCell))
      drawCells(endCell, Direction.RIGHT)
    } else if (startCell.y < endCell.y) {
      maze.addUsedPassage(Passage(endCell, startCell))
      drawCells(startCell, Direction.UP)
    } else if (startCell.y > endCell.y) {
      maze.addUsedPassage(Passage(endCell, startCell))
      drawCells(endCell, Direction.DOWN)
    }
  }

  /**
   * Draw passage between a cell and
   * a given direction.
   * @param startCell cell from which to draw
   * @param direction direction to draw from startCell
   */
  def drawCells(startCell: Cell, direction: String): Unit = {
    fg.setColor(Color.white)

    val x = startCell.x * ratio + WALLSIZE
    val y = startCell.y * ratio + WALLSIZE

    direction match {
      case Direction.LEFT => fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.RIGHT => fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.UP => fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
      case Direction.DOWN => fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
      case _ => println(s"NOT A VALID DIRECTION! '$direction'")
    }

    Thread.sleep(100)
  }
}
