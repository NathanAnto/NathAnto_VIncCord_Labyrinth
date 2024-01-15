package general

import java.awt.Color

class Resolution(maze: Maze, mazeDrawer: MazeDrawer) {
  private def reconstructPath(startCell: Cell, endCell: Cell): Unit = {
    var cellsToEnd: IndexedSeq[Cell] = IndexedSeq()
    var nextParent: Cell = endCell

    while(true) {
      // add to start of list
      cellsToEnd = nextParent +: cellsToEnd

      nextParent = nextParent.parent

      if(nextParent == startCell) {
        for(c <- cellsToEnd) {
          Thread.sleep(50)
          mazeDrawer.drawCells(Array(c.parent, c), Color.magenta)
        }
        return
      }
    }
  }

  def aStar(startCell: Cell, endCell: Cell): Unit = {
    var openList: Set[Cell] = Set(startCell)
    var closedList: Set[Cell] = Set()

    openList += startCell
    var current = startCell

    while (openList.nonEmpty) {
      openList -= current
      // If current is end cell retrace path (END)
      if(current == endCell) {
        reconstructPath(startCell, endCell)
        return
      }

      // Add current cell to closed
      closedList += current
      mazeDrawer.drawCell(current, Color.cyan)

      Thread.sleep(50)
      // Find all current cells neighbours and add them to openList
      for(n <- current.neighbours) {
        // Calculate all the open neighbour parameters (g, h, f)
        if(!closedList.contains(n) && maze.getUsablePassages.contains(maze.getUsablePassage(current, n))) {
          n.g = current.g + 1
          n.h = math.abs(n.x - endCell.x) + math.abs(n.y - endCell.y)
          n.f = n.g + n.h

          // Set their parents to current
          n.parent = current

          // Add the to openList
          openList += n
          mazeDrawer.drawCells(Array(current, n), Color.ORANGE)
        }
      }

      var lowest: Cell = openList.toIndexedSeq(0)

      // Get cell with lowest f and set it as the new current
      for(c <- openList) {
        if(c.f < lowest.f)
          lowest = c

        // If two have the same f choose one with lowest h
        else if(c.f == lowest.f) {
          if(c.h < lowest.h) lowest = c
        }
      }
      current = lowest
    }
  }
}
