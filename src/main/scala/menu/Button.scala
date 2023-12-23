package menu

class Button(var x: Int, var y: Int, var text: String, runner: Runnable){
  val width: Int = 75
  val height: Int = 20

  /**
   * Creates a thread and runs the
   * [[Button]]s [[Runnable]] class
   */
  def launch(): Unit = {
    val th = new Thread(runner)
    th.setName(runner.toString)
    th.start()
  }
  def clicked(posX: Int, posY: Int): Boolean = posX > x && posX < x + width && posY > y && posY < y + height
}

object Button {
  var buttons: List[Button] = List()

  /**
   * Creates a new instance of the [[Button]] class
   * @param x horizontal position
   * @param y vertical position
   * @param text the text shown
   * @param runner [[Runnable]] class that will run
   * @return the [[Button]] created
   */
  def createButton(x: Int, y: Int, text: String, runner: Runnable): Button = {
    val btn = new Button(x,y,text,runner)
    buttons = buttons :+ btn
    btn
  }

  /**
   *
   * @param btn [[Button]] that will be checked for the click
   * @param posX horizontal coordinate of click
   * @param posY vertical coordinate of click
   * @return [[Boolean]] if clicked or not
   */
  def clicked(btn: Button, posX: Int, posY: Int): Boolean = btn.clicked(posX, posY)
}