package Prims

import general.{Cell, Game, MazeDrawer, Mode}

import java.awt.Color

class Prims(mode: String = Mode.GENERATION) {
  val MAZEDIMENSIONS = 10
  val maze: PrimsMaze = new PrimsMaze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(MAZEDIMENSIONS*50, "Prims", maze)

  var visited: Set[Cell] = Set()

  primsfrontier(maze.getCell(0,0))

  def primsfrontier(location: Cell): Unit = {
    visited += location

    // Get all neighbours that haven't been visited
    for(l <- location.neighbours){
      if(!visited.contains(l)) {
        maze.frontiers += l
        if(mode == Mode.GENERATION)
          mazeDrawer.drawCell(l, Color.red)
      }
    }
    val nextCell: Cell = maze.getRandomFrontier
    if(nextCell == null) return

    visited += nextCell

    // Find a neighbour that has already been visited
    for(n <- nextCell.neighbours) {
        if(visited.contains(n)) {
          mazeDrawer.drawCells(nextCell,n)
          nextCell.addUsableNeighbour(n)
          if(mode == Mode.GENERATION) Thread.sleep(10)
          primsfrontier(nextCell)
          return
        }
    }
  }

  // If in game mode, start game
  if(mode == Mode.GAME) new Game(maze, mazeDrawer)

  // TODO: Add pathfinding
  //  if(mode == Mode.RESOLUTION) new DFSResolution(maze)
}
