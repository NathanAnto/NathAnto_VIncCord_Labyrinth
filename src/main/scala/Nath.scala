import hevs.graphics.FunGraphics

import java.awt.Color
import scala.collection.mutable
import scala.util.Random
case class Cell(x: Int, y: Int)
class Nath {
  val fg = new FunGraphics(300, 300, "DFS")
  val MAZEDIMENSIONS = 6
  val ratio = fg.width/MAZEDIMENSIONS

  fg.setColor(Color.black)
  // Draw black bg
  for(y <- 0 until fg.height; x <- 0 until fg.width) {
    fg.setPixel(x,y)
  }

  /**
   * DFS Algorithm
    * @param location
   * @param visited
   */
  def backtracker(location: Cell, visited: mutable.Set[Cell]): Unit = {
//    Thread.sleep(1000)
    visited += location;

    val neighbours: Array[Cell] = new Array(4)

    // Randomize neighbour order
    val indices = generateSequence(neighbours.length)
    println(indices.mkString(","))
    neighbours(indices(0)) = Cell(location.x, location.y-1) // TOP
    neighbours(indices(1)) = Cell(location.x-1, location.y) // LEFT
    neighbours(indices(2)) = Cell(location.x, location.y+1) // BOTTOM
    neighbours(indices(3)) = Cell(location.x+1, location.y) // RIGHT

    for(pos <- neighbours) {
      val isInDimensions: Boolean = (
        pos.x < MAZEDIMENSIONS && pos.x >= 0 &&
        pos.y < MAZEDIMENSIONS && pos.y >= 0
      )

      if(!visited.contains(pos) && isInDimensions) {
        println(s"Found neighbour: $pos")
        fg.setColor(Color.white)

        // GOING LEFT
        if(pos.x < location.x)
          fg.drawFillRect(pos.x*ratio+1, pos.y*ratio+1, ratio*2-1, ratio-1)
        // GOING RIGHT
        else if (pos.x > location.x)
          fg.drawFillRect(location.x*ratio+1, location.y*ratio+1, ratio*2-1, ratio-1)
        // GOING UP
        else if (pos.y < location.y)
          fg.drawFillRect(pos.x*ratio+1, pos.y*ratio+1, ratio-1, ratio*2-1)
        // GOING DOWN
        else if (pos.y > location.y)
          fg.drawFillRect(location.x*ratio+1, location.y*ratio+1, ratio-1, ratio*2-1)

        Thread.sleep(250)
        backtracker(pos, visited)
      }
    }
  }

  def generateSequence(l: Int): Array[Int] = {
    val res: Array[Int] = Array.fill(l)(-1)

    var num = 0
    for((i,index) <- res.zipWithIndex) {
      do {
        num = Random.nextInt(l);
      } while (res.contains(num))
      res(index) = num;
    }
    res
  }

  var visitedCells: mutable.Set[Cell] = mutable.Set()
  backtracker(Cell(0,0), visitedCells)
}
