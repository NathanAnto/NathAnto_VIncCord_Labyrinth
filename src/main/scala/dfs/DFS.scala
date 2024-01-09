package dfs

import general.{Cell, Maze, MazeDrawer}

import scala.collection.mutable
import scala.util.Random

class DFS {
  val MAZEDIMENSIONS = 10
  val maze: Maze = new Maze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(500, "DFS", maze)

  backtracker(maze.getCell(
    Random.nextInt(MAZEDIMENSIONS),
    Random.nextInt(MAZEDIMENSIONS)
  ), visited = mutable.Set())

  // TODO: Add pathfinding
  // TODO: Add movable character

  /**
   * DFS Algorithm backtracker
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def backtracker(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location;

    for(pos <- location.neighbours) {
      if(!visited.contains(pos) && pos != null) {
        // TODO: Find a way to get a non graphical result
        mazeDrawer.drawCell(pos, location)

        backtracker(pos, visited)
      }
    }
  }
}
