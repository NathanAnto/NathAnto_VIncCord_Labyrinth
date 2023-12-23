package general

import hevs.graphics.FunGraphics
import menu.Button

import java.awt.Color
import scala.reflect.ClassTag

class MenuDrawer(fg: FunGraphics) {
  /**
   * Draw a created button
   * @param btn
   */
  def drawButton(btn: Button[Runner]): Unit = {
    fg.setColor(Color.LIGHT_GRAY)
    fg.drawFillRect(btn.x, btn.y, 75, 20)
    fg.setColor(Color.BLACK)
    fg.drawString(btn.x+10, btn.y+15, btn.text)
  }
}
