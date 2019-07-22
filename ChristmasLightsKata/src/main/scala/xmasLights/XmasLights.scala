package xmasLights

class XmasLights(sizeX: Int = 1000, sizeY: Int = 1000) {
  val lightsStatus = Array.fill[LightStatus](sizeX, sizeY)(LightStatus())

  def printLights(): Unit = {
    lightsStatus.foreach(x => {
      x.foreach(y => y.printMe())
      println()
    })
  }

  def countLights(status: LightStatus): Int = {
    lightsStatus
      .map(x => x.count(y => y.sameStatus(status)))
      .sum
  }

  def changeLightStatus(status: LightStatus, coords: Coordinates): Unit = {
    lightsStatus(coords.x)(coords.y) = status
  }

  def changeLightsStatus(status: LightStatus,
                         coords1: Coordinates,
                         coords2: Coordinates): Unit = {
    for (x <- coords1.x to coords2.x) {
      for (y <- coords1.y to coords2.y) {
        changeLightStatus(status, Coordinates(x, y))
      }
    }
  }

  def toggleLight(coords: Coordinates): Unit = {
    val newStatus = lightsStatus(coords.x)(coords.y).toggle()
    changeLightStatus(newStatus, coords)
  }

  def toggleLights(coords1: Coordinates, coords2: Coordinates): Unit = {
    for (x <- coords1.x to coords2.x) {
      for (y <- coords1.y to coords2.y) {
        toggleLight(Coordinates(x, y))
      }
    }
  }
}
