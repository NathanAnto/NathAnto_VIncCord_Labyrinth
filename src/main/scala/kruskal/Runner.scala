package kruskal

import general.{Cell, Direction, Maze, MazeDrawer, Passage}

import scala.util.Random

class Runner extends Runnable {
  val MAZEDIMENSIONS = 10
  var maze: Maze = _
  var mazeDrawer: MazeDrawer = _
  override def run(): Unit = {
    maze = new Maze
    maze.create(MAZEDIMENSIONS)
    mazeDrawer = new MazeDrawer(500, "Kruskal", MAZEDIMENSIONS)
    kruskal();
  }

  def kruskal(): Unit = {
    // loop over every possible passage
    do {
      val p = maze.getRandomPassage
      var connection1: Set[Cell] = Set()
      var connection2: Set[Cell] = Set()
      var connected: Boolean = false
      maze.connections.foreach(conn => {
        if (conn.contains(p.cell1) && conn.contains(p.cell2))
          connected = true
        else {
          if (conn.contains(p.cell1)) connection1 += p.cell1
          if (conn.contains(p.cell2)) connection2 += p.cell2
        }
      })

      if(!connected) {
        // concat both sets to make them connected
        val conn1: Option[Set[Cell]] = maze.connections.find(c => c.contains(p.cell1))
        val conn2: Option[Set[Cell]] = maze.connections.find(c => c.contains(p.cell2))
        maze.connections -= conn1.get
        maze.connections -= conn2.get

        val newConnection: Set[Cell] = conn1.get ++ conn2.get
        maze.connections += newConnection

        if(p.cell1.x < p.cell2.x)
          mazeDrawer.drawCell(p.cell1, Direction.LEFT)
        else if (p.cell1.x > p.cell2.x)
          mazeDrawer.drawCell(p.cell2, Direction.RIGHT)
        else if (p.cell1.y < p.cell2.y)
          mazeDrawer.drawCell(p.cell1, Direction.UP)
        else if (p.cell1.y > p.cell2.y)
          mazeDrawer.drawCell(p.cell2, Direction.DOWN)

        println(maze.connections.mkString(", "))
      }
    } while(maze.connections.size > 1)
    println("Finished Kruskal")
  }
}