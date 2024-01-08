package kruskal

import general.{Cell, Passage}

import scala.util.Random

class KruskalMaze extends general.Maze {
  private var passages: Set[Passage] = Set()
  var connections: Set[Set[Cell]] = Set()
  override def create(d: Int) {
    dimensions = d
    super.createCells()
    createPassages()
  }

  def createPassages() = {
    // Get all passages and cell neighbours
    for (cell <- cells; n <- cell.neighbours) {
      connections += Set(cell)
      cell.getNeighbours(this)
      if ((!passages.contains(Passage(cell, n)) || !passages.contains(Passage(n, cell))) && n != null) {
        passages += Passage(cell, n)
      }
    }
  }

  def getRandomPassage: Passage = {
    if(passages.size <= 1) return passages.head
    val num = Random.nextInt(passages.size)
    val p = passages.toIndexedSeq(num)
    passages -= p
    p
  }

  def removePassage(cell1: Cell, cell2: Cell) = {
    passages -= Passage(cell1, cell2)
    passages -= Passage(cell2, cell1)
  }
}
