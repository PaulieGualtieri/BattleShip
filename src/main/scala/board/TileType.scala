package board

sealed trait TileType {
  val size:Int
}
case object Carrier extends TileType {
  override val size: Int = 5
}
case object BattleShip extends TileType {
  override val size: Int = 4
}
case object Cruiser extends TileType {
  override val size: Int = 3
}
case object Submarine extends TileType {
  override val size: Int = 3
}
case object Destroyer extends TileType {
  override val size: Int = 2
}

