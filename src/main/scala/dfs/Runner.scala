package dfs

import general.{Cell, MazeDrawer, Direction}

import scala.collection.mutable
import scala.util.Random

class Runner extends Runnable {
  val MAZEDIMENSIONS = 10
  var mazeDrawer: MazeDrawer = _

  override def run(): Unit = {
    mazeDrawer = new MazeDrawer(500, "DFS", MAZEDIMENSIONS)
    backtracker(Cell(
      Random.nextInt(MAZEDIMENSIONS),
      Random.nextInt(MAZEDIMENSIONS)
    ), visited = mutable.Set())
  }

  /**
   * DFS Algorithm backtracker
   * @param location location of next [[Cell]]
   * @param visited [[Set]] of visited cells
   */
  private def backtracker(location: Cell, visited: mutable.Set[Cell]): Unit = {
    visited += location;

    val neighbours: Array[Cell] = new Array(4)

    // Randomize neighbour order
    val indices = generateSequence(neighbours.length)
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
//        println(s"Found neighbour: $pos")

        // TODO: Find a way to get a non graphical result

        if(pos.x < location.x)
          mazeDrawer.drawCell(pos, Direction.LEFT)
        else if (pos.x > location.x)
          mazeDrawer.drawCell(location, Direction.RIGHT)
        else if (pos.y < location.y)
          mazeDrawer.drawCell(pos, Direction.UP)
        else if (pos.y > location.y)
          mazeDrawer.drawCell(location, Direction.DOWN)

        backtracker(pos, visited)
      }
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