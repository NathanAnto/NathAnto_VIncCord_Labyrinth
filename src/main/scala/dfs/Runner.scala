package dfs

import general.{Cell, MazeDrawer, Direction}

import scala.collection.mutable
import scala.util.Random

class Runner {
  val MAZEDIMENSIONS = 10
  val mazeDrawer = new MazeDrawer(500, "DFS", MAZEDIMENSIONS)

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
  backtracker(Cell(
    Random.nextInt(MAZEDIMENSIONS),
    Random.nextInt(MAZEDIMENSIONS)
  ), visitedCells)
}
