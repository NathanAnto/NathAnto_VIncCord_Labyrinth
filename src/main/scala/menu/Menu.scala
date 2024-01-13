package menu

import general.{MenuDrawer, Mode}
import hevs.graphics.FunGraphics

import java.awt.event.{MouseAdapter, MouseEvent}

class Menu {
  private val fg = new FunGraphics(300, 300, "Menu")
  private val md = new MenuDrawer(fg)
  private val MAZEDIMENSIONS = 10

  md.drawTitle(20, 30, "Maze generation")
  md.drawButton(Button.createButton(20, 40, "Prims", new Prims.Runner(Mode.GENERATION, MAZEDIMENSIONS)))
  md.drawButton(Button.createButton(100, 40, "DFS", new dfs.Runner(Mode.GENERATION, MAZEDIMENSIONS)))
  md.drawButton(Button.createButton(180, 40, "Kruskal", new kruskal.Runner(Mode.GENERATION, MAZEDIMENSIONS)))

  md.drawTitle(20, 100, "Maze game")
  md.drawButton(Button.createButton(20, 110, "Prims", new Prims.Runner(Mode.GAME,MAZEDIMENSIONS)))
  md.drawButton(Button.createButton(100, 110, "DFS", new dfs.Runner(Mode.GAME,MAZEDIMENSIONS)))
  md.drawButton(Button.createButton(180, 110, "Kruskal", new kruskal.Runner(Mode.GAME,MAZEDIMENSIONS)))

  md.drawTitle(20, 170, "Controls")
  md.drawText(20, 190, "MOVE: W,A,S,D")
  md.drawText(20, 210, "SHOW RESOLUTION: R")

  fg.addMouseListener(new MouseAdapter() {
    override def mouseClicked(event: MouseEvent): Unit = {
      // Get the mouse position from the event
      val posX = event.getX
      val posY = event.getY

      println(s"Mouse position $posX - $posY")

      for(btn <- Button.buttons) {
        if(btn.clicked(posX, posY)) {
          btn.launch()
        }
      }
    }
  })
}
