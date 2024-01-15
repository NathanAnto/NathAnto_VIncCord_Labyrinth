package general

import scala.util.Random

class Cell(val x: Int, val y: Int) {
  var neighbours: Set[Cell] = Set()

  // A* parameters
  var g: Int = 0
  var h: Int = 0
  var f: Int = 0
  var parent: Cell = _

  override def toString: String = s"Cell($x,$y) [$g, $h, $f]"

  def findNeighbours(maze: Maze): Unit = {
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
}