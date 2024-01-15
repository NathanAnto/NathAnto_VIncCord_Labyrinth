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


  // Do something when a key has been pressed
  fg.setKeyManager(new KeyAdapter() { // Will be called when a key has been pressed
    override def keyPressed(e: KeyEvent): Unit = {
      if (e.getKeyCode == KeyEvent.VK_W) {
        if(maze.getUsablePassages.contains(maze.getUsablePassage(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX, playerY-1)
        ))) {
          playerY -= 1
          mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.UP)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_S) {
        if(maze.getUsablePassages.contains(maze.getUsablePassage(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX, playerY+1)
        ))) {
          playerY += 1
          mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.DOWN)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_A) {
        if(maze.getUsablePassages.contains(maze.getUsablePassage(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX-1, playerY)
        ))) {
          playerX -= 1
          mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.LEFT)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_D) {
        if(maze.getUsablePassages.contains(maze.getUsablePassage(
          maze.getCell(playerX, playerY),
          maze.getCell(playerX+1, playerY)
        ))) {
          playerX += 1
          mazeDrawer.drawPlayer(maze.getCell(playerX, playerY), Direction.RIGHT)
        }
      }
      if (e.getKeyCode == KeyEvent.VK_R) {
        res.aStar(startCell, endCell)
      }

      mazeDrawer.drawCell(startCell, Color.green)
      mazeDrawer.drawCell(endCell, Color.red)

      mazeDrawer.drawPlayer(maze.getCell(playerX,playerY))

      if(playerX == endCell.x && playerY == endCell.y && !playSound){
        music.stop()
        println("YOU WIN!!")
        playSound = true

        // Spécifiez le chemin relatif du fichier batch par rapport au répertoire du projet
        val relativeBatchFilePath = "src/main/scala/general/volume.bat"

        // Spécifiez le chemin complet du fichier batch que vous souhaitez exécuter
        val batchFilePath = new File(relativeBatchFilePath).getAbsolutePath

        // Créez un objet ProcessBuilder en utilisant le chemin du fichier batch
        val processBuilder = Process(Seq("cmd", "/c", batchFilePath))

        // Exécutez le processus
        val process = processBuilder.run()

        // Attendez que le processus se termine et obtenez le code de sortie
        val exitCode = process.exitValue()

        // Affichez le code de sortie
        println(s"Le fichier batch a été exécuté avec le code de sortie : $exitCode")
        mazeDrawer.winimage()

        // lancement du son
        winSound.play()

      }
    }
  })
}
