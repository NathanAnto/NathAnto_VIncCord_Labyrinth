package kruskal

class Runner(mode: String) extends Runnable {
  override def run(): Unit = {
    new Kruskal(mode)
  }
}