package Prims

import general.{Cell, Maze, MazeDrawer, Passage}

import scala.util.Random

class Prims {
  val MAZEDIMENSIONS = 15
  val maze: Maze = new Maze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer: MazeDrawer = new MazeDrawer(750, "Prims", maze)

  var frontier: Set[Cell] = Set()
  var visited: Set[Cell] = Set()

  primsfrontier(maze.getCell(0,0))


  def primsfrontier(location: Cell): Unit = {
    visited += location
    for(l <- location.neighbours){
      if(l != null && !visited.contains(l)){
        frontier += l
        //mazeDrawer.drawCell(l)
      }
    }
    var nextcell: Cell = getRandomFrontier
    visited += nextcell
    for(n <- nextcell.neighbours){
        if(visited.contains(n) ){
          mazeDrawer.drawCells(nextcell,n)
          primsfrontier(nextcell)
        }
    }
  }


  def getRandomFrontier: Cell = {
    if(frontier.size <= 1) return frontier.head
    val num = Random.nextInt(frontier.size)
    val p = frontier.toIndexedSeq(num)
    frontier -= p
    p
  }

}
