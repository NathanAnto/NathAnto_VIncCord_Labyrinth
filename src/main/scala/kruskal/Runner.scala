package kruskal

import general.MazeDrawer

class Runner extends Runnable {
  val MAZEDIMENSIONS = 10
  var mazeDrawer: MazeDrawer = _

  override def run(): Unit = {
    mazeDrawer = new MazeDrawer(500, "Kruskal", MAZEDIMENSIONS)
    kruskal();
  }

  def kruskal(): Unit = {
    
  }
}
