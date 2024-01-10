package general

import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

class MazeDrawer(size: Int, name: String, val maze: Maze) {

  val fg = new FunGraphics(size, size, name)
  private val ratio = size/maze.dimensions
  private val WALLSIZE = math.min(ratio/2, ratio - 1)

  // Draw black bg
  def drawBlackBackground() = {
    fg.setColor(Color.black)
    for(y <- 0 until fg.height; x <- 0 until fg.width)
      fg.setPixel(x,y)
  }
  drawBlackBackground()

  /**
   * Get the position in the window based on cell position
   * @param v value of cell position
   * @return value based on window size
   */
  private def getRealValue(v: Int): Int = v * ratio + WALLSIZE / 2


  def drawCell(cell: Cell): Unit = {
    drawCell(maze.getCell(cell.x,cell.y),Color.white)
  }
  def drawCell(cell: Cell, color: Color): Unit = {
    val x = getRealValue(cell.x)
    val y = getRealValue(cell.y)
    fg.setColor(color)
    fg.drawFillRect(x, y, ratio-WALLSIZE, ratio-WALLSIZE)
  }

  /**
   * Draw passage between the cells passed
   * as parameters.
   * @param startCell cell from which to draw
   * @param endCell cell to draw to
   */
  def drawCells(startCell: Cell, endCell: Cell): Unit = {
    if (startCell.x < endCell.x) {
      maze.addUsedPassage(Passage(Set(endCell, startCell)))
      drawCells(startCell, Direction.LEFT)
    } else if (startCell.x > endCell.x) {
      maze.addUsedPassage(Passage(Set(endCell, startCell)))
      drawCells(endCell, Direction.RIGHT)
    } else if (startCell.y < endCell.y) {
      maze.addUsedPassage(Passage(Set(endCell, startCell)))
      drawCells(startCell, Direction.UP)
    } else if (startCell.y > endCell.y) {
      maze.addUsedPassage(Passage(Set(endCell, startCell)))
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

    val x: Int = getRealValue(startCell.x)
    val y: Int = getRealValue(startCell.y)

    direction match {
      case Direction.LEFT =>  fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.RIGHT => fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.UP =>    fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
      case Direction.DOWN =>  fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
      case _ => println(s"NOT A VALID DIRECTION! '$direction'")
    }
  }

  /**
   *
   * @param x Current x [[Cell]] position
   * @param y Current y [[Cell]] position
   */
  def drawPlayer(x: Int, y: Int): Unit = {
    drawCell(maze.getCell(x,y),Color.blue)
  }
  /**
   *
   * @param x Current x [[Cell]] position
   * @param y Current y [[Cell]] position
   * @param direction What direction the player moved
   */
  def drawPlayer(x: Int, y: Int, direction: String): Unit = {
    drawPlayer(x,y)

    direction match {
      case Direction.LEFT => drawCell(maze.getCell(x+1,y), Color.white)
      case Direction.RIGHT => drawCell(maze.getCell(x-1,y), Color.white)
      case Direction.UP => drawCell(maze.getCell(x,y+1), Color.white)
      case Direction.DOWN => drawCell(maze.getCell(x,y-1), Color.white)
    }
  }
}
