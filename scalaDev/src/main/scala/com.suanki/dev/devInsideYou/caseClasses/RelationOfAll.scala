package com.suanki.tutorials.devInsideYou.caseClasses

object RelationOfAll {

  def code: Unit = {

    val f3: (String, Int, Boolean) => Unit =
      (string, int, boolean) => {
        println(int)
      }

    val f2: ((String, Int, Boolean)) => Unit =
      f3.tupled

    val person: Person = Person("skuma", age = 5, isMale = true)

    f2(
      Person
        .unapply(person) // Option[(String,Int,Boolean)]
        .get
    )
  }
}
