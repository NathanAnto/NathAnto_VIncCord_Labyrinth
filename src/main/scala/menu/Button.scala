package menu

import general.Runner

import scala.reflect.ClassTag

class Button[+T:ClassTag](var x: Int, var y: Int, var text: String, runner: Runner){
  val width: Int = 75
  val height: Int = 20

  def launch(): Unit = runner.start()
  def clicked(posX: Int, posY: Int): Boolean = posX > x && posX < x + width && posY > y && posY < y + height
}

object Button {
  var buttons: List[Button[Runner]] = List()
  def createButton(x: Int, y: Int, text: String, runner: Runner): Button[Runner] = {
    val btn = new Button[runner.type](x,y,text,runner)
    buttons = buttons :+ btn
    btn
  }
  def clicked(btn: Button[Any], posX: Int, posY: Int) = btn.clicked(posX, posY)
}