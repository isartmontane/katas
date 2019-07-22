package xmasLights

case class Coordinates(x: Int, y: Int) {
  val maxRange = 999
  val minRange = 0
  validate(x, y)

  def validate(x: Int, y: Int): Unit = {
    if (x < minRange || y < minRange) {
      throw new Exception("Invalid Coordinate")
    }
    if (x > maxRange || y > maxRange) {
      throw new Exception("Invalid Coordinate")
    }
  }
}
