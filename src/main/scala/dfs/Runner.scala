package dfs

import general.{Cell, Maze, MazeDrawer, Direction}

import scala.collection.mutable
import scala.util.Random

class Runner extends Runnable {
  val MAZEDIMENSIONS = 10
  var maze: Maze = _
  var mazeDrawer: MazeDrawer = _

  override def run(): Unit = {
    maze = new Maze
    maze.create(MAZEDIMENSIONS)
    mazeDrawer = new MazeDrawer(500, "DFS", MAZEDIMENSIONS)
    backtracker(maze.getCell(
      Random.nextInt(MAZEDIMENSIONS),
      Random.nextInt(MAZEDIMENSIONS)
    ), visited = mutable.Set())
  }

  /**
   * DFS Algorithm backtracker
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def backtracker(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location;

    for(pos <- location.neighbours) {
      if(!visited.contains(pos) && pos != null) {
        println(s"Found neighbour: $pos")

        // TODO: Find a way to get a non graphical result

        if(pos.x < location.x)
          mazeDrawer.drawCell(pos, Direction.LEFT)
        else if (pos.x > location.x)
          mazeDrawer.drawCell(location, Direction.RIGHT)
        else if (pos.y < location.y)
          mazeDrawer.drawCell(pos, Direction.UP)
        else if (pos.y > location.y)
          mazeDrawer.drawCell(location, Direction.DOWN)

        backtracker(pos, visited)
      }
    }
  }
}