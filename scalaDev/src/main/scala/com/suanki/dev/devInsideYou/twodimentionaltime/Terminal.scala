package com.suanki.dev.devInsideYou.twodimentionaltime

object Terminal {

  def clearCanvas: Unit = print("\u001b[2J")

  def goUp(linesToUp: Int): Unit = {
    print(s"\u001b[${linesToUp}A")
  }
}
