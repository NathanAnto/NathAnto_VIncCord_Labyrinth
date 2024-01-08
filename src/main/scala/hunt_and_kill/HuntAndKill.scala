package hunt_and_kill

import general.{Maze, MazeDrawer}

class HuntAndKill {
  val MAZEDIMENSIONS = 10
  var maze: Maze = _
  var mazeDrawer: MazeDrawer = _

  maze = new Maze
  maze.create(MAZEDIMENSIONS)
  mazeDrawer = new MazeDrawer(500, "Hunt and Kill", MAZEDIMENSIONS)
}
