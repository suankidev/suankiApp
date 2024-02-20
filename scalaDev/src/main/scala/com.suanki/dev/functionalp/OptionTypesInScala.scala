package com.suanki.tutorials.functionalp

object OptionTypesInScala {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int]      = None

  println(myFirstOption)

  // work with unsafe Apis
  def unSafeMEthod(): String = null

  //  val result = Some(null) //WRONG
  val result = Option(unSafeMEthod()) // some or None

  // chained methods
  def backupMethod(): String = "A valid result"

  val chaindResult = Option(unSafeMEthod()).orElse(Option(backupMethod()))

  // design unsafe apis

  def betterUnsafeMethod: Option[String]   = None
  def betterBackupMethod(): Option[String] = Some("a valid result")

  val betterResult = betterUnsafeMethod orElse betterUnsafeMethod

}
