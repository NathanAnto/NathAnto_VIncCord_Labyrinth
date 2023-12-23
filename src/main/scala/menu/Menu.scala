package menu

import dfs.Runner
import general.MenuDrawer
import hevs.graphics.FunGraphics

import java.awt.event.{MouseAdapter, MouseEvent}

class Menu {
  // TODO: Rewrite menu code
  val fg = new FunGraphics(300, 300, "Menu")
  val md = new MenuDrawer(fg)

  md.drawButton(Button.createButton(20, 20, "Vinc", new Vinc()))
  md.drawButton(Button.createButton(100, 20, "DFS", new dfs.Runner()))
  md.drawButton(Button.createButton(180, 20, "Kruskal", new kruskal.Runner()))

  fg.addMouseListener(new MouseAdapter() {
    override def mouseClicked(event: MouseEvent): Unit = {
      // Get the mouse position from the event
      val posX = event.getX
      val posY = event.getY

      println(s"Mouse position $posX - $posY")

      for(btn <- Button.buttons) {
        if(Button.clicked(btn, posX, posY)) {
          btn.launch()
        }
      }
    }
  })
}
