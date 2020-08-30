package board

case class Board(){
  val tiles:Map[(Char,Char),Tile] = BoardFactory.createTiles()

}
