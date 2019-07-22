package xmasLights

import org.scalatest.{BeforeAndAfterEach, FlatSpec}

class XmasLightsSpec extends FlatSpec with BeforeAndAfterEach {
  var xmas: XmasLights = _
  val lightOff = LightStatus()
  val lightOn = LightStatus(true)
  val boardSizeX = 10
  val boardSizeY = 10
  val boardNumLights = boardSizeX * boardSizeY

  override def beforeEach() {
    xmas = new XmasLights(boardSizeX, boardSizeY)
  }

  "a XmasLights" should "return all the lights OFF by default" in {
    val numLightsOn = xmas.countLights(lightOn)
    assert(numLightsOn == 0)
  }

  "a XmasLights" should "allow to turn on one light" in {
    val coords = Coordinates(0, 0)
    xmas.changeLightStatus(lightOn, coords)
    assert(xmas.countLights(lightOn) == 1)
  }

  "a XmasLights" should "allow to turn off one light" in {
    val coords = Coordinates(0, 0)
    xmas.changeLightStatus(lightOn, coords)
    assert(xmas.countLights(lightOn) == 1)
    xmas.changeLightStatus(lightOff, coords)
    assert(xmas.countLights(lightOn) == 0)
  }

  "a XmasLights" should "allow to toggle one light" in {
    assert(xmas.countLights(lightOff) == boardNumLights)
    assert(xmas.countLights(lightOn) == 0)
    val coords = Coordinates(0, 0)
    xmas.toggleLight(coords)
    assert(xmas.countLights(lightOn) == 1)
  }

  "a XmasLights" should "allow to turn on a range of lights" in {
    assert(xmas.countLights(lightOn) == 0)
    val coords1 = Coordinates(0, 0)
    val coords2 = Coordinates(2, 2)
    xmas.changeLightsStatus(lightOn, coords1, coords2)
    assert(xmas.countLights(lightOn) == 9)
  }

  "a XmasLights" should "allow to turn off a range of lights" in {
    assert(xmas.countLights(lightOn) == 0)
    assert(xmas.countLights(lightOff) == boardNumLights)
    val coords1 = Coordinates(0, 0)
    val coords2 = Coordinates(2, 2)
    xmas.changeLightsStatus(lightOn, coords1, coords2)
    assert(xmas.countLights(lightOn) == 9)
    xmas.changeLightsStatus(lightOff, coords1, coords2)
    assert(xmas.countLights(lightOn) == 0)
    assert(xmas.countLights(lightOff) == boardNumLights)
  }

  "a XmasLights" should "allow to toggle a range of lights" in {
    assert(xmas.countLights(lightOn) == 0)
    assert(xmas.countLights(lightOff) == boardNumLights)
    val coords1 = Coordinates(0, 0)
    val coords2 = Coordinates(2, 2)
    xmas.toggleLights(coords1, coords2)
    assert(xmas.countLights(lightOn) == 9)
  }

  "a XmasLights" should "defeat your neighbours!" in {
    // turn on 0,0 through 999,999 would turn on (or leave on) every light.
    // toggle 0,0 through 999,0 would toggle the first line of 1000 lights,
    //     turning off the ones that were on, and turning on the ones that were off.
    // turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
    val myXmas = new XmasLights(1000, 1000)
    myXmas.changeLightsStatus(lightOn, Coordinates(0, 0), Coordinates(999, 999))
    myXmas.toggleLights(Coordinates(0, 0), Coordinates(999, 0))
    myXmas.changeLightsStatus(
      lightOff,
      Coordinates(499, 499),
      Coordinates(500, 500)
    )
//    myXmas.printLights()
    println(myXmas.countLights(lightOn))
  }
}
