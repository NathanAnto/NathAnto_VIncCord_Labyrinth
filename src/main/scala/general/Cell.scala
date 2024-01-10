package general

import scala.util.Random

class Cell(val x: Int, val y: Int) {
  var neighbours: Set[Cell] = Set()

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

  def findNeighbours(maze: Maze) = {
    // Random neighbour order
    val directions: Array[Cell] = new Array(4)
    val indices = generateSequence(directions.length)

    directions(indices(0)) = maze.getCell(x, y - 1) // TOP
    directions(indices(1)) = maze.getCell(x - 1, y) // LEFT
    directions(indices(2)) = maze.getCell(x, y + 1) // BOTTOM
    directions(indices(3)) = maze.getCell(x + 1, y) // RIGHT
    for(d <- directions) {
      if(d != null) neighbours += d
    }
  }
}