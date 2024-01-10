package general

import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

class Game(maze: Maze, mazeDrawer: MazeDrawer) {
  val fg: FunGraphics = mazeDrawer.fg

  val startCell: Cell = maze.getCell(0,0)
  val endCell: Cell = maze.getCell(maze.dimensions-1, maze.dimensions-1)

  private var playerX: Int = 0
  private var playerY: Int = 0

  mazeDrawer.drawPlayer(playerX, playerY)
  mazeDrawer.drawCell(endCell, Color.red)

  // Do something when a key has been pressed
  fg.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_W) {
        if(maze.usablePassages.contains(Passage(Set(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX, playerY-1)
        )))) {
          playerY -= 1
          mazeDrawer.drawPlayer(playerX, playerY, Direction.UP)
        }

      }
      if (e.getKeyCode == KeyEvent.VK_S) {
        if(maze.usablePassages.contains(Passage(Set(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX, playerY+1)
        )))) {
          playerY += 1
          mazeDrawer.drawPlayer(playerX, playerY, Direction.DOWN)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_A) {
        if(maze.usablePassages.contains(Passage(Set(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX-1, playerY)
        )))) {
          playerX -= 1
          mazeDrawer.drawPlayer(playerX, playerY, Direction.LEFT)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_D) {
        if(maze.usablePassages.contains(Passage(Set(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX+1, playerY)
        )))) {
          playerX += 1
          mazeDrawer.drawPlayer(playerX, playerY, Direction.RIGHT)
        }
      }

      mazeDrawer.drawCell(startCell, Color.green)
      mazeDrawer.drawCell(endCell, Color.red)

      mazeDrawer.drawPlayer(playerX,playerY)

      if(playerX == endCell.x && playerY == endCell.y) println("YOU WIN!!")
    }
  })
}
