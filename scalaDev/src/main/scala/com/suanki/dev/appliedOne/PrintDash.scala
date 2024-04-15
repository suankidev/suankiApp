package com.suanki.dev.appliedOne

trait PrintDash {

  val line       = "=" * 50
  val prntLine   = s"${Console.GREEN} $line ${Console.RESET}"
  lazy val start = println(prntLine)
  lazy val end   = println(prntLine)

}
