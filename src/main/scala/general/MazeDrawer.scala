package general

import hevs.graphics.FunGraphics

import java.awt.Color

class MazeDrawer(size: Int, name: String, val mazeDimensions: Int) {
  val fg = new FunGraphics(size, size, name)
  private val ratio = size/mazeDimensions
  private val WALLSIZE = ratio/2

  // Draw black bg
  fg.setColor(Color.black)
  for(y <- 0 until fg.height; x <- 0 until fg.width) fg.setPixel(x,y)

  def drawCell(startPos: Cell, direction: String): Unit = {
    fg.setColor(Color.white)

    val x = startPos.x * ratio + WALLSIZE
    val y = startPos.y * ratio + WALLSIZE

    direction match {
      case Direction.LEFT => fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.RIGHT => fg.drawFillRect(x, y, ratio*2-WALLSIZE, ratio-WALLSIZE)
      case Direction.UP => fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
      case Direction.DOWN => fg.drawFillRect(x, y, ratio-WALLSIZE, ratio*2-WALLSIZE)
    }

    Thread.sleep(100)
  }
}
