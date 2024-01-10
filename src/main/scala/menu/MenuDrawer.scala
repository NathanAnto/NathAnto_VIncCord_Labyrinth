package general

import hevs.graphics.FunGraphics
import menu.Button

import java.awt.Color

class MenuDrawer(fg: FunGraphics) {

  def drawTitle(x: Int, y: Int, text: String): Unit = {
    fg.setColor(Color.BLACK)
    fg.drawString(x, y, text,Color.BLACK, 20)
  }

  /**
   * Draw a created [[Button]]
   * @param btn [[Button]] that will be drawn
   */
  def drawButton(btn: Button): Unit = {
    fg.setColor(Color.LIGHT_GRAY)
    fg.drawFillRect(btn.x, btn.y, 75, 20)
    fg.setColor(Color.BLACK)
    fg.drawString(btn.x+10, btn.y+15, btn.text)
  }
}
