package hunt_and_kill

import general.{Cell, Direction, Maze, MazeDrawer, Passage}

import scala.collection.mutable
import scala.util.Random

class HuntAndKill {
  val MAZEDIMENSIONS = 10
  var maze: Maze = _
  var mazeDrawer: MazeDrawer = _

  maze = new Maze
  maze.create(MAZEDIMENSIONS)
  mazeDrawer = new MazeDrawer(500, "Hunt and Kill", MAZEDIMENSIONS)
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
    var tryCount = 0

    for(pos <- location.neighbours) {
      if (!visited.contains(pos) && pos != null) {
        // TODO: Find a way to get a non graphical result
        drawCells(pos, location)
        huntAndKill(pos, visited)
      }
    }

    if(visited.size < (maze.getDimensions()*maze.getDimensions()))
      hunt(visited)
  }

  def hunt(visited: mutable.Set[Cell]): Unit = {
    // Find new Cell
    for (y <- 0 until maze.getDimensions(); x <- 0 until maze.getDimensions()) {
      val cell = maze.getCell(x, y)
      if (!visited.contains(cell)) {
        for(n <- cell.neighbours) {
          if(visited.contains(n)) {
            drawCells(n, cell)
            huntAndKill(cell, visited)
            return
          }
        }
      }
    }
  }

  private def drawCells(pos: Cell, location: Cell) = {
    if (pos.x < location.x) {
      maze.addUsedPassage(Passage(location, pos))
      mazeDrawer.drawCell(pos, Direction.LEFT)
    } else if (pos.x > location.x) {
      maze.addUsedPassage(Passage(location, pos))
      mazeDrawer.drawCell(location, Direction.RIGHT)
    } else if (pos.y < location.y) {
      maze.addUsedPassage(Passage(location, pos))
      mazeDrawer.drawCell(pos, Direction.UP)
    } else if (pos.y > location.y) {
      maze.addUsedPassage(Passage(location, pos))
      mazeDrawer.drawCell(location, Direction.DOWN)
    }
  }
}
