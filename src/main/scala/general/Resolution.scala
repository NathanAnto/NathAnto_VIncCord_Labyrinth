package general

import java.awt.Color
import scala.collection.mutable

class Resolution(mazeDrawer: MazeDrawer) {

  private def reconstruct_path(startCell: Cell, endCell: Cell): Unit = {
    var nextParent: Cell = endCell
    while(true) {
      mazeDrawer.drawCells(nextParent, nextParent.parent, Color.magenta)
      nextParent = nextParent.parent
      Thread.sleep(100)
      if(nextParent == startCell) return
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
        reconstruct_path(startCell, endCell)
        return
      }

      // Add current cell to closed
      closedList += current
      mazeDrawer.drawCell(current, Color.cyan)

      Thread.sleep(100)
//      Thread.sleep(500)
      println(current.usableNeighbours)
      // Find all current cells neighbours and add them to openList
      for(n <- current.usableNeighbours) {
        // Calculate all the open neighbour parameters (g, h, f)
        if(!closedList.contains(n)) {
          n.g = current.g + 1
          n.h = math.abs(n.x - endCell.x) + math.abs(n.y - endCell.y)
          n.f = n.g + n.h

          // Set their parents to current
          n.parent = current

          // Add the to openList
          openList += n
          mazeDrawer.drawCells(current, n, Color.ORANGE)
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
      // Restart
    }
  }
}
