package board

import scala.annotation.tailrec

object BoardFactory {


  @tailrec
  def createTiles(): Map[(Char, Char), Tile] = {
    println("Provide starting point for a Carrier(5 tiles) and the direction(left,right,up,down)")
    val carrier = getShip(Carrier)
    println("Provide starting point for a BattleShip(4 tiles) and the direction(left,right,up,down)")
    val battleShip = getShip(BattleShip)
    println("Provide starting point for a cruiser(3 tiles) and the direction(left,right,up,down)")
    val cruiser = getShip(Cruiser)
    println("Provide starting point for a submarine(3 tiles) and the direction(left,right,up,down)")
    val submarine = getShip(Submarine)
    println("Provide starting point for a destroyer(2 tiles) and the direction(left,right,up,down)")
    val destroyer = getShip(Destroyer)
    val result = for {
      a <- carrier
      b <- battleShip
      c <- cruiser
      d <- submarine
      e <- destroyer
    } yield a ++ b ++ c ++ d ++ e
    if (result.isEmpty) {
      println("Some data provided by you was incorrect, try again")
      createTiles()
    }
    else result.get

  }

  private def getCoordinates(row: String, column: String): Option[(Char, Char)] =
    if (row.length != 1 || column.length != 1) None
    else {
      val coordinates = Some((row.charAt(0), column.charAt(0)))
      validateCoordsAreInBound(coordinates)
    }


  private def validateCoordsAreInBound(coordinates: Option[(Char, Char)]) = {
    coordinates
      .filter(t => t._1 >= 'A' && t._1 <= 'J')
      .filter(t => t._2 >= '0' && t._2 <= '9')
  }

  private def getShipMap(maybeCoord: Option[(Char, Char)], direction: String, shipType: TileType): Option[Map[(Char, Char), Tile]] = {
    val coordDifference = getCoordDifference(direction)
    if(coordDifference == null) None
    val sequence = (1 until shipType.size)
      .map(i => maybeCoord.map(t => ((t._1 + (i * coordDifference._1)).asInstanceOf[Char], (t._2 + (i * coordDifference._2)).asInstanceOf[Char])))
      .map(validateCoordsAreInBound)
      .map(o => o.flatMap(t => Some(t -> new Tile(shipType))))
    val z: Option[Map[(Char, Char), Tile]] = Some(Map.empty[(Char, Char), Tile])
    sequence.foldLeft(z)((acc, elem) => acc.flatMap(map => elem.map(e => map + e)))


  }

  private def getCoordDifference(direction: String) = {
    direction match {
      case "UP" => (1, 0)
      case "DOWN" => (-1, 0)
      case "LEFT" => (-1, 0)
      case "RIGHT" => (1, 0)
      case _ => null
    }
  }

  private def getShip(shipType: TileType): Option[Map[(Char, Char), Tile]] = {
    val info = scala.io.StdIn.readLine.split(" ")
    if (info.length != 3) None
    val row = info(0)
    val column = info(1)
    val direction = info(2)
    val coordinates: Option[(Char, Char)] = getCoordinates(row, column)
    getShipMap(coordinates: Option[(Char, Char)], direction, shipType)

  }


}
