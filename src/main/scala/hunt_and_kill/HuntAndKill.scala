package hunt_and_kill

import general.{Cell, Game, Maze, MazeDrawer, Mode}

import scala.collection.mutable
import scala.util.Random

class HuntAndKill(mode: String, dimensions: Int) {
  val MAZEDIMENSIONS: Int = dimensions
  val maze: Maze = new Maze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(MAZEDIMENSIONS*50, "Hunt & Kill", maze)

  huntAndKill(maze.getCell(
    Random.nextInt(MAZEDIMENSIONS),
    Random.nextInt(MAZEDIMENSIONS)
  ), visited = mutable.Set())

  /**
   * Hunt and kill Algorithm
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def huntAndKill(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location

    for(pos <- location.neighbours) {
      if(!visited.contains(pos) && pos != null) {
        if(mode == Mode.GENERATION) Thread.sleep(100)
        mazeDrawer.drawCells(Array(pos, location))
        huntAndKill(pos, visited)
      }
    }

    if(visited.size < (maze.dimensions*maze.dimensions))
      hunt(visited)
  }

  def hunt(visited: mutable.Set[Cell]): Unit = {
    // Find new Cell
    for (y <- 0 until maze.dimensions; x <- 0 until maze.dimensions) {
      val cell = maze.getCell(x, y)
      if (!visited.contains(cell)) {
        for(n <- cell.neighbours) {
          if(visited.contains(n)) {
            mazeDrawer.drawCells(Array(n, cell))
            huntAndKill(cell, visited)
            return
          }
        }
      }
    }
  }

  // If in game mode, start game
  if(mode == Mode.GAME) new Game(maze, mazeDrawer)
}
