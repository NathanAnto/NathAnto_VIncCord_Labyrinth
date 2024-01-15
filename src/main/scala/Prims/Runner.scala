package Prims

class Runner(mode: String, dimensions: Int) extends Runnable {

  override def run(): Unit = {
    new Prims(mode, dimensions)
  }
}
