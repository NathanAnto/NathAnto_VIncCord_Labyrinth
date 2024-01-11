package menu

import general.{MenuDrawer, Mode}
import hevs.graphics.FunGraphics

import java.awt.event.{MouseAdapter, MouseEvent}

class Menu {
  val fg = new FunGraphics(300, 300, "Menu")
  private val md = new MenuDrawer(fg)

  md.drawTitle(20, 30, "Maze generation")
  md.drawButton(Button.createButton(20, 40, "Prims", new Prims.Runner(Mode.GENERATION)))
  md.drawButton(Button.createButton(100, 40, "DFS", new dfs.Runner(Mode.GENERATION)))
  md.drawButton(Button.createButton(180, 40, "Kruskal", new kruskal.Runner(Mode.GENERATION)))


  md.drawTitle(20, 100, "Maze game")
  md.drawButton(Button.createButton(20, 110, "Prims", new Prims.Runner(Mode.GAME)))
  md.drawButton(Button.createButton(100, 110, "DFS", new dfs.Runner(Mode.GAME)))
  md.drawButton(Button.createButton(180, 110, "Kruskal", new kruskal.Runner(Mode.GAME)))


  md.drawTitle(20, 170, "Maze resolution")
  md.drawButton(Button.createButton(20, 180, "Prims", new Prims.Runner(Mode.RESOLUTION)))
  md.drawButton(Button.createButton(100, 180, "DFS", new dfs.Runner(Mode.RESOLUTION)))
  md.drawButton(Button.createButton(180, 180, "Kruskal", new kruskal.Runner(Mode.RESOLUTION)))

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
