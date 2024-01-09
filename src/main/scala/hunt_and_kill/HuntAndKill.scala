package hunt_and_kill

import general.{Cell, Maze, MazeDrawer}

import scala.collection.mutable
import scala.util.Random

class HuntAndKill {
  val MAZEDIMENSIONS = 10
  val maze: Maze = new Maze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(500, "Hunt & Kill", maze)

  huntAndKill(maze.getCell(
        Random.nextInt(MAZEDIMENSIONS),
        Random.nextInt(MAZEDIMENSIONS)
  ), visited = mutable.Set())

  // TODO: Add pathfinding
  // TODO: Add movable character

  /**
   * Hunt and kill Algorithm
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def huntAndKill(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location
    var tryCount = 0

    for(pos <- location.neighbours) {
      if (!visited.contains(pos) && pos != null) {
        mazeDrawer.drawCell(pos, location)
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
            mazeDrawer.drawCell(n, cell)
            huntAndKill(cell, visited)
            return
          }
        }
      }
    }
  }
}
