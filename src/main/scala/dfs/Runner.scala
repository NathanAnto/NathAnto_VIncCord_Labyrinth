package dfs

class Runner(mode: String) extends Runnable {

  override def run(): Unit = {
    new DFS(mode)
  }
}