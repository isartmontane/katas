package xmasLights

import org.scalatest.FlatSpec

class CoordinatesSpec extends FlatSpec {

  "Coordinates" should "be between 0 and 999" in {
    assertThrows[Exception](Coordinates(-1, 0))
    assertThrows[Exception](Coordinates(0, -1))
    assertThrows[Exception](Coordinates(1000, 0))
    assertThrows[Exception](Coordinates(0, 1000))
    assertThrows[Exception](Coordinates(1000, 1000))
    Coordinates(0, 0)
    Coordinates(999, 0)
    Coordinates(0, 999)
    Coordinates(999, 999)
  }
}
