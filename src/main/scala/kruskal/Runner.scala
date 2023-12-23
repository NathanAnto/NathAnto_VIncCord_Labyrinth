package kruskal

import general.MazeDrawer

class Runner extends general.Runner {
  val MAZEDIMENSIONS = 10
  var mazeDrawer: MazeDrawer = _

  override def start(): Unit = {
    mazeDrawer = new MazeDrawer(500, "Kruskal", MAZEDIMENSIONS)
    kruskal();
  }

  def kruskal(): Unit = {
    
  }
}
