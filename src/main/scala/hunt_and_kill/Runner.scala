package hunt_and_kill

import java.lang.Runnable

class Runner extends Runnable {

  override def run(): Unit = {
    val instance = new HuntAndKill
  }
}
