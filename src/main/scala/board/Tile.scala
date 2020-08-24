package board

 class Tile(val tileType: TileType, val wasAttacked: Boolean) {
  def this(tileType: TileType) = this(tileType, false)
}
