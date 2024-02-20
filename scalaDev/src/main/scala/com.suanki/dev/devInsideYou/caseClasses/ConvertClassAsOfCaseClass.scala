package com.suanki.tutorials.devInsideYou.caseClasses

// default parameters are private[this] val name:String...
//but in case class it's val name:String....
//all case class i
final class Human(
    val name: String,
    val age: Int,
    val isMale: Boolean
) extends Product {
  def isFemale: Boolean = !isMale

  override def toString: String = s"Human($name, $age, $isMale)"

  override def equals(obj: Any): Boolean = obj match {
    case that: Human =>
      this.name == that.name && this.age == that.age && this.isMale == that.isMale
    case _ => false
  }

  override def hashCode(): Int =
    41 * (41 * (41 * this.name.hashCode) + this.name.hashCode) + this.isMale.hashCode

  def copy(
      name: String = this.name,
      age: Int = this.age,
      isMail: Boolean = this.isMale
  ): Human = new Human(name, age, isMale)

  override def productArity: Int = 3

  override def productElement(n: Int): Any = n match {
    case 0 => this.name
    case 1 => this.age
    case 2 => this.isMale
    case _ => throw new ArrayIndexOutOfBoundsException
  }

  override def canEqual(that: Any): Boolean = that.isInstanceOf[Human]

  override def productPrefix: String = Human.toString
}

//object Human extends Function3[String,Int,Boolean,Human]{
// type x = Int => Int
object Human extends ((String, Int, Boolean) => Human) {

  override def apply(
      name: String, // default private[this] val name
      age: Int,
      isMale: Boolean
  ): Human = new Human(name, age, isMale)

  override def toString: String = s"Human"

  type Answer[+A] = {
    def isEmpty: Boolean
    def get: A
  }

  def unapply(human: Human): Answer[(String, Int, Boolean)] = {
    if (human == null)
      NotReally
    else
      SureThing(
        human.name,
        human.age,
        human.isMale
      )
  }

}

final case class Person(name: String, age: Int, isMale: Boolean) {
  def isFemale: Boolean = !isMale

}

object ConvertClassAsOfCaseClass {

  def code(args: Array[String]): Unit = {

//person
    val person = Person("Bob", age = 27, isMale = true)
    println(person.isMale)
    println(person.age)
    println(person)
    println(Person)
    println(Person.isInstanceOf[Function3[_, _, _, _]]) // instance of 3
    val personFactory: (String, Int, Boolean) => Person = Person

    println(person == Person("Bob", age = 27, true))
    val personOne: Set[Person] = Set(
      person,
      Person("Bob", age = 27, isMale = true),
      Person("Bob", age = 28, isMale = true),
      Person("Bob", age = 29, isMale = true),
      Person("Bob", age = 30, isMale = true),
      Person("Bob", age = 31, isMale = true),
      Person("Bob", age = 32, isMale = true)
    )

    println(
      "contain=>" + personOne.contains(
        Person("Bob", age = 28, isMale = true)
      )
    )
    println(
      "contain=>" + personOne.contains(
        Person("Bob", age = 33, isMale = false)
      )
    )

    println(person.copy(name = "sujeet"))

    println(person.isInstanceOf[Product])
    println(person.productElement(0))
    println(person.productArity)
    println(person.productPrefix)
    println(person.productIterator.mkString(", "))

    person match {
      case Person(n, a, i) => println(s"Hello $n, $a, $i")
    }

    // human
    val prnt = "=" * 50
    println(s"${Console.MAGENTA}$prnt${Console.RESET}")

    //    val human = new Human("Bob",age=27, isMale = true)
    val human = Human("Bob", age = 27, isMale = true) // will with object
    println(human.isMale) // not accessible, as these are not field values
    println(human.age)
    println(human.name)
    println(human)
    println(Human)
    println(Human.isInstanceOf[Function3[_, _, _, _]])
    val humanFactory: (String, Int, Boolean) => Human = Human
    // println(humanFactory)
    println(human == Human("Bob", age = 27, true))

    val HumanSet: Set[Human] = Set(
      human,
      Human("Bob", age = 27, isMale = true),
      Human("Bob", age = 28, isMale = true),
      Human("Bob", age = 29, isMale = true),
      Human("Bob", age = 30, isMale = true),
      Human("Bob", age = 31, isMale = true),
      Human("Bob", age = 32, isMale = true)
    )

    println(
      "contain=>" + HumanSet.contains(
        Human("Bob", age = 28, isMale = true)
      )
    )
    println(
      "contain=>" + HumanSet.contains(
        Human("Bob", age = 33, isMale = false)
      )
    )

    println(human.copy(name = "sujeet"))

    println(human.isInstanceOf[Product])
    println(human.productElement(0))
    println(human.productArity)
    println(human.productPrefix)
    println(human.productIterator.mkString(", "))

    // either human should be case class or it should contain unapply method
    human match {
      case Human(n, a, i) => println(s"Hello:Pattern matching works: $n, $a, $i")
    }

    println()

    println(Set().getClass)
    println(Set(1).getClass)
    println(Set(1, 2).getClass)
    println(Set(1, 2, 3).getClass)
    println(Set(1, 2, 3, 4).getClass)
    println(Set(1, 2, 3, 4, 5).getClass)
    println(Set(1, 2, 3, 4, 5, 6).getClass)

  }
}
