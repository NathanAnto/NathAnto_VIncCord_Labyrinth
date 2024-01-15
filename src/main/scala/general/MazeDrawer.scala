package general

import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap
import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

class MazeDrawer(size: Int, name: String, val maze: Maze) {

  val fg = new FunGraphics(size, size, name)
  private val RATIO = size/maze.dimensions
  private val WALLSIZE = math.max(1, math.min(RATIO/2, RATIO - 1))

  private def drawBlackBackground(): Unit = {
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
  private def getRealValue(v: Int): Int = v * RATIO + WALLSIZE/2

  def drawCell(cell: Cell, color: Color = Color.white): Unit = {
    val x = getRealValue(cell.x)
    val y = getRealValue(cell.y)
    fg.setColor(color)
    fg.drawFillRect(x, y, RATIO-WALLSIZE, RATIO-WALLSIZE)
  }

  /**
   * Draw passage between the cells passed
   * as parameters.
   * @param cells cells to draw
   * @param color Optional color
   */
  def drawCells(cells: Array[Cell], color: Color = Color.white): Unit = {
    val startCell = cells(0)
    val endCell = cells(1)

    if (startCell.x < endCell.x) {
      maze.addUsablePassage(startCell, endCell)
      drawCells(startCell, Direction.LEFT, color)
    } else if (startCell.x > endCell.x) {
      maze.addUsablePassage(startCell, endCell)
      drawCells(endCell, Direction.RIGHT, color)
    } else if (startCell.y < endCell.y) {
      maze.addUsablePassage(startCell, endCell)
      drawCells(startCell, Direction.UP, color)
    } else if (startCell.y > endCell.y) {
      maze.addUsablePassage(startCell, endCell)
      drawCells(endCell, Direction.DOWN, color)
    }
  }

  /**
   * Draw passage between a cell and
   * a given direction.
   * @param startCell cell from which to draw
   * @param direction direction to draw from startCell
   */
  private def drawCells(startCell: Cell, direction: String, color: Color): Unit = {
    fg.setColor(color)

    val x: Int = getRealValue(startCell.x)
    val y: Int = getRealValue(startCell.y)

    direction match {
      case Direction.LEFT =>  fg.drawFillRect(x, y, RATIO * 2 - WALLSIZE, RATIO - WALLSIZE)
      case Direction.RIGHT => fg.drawFillRect(x, y, RATIO * 2 - WALLSIZE, RATIO - WALLSIZE)
      case Direction.UP =>    fg.drawFillRect(x, y, RATIO - WALLSIZE, RATIO * 2 - WALLSIZE)
      case Direction.DOWN =>  fg.drawFillRect(x, y, RATIO - WALLSIZE, RATIO * 2 - WALLSIZE)
      case _ => println(s"NOT A VALID DIRECTION! '$direction'")
    }
  }

  def winImage(): Unit = {
    // Image should be in /src/res/ directory for this example to work
    val bm = new GraphicsBitmap("/res/img/mandrill.jpg")
    val scale = 1
    var angle = 0
    var offset = 0.01
    fg.frontBuffer.synchronized {
      fg.clear(Color.white)
      val x = fg.getFrameWidth / 2
      val y = fg.getFrameHeight / 2 - 25
      // Coordinates X and Y are where to draw the center of the image
      fg.drawTransformedPicture(x, y, angle, scale, bm)
    }
  }

  /**
   * If no direction is passed, just draw the cell
   * @param x Current x [[Cell]] position
   * @param y Current y [[Cell]] position
   * @param direction What direction the player moved
   */
  def drawPlayer(cell: Cell, direction: String = "None"): Unit = {
    direction match {
      case Direction.LEFT => drawCell(maze.getCell(cell.x+1,cell.y), Color.white)
      case Direction.RIGHT => drawCell(maze.getCell(cell.x-1,cell.y), Color.white)
      case Direction.UP => drawCell(maze.getCell(cell.x,cell.y+1), Color.white)
      case Direction.DOWN => drawCell(maze.getCell(cell.x,cell.y-1), Color.white)
      case _ => drawCell(cell, Color.blue)
    }
  }
}
