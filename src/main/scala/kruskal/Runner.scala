package kruskal

class Runner(mode: String, dimensions: Int) extends Runnable {
  override def run(): Unit = {
    new Kruskal(mode, dimensions)
  }
}