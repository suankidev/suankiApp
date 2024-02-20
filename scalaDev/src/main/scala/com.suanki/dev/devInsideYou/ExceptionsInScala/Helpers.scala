package com.suanki.dev.devInsideYou.ExceptionsInScala

object Helpers {

  def showGreen(text: String) = {
    styled(text, Console.GREEN)
  }

  def showYellow(text: String) = {
    styled(text, Console.YELLOW)
  }

  def showRed(text: String) = {
    styled(text, Console.RED)
  }

  def styled(str: String, value: String) = {

    println(s"${value} $str ${Console.RESET}")
  }

}
