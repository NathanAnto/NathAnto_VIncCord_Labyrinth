package kruskal

import general.{Cell, Direction, MazeDrawer}

class Kruskal {
  val MAZEDIMENSIONS = 10
  val maze: KruskalMaze = new KruskalMaze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer = new MazeDrawer(500, "Kruskal", maze)

  kruskal();

  // TODO: Add pathfinding
  // TODO: Add movable character

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
        // concat both sets to connect them
        val conn1: Option[Set[Cell]] = maze.connections.find(c => c.contains(p.cell1))
        val conn2: Option[Set[Cell]] = maze.connections.find(c => c.contains(p.cell2))
        maze.connections -= conn1.get
        maze.connections -= conn2.get

        val newConnection: Set[Cell] = conn1.get ++ conn2.get
        maze.connections += newConnection

        mazeDrawer.drawCell(p.cell1, p.cell2)
      }
    } while(maze.connections.size > 1)
    println("Finished Kruskal")
  }
}
