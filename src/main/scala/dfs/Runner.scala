package dfs

class Runner(mode: String, dimensions: Int) extends Runnable {

  override def run(): Unit = {
    new DFS(mode, dimensions)
  }
}