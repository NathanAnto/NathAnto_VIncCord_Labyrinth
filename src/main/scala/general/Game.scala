package general
import scala.sys.process._
import java.io.File
import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

class Game(maze: Maze, mazeDrawer: MazeDrawer) {

  val fg: FunGraphics = mazeDrawer.fg
  val res = new Resolution(maze, mazeDrawer)
  val startCell: Cell = maze.getCell(0,0)
  val endCell: Cell = maze.getCell(maze.dimensions-1, maze.dimensions-1)

  // launch song
  private val music: AudioPlayer = new AudioPlayer("general/buckshotost.wav")
  private val winSound: AudioPlayer = new AudioPlayer("general/win.wav")
  music.play()

  private var playSound = false

  private var playerX: Int = startCell.x
  private var playerY: Int = startCell.y

  mazeDrawer.drawPlayer(maze.getCell(playerX, playerY))
  mazeDrawer.drawCell(endCell, Color.red)

  fg.setKeyManager(new KeyAdapter() {

    // When a key has been pressed, this function will be called
    override def keyPressed(e: KeyEvent): Unit = {
      if(!playSound) {
        if (e.getKeyCode == KeyEvent.VK_W) {
          if (maze.getUsablePassages.contains(maze.getUsablePassage(
            maze.getCell(playerX, playerY),
            maze.getCell(playerX, playerY - 1)
          ))) {
            playerY -= 1
            mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.UP)
          }
        }
        if (e.getKeyCode == KeyEvent.VK_S) {
          if (maze.getUsablePassages.contains(maze.getUsablePassage(
            maze.getCell(playerX, playerY),
            maze.getCell(playerX, playerY + 1)
          ))) {
            playerY += 1
            mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.DOWN)
          }
        }
        if (e.getKeyCode == KeyEvent.VK_A) {
          if (maze.getUsablePassages.contains(maze.getUsablePassage(
            maze.getCell(playerX, playerY),
            maze.getCell(playerX - 1, playerY)
          ))) {
            playerX -= 1
            mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.LEFT)
          }
        }
        if (e.getKeyCode == KeyEvent.VK_D) {
          if (maze.getUsablePassages.contains(maze.getUsablePassage(
            maze.getCell(playerX, playerY),
            maze.getCell(playerX + 1, playerY)
          ))) {
            playerX += 1
            mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.RIGHT)
          }
        }
        if (e.getKeyCode == KeyEvent.VK_R) {
          playSound = true
          res.aStar(startCell, endCell)
        }

        mazeDrawer.drawCell(startCell, Color.green)
        mazeDrawer.drawCell(endCell, Color.red)

        mazeDrawer.drawPlayer(maze.getCell(playerX, playerY))
      }

      if(playerX == endCell.x && playerY == endCell.y && !playSound){
        music.stop()
        playSound = true

        // Specify the relative path of the batch file to the project directory
        val relativeBatchFilePath = "src/main/scala/general/volume.bat"

        // Specify the full path of the batch file you wish to run
        val batchFilePath = new File(relativeBatchFilePath).getAbsolutePath

        // Create a ProcessBuilder object using the batch file path
        val processBuilder = Process(Seq("cmd", "/c", batchFilePath))

        val process = processBuilder.run()

        // Wait for the process to finish and obtain the output code
        val exitCode = process.exitValue()

        mazeDrawer.winImage()
        winSound.play()
      }
    }
  })
}
