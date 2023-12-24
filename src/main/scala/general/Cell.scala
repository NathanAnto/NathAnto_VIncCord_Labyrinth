package general

import scala.collection.mutable
import scala.util.Random

class Cell(val x: Int, val y: Int) {
  val neighbours: Array[Cell] = new Array(4)

  /**
   * Generates list of numbers from 0 to 'length'
   * in random order
   * @param length size of sequence as an [[Int]]
   * @return [[Array]] of [[Int]]s
   */
  private def generateSequence(length: Int): Array[Int] = {
    val res: Array[Int] = Array.fill(length)(-1)
    var num = 0
    for((i,index) <- res.zipWithIndex) {
      do {
        num = Random.nextInt(length);
      } while (res.contains(num))
      res(index) = num;
    }
    res
  }

  override def toString: String = s"Cell($x, $y)"

  def getNeighbours(maze: Maze) = {
    // Random neighbour order
    val indices = generateSequence(neighbours.length)
    neighbours(indices(0)) = maze.getCell(x, y - 1) // TOP
    neighbours(indices(1)) = maze.getCell(x - 1, y) // LEFT
    neighbours(indices(2)) = maze.getCell(x, y + 1) // BOTTOM
    neighbours(indices(3)) = maze.getCell(x + 1, y) // RIGHT
  }
}