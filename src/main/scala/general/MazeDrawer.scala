package general

import hevs.graphics.FunGraphics

import java.awt.Color

class MazeDrawer(size: Int, name: String, val mazeDimensions: Int) {
  val fg = new FunGraphics(size, size, name)
  val ratio = size/mazeDimensions

  // Draw black bg
  fg.setColor(Color.black)
  for(y <- 0 until fg.height; x <- 0 until fg.width) fg.setPixel(x,y)

  def drawCell(startPos: Cell, direction: String): Unit = {
    fg.setColor(Color.white)

    val x = startPos.x * ratio + 1
    val y = startPos.y * ratio + 1

    direction match {
      case Direction.LEFT => fg.drawFillRect(x, y, ratio*2-1, ratio-1)
      case Direction.RIGHT => fg.drawFillRect(x, y, ratio*2-1, ratio-1)
      case Direction.UP => fg.drawFillRect(x, y, ratio-1, ratio*2-1)
      case Direction.DOWN => fg.drawFillRect(x, y, ratio-1, ratio*2-1)
    }

    Thread.sleep(100)
  }
}
