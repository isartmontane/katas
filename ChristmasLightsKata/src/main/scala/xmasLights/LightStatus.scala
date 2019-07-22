package xmasLights

case class LightStatus(value: Boolean = false) {
  def sameStatus(status: LightStatus): Boolean = {
    status.value == value
  }

  def toggle(): LightStatus = {
    LightStatus(!value)
  }

  def printMe(): Unit = {
    if (value) print("O")
    else print("X")
  }
}
