package com.suanki.dev.devInsideYou.twodimentionaltime
final case class TwoDimensionalString(
    top: String,
    middle: String,
    bottom: String
) {

  require(
    requirement = doAllRowsHaveSameLength,
    message = "All rows must have the same length"
  )

  private[this] def doAllRowsHaveSameLength: Boolean =
    top.length == middle.length && middle.length == bottom.length

  def +(that: TwoDimensionalString): TwoDimensionalString = {

    TwoDimensionalString(
      top = this.top + that.top,
      middle = this.middle + that.middle,
      bottom = this.bottom + that.bottom
    )
  }

  def show(): Unit = {

    println(rendered)
  }

  private[this] def rendered: String = {
    s"$top\n$middle\n$bottom"
  }

}

object TwoDimensionalString {
  val QuestionMarks: TwoDimensionalString =
    TwoDimensionalString(
      top = "   ",
      middle = " ? ",
      bottom = " ? "
    )

  val Empty: TwoDimensionalString =
    TwoDimensionalString(
      top = "",
      middle = "",
      bottom = ""
    )
}

object SevenSegmentsClock extends App {

  {

    println("-" * 50)

    TwoDimensionalTime.showCurrent(pattern = "HH:mm:ss")
    println("-" * 50)

  }
}
