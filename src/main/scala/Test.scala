import board.BoardFactory

object Test extends App {
  val tiles = BoardFactory.createTiles()
  println(tiles.size)
}
