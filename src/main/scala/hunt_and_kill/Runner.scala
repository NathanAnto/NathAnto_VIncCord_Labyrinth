package hunt_and_kill

import java.lang.Runnable

class Runner(mode: String, dimensions: Int) extends Runnable {
  override def run(): Unit = {
    new HuntAndKill(mode, dimensions)
  }
}
