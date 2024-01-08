package dfs

import general.{Cell, Direction, Maze, MazeDrawer, Passage}

import scala.collection.mutable
import scala.util.Random

class DFS {
  val MAZEDIMENSIONS = 10
  var maze: DFSMaze = new DFSMaze
  var mazeDrawer: MazeDrawer = _

  maze.create(MAZEDIMENSIONS)
  mazeDrawer = new MazeDrawer(500, "DFS", MAZEDIMENSIONS)
  backtracker(maze.getCell(0,0
//    Random.nextInt(MAZEDIMENSIONS),
//    Random.nextInt(MAZEDIMENSIONS)
  ), visited = mutable.Set())

  // TODO: Add pathfinding

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
        if(pos.x < location.x) {
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

        backtracker(pos, visited)
      }
    }
  }
}
