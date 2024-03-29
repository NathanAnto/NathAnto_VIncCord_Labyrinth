package kruskal

import general.{Cell, Game, MazeDrawer, Mode, Passage}

class Kruskal(mode: String = Mode.GENERATION, dimensions: Int) {
  val MAZEDIMENSIONS: Int = dimensions
  val maze: KruskalMaze = new KruskalMaze(MAZEDIMENSIONS)
  maze.create()

  val mazeDrawer = new MazeDrawer(MAZEDIMENSIONS*50, "Kruskal", maze)

  kruskal();

  def kruskal(): Unit = {
    // loop over every possible passage
    do {
      val passage: Passage = maze.getRandomPassage
      val passageCells = passage.cells.toIndexedSeq

      var connection1: Set[Cell] = Set()
      var connection2: Set[Cell] = Set()
      var connected: Boolean = false

      maze.connections.foreach(conn => {
        if (conn.contains(passageCells(0)) && conn.contains(passageCells(1)))
          connected = true
        else {
          if (conn.contains(passageCells(0))) connection1 += passageCells(0)
          if (conn.contains(passageCells(1))) connection2 += passageCells(1)
        }
      })

      if(!connected) {
        // concat both sets to connect them
        val conn1: Option[Set[Cell]] = maze.connections.find(c => c.contains(passageCells(0)))
        val conn2: Option[Set[Cell]] = maze.connections.find(c => c.contains(passageCells(1)))
        maze.connections -= conn1.get
        maze.connections -= conn2.get

        val newConnection: Set[Cell] = conn1.get ++ conn2.get
        maze.connections += newConnection

        mazeDrawer.drawCells(Array(passage.cells(0), passage.cells(1)))
        if(mode == Mode.GENERATION) Thread.sleep(100)
      }
    } while(maze.connections.size > 1)
  }

  // If in game mode, start game
  if(mode == Mode.GAME) new Game(maze, mazeDrawer)
}
