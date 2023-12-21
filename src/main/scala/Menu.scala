import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{MouseAdapter, MouseEvent}
class Menu {
  val fg = new FunGraphics(300, 300, "Menu")

  //Nath
  fg.setColor(Color.LIGHT_GRAY)
  fg.drawFillRect(80, 20, 75, 20)
  fg.setColor(Color.BLACK)
  fg.drawString(90, 35, "DFS de Nathan")


  //Vinc
  fg.setColor(Color.LIGHT_GRAY)
  fg.drawFillRect(20, 20, 55, 20)
  fg.setColor(Color.BLACK)
  fg.drawString(30, 35, "salope")


  fg.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e

      // Get the mouse position from the event
      val posx = event.getX
      val posy = event.getY

      println(s"Mouse position $posx - $posy")

      // Jeux Vinc
      if (posx > 20 && posx < 75 && posy > 20 && posy < 40) {
        var vin: Vinc = new Vinc()
      }

      // Jeux Nath
      if (posx > 80 && posx < 135 && posy > 20 && posy < 40) {
        var nat: Nath = new Nath()
      }

    }
  })



}
