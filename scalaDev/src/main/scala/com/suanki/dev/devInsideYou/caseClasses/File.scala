package com.suanki.dev.devInsideYou.caseClasses

//https://stackoverflow.com/questions/46897540/why-i-have-to-return-some-in-unapply-method
object File {

  def apply(location: String, name: String, extension: String): String = s"""${location}/${name} ${extension}"""

  def unapply(path: String): Option[(String, String, String)] = {
    if (path == null || path.isEmpty) {
      None
    } else {
      // deconstruct the path
      val lastIndexOfSlash = path.lastIndexOf("/")
      val lastIndexOfDot   = path.lastIndexOf(".")
      val location         = path.substring(0, lastIndexOfSlash)
      val name             = path.substring(lastIndexOfSlash + 1, lastIndexOfDot)
      val extension        = path.substring((lastIndexOfDot + 1))
      Some(
        location,
        name,
        extension
      )
    }

  }

}

//unnapply can also return boolean

object FileIsMoreThan20Char {

  def unapply(path: String): Boolean = path.length > 20

}
