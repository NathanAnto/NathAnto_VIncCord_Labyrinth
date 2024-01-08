package dfs

class DFSMaze extends general.Maze {

  override def create(d: Int) {
    dimensions = d
    super.createCells()
  }
}
