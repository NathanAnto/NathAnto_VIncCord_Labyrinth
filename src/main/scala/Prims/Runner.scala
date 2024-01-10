package Prims

class Runner(mode: String) extends Runnable {

  override def run(): Unit = {
    new Prims(mode)
  }
}
