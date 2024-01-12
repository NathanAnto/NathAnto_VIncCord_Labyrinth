package dfs

import general.{Cell, Game, Maze, MazeDrawer, Mode, Passage, Resolution}

import java.awt.Color
import scala.collection.mutable
import scala.util.Random

class DFS(mode: String = Mode.GENERATION) {
  val MAZEDIMENSIONS = 10
  val maze: Maze = new Maze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(MAZEDIMENSIONS*50, "DFS", maze)

  backtracker(maze.getCell(
    Random.nextInt(MAZEDIMENSIONS),
    Random.nextInt(MAZEDIMENSIONS)
  ), visited = mutable.Set())

  /**
   * DFS Algorithm backtracker
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def backtracker(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location

    for(pos <- location.neighbours) {
      if(!visited.contains(pos) && pos != null) {
        mazeDrawer.drawCells(Passage(Set(pos, location)))
        if(mode == Mode.GENERATION) Thread.sleep(500)
        mazeDrawer.drawCells(Passage(Set(pos, location)))
        backtracker(pos, visited)
      }
    }
  }

  // If in game mode, start game
  if(mode == Mode.GAME) new Game(maze, mazeDrawer)
}
