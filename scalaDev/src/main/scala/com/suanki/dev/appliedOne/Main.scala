package com.suanki.dev.appliedOne

import scala.io.Source

object Main extends App {

  println("=" * 40)

  // 1. Quick collection info
//  QuickCollectionInfo.code

  // 2. -> extension method
//  ExtensionMethod.code

  // 3. open and read a file
//  FileReading.code

  // class and object
//  val mobile = new Mobile("Ver_1.1")
//  println(mobile.getPrice(6))
//  println(mobile.name)

  // returning this instead unit
//  new StatementVsExpression

  // funcitona dn closure
//  FunctionAndClosure.code

  AnEnd.code
  println("=" * 50)

}

//scala rewrite below
class MobileOne(val version: String) {}

//rewrite by scala
class MobileOneRewrittenByScalaCompiler(val _version: String) {
  val version: String = _version
}

class Mobile(version: String) {

  val name: String  = "Nokia 6.1 Plus"
  val price: Double = 12313.25

  def getPrice(tax: Double) = price + (price * tax / 100)
}

object FileReading {

  def code: Unit = {

    for (
      line <- Source
        .fromFile(
          "C:\\Users\\sujee\\OneDrive\\Documents\\bigdata_and_hadoop\\suankiApp\\scalaDev\\src\\main\\scala\\com\\suanki\\dev\\appliedOne\\Main.scala"
        )
        .getLines
    ) {
      println(line)
    }
  }
}

object ExtensionMethod {

  def code: Unit = {
    // ->  can be called an instance of any type with one parameter of any other type
    // the result is a tuple2[firstType, Secondtype] with the values of noth instance
    // it's mainy syntatic sugar for creating maps, but it's not a keyword

    1 -> "One"
    // is rewritten
    1.->("One")
    // is expanded to
    ArrowAssoc(1).->("One") // comming from implicit

    val a = 1 -> "One" // giving you a tuple2, since -> method doesn't exist so expanded and give the tuple2
    println(a)
    // simple Map iteration
    println("===map iteration======")
    val mapToRices = Map(
      1 -> "steal underpants",
      2 -> "???",
      3 -> "profit"
    )

    for ((step, instruction) <- mapToRices) {
      println(step, instruction)
    }

  }
}

object QuickCollectionInfo {

  def code: Unit = {

    val arry1: Array[Int]   = Array(1, 2, 4)
    val list1: List[String] = List("scooby", "ddoby", "doo")

    // the type parameter is not optional, but can be infeered from the initiliazation content
    val array2 = Array(1, 2, 4)

    // when specifying a collection type in a method parameter , the type parameter must be provided

    def sequareRoot(xs: List[Int]): List[Double] = {
      for (x <- xs)
        yield math.sqrt(x)
    }

    val rootList = List(2, 4, 6)
//    println(sequareRoot(rootList))

    // List initialization:
    val emptyList = Nil: scala.collection.immutable.Nil.type
    println(emptyList.length)

    // using cons form
    val listb = Nil.::(1).::(2)
    val listc = 4 :: 5 :: 6 :: Nil // without Nill at the end won't work, Nil represent empty list
    // :: is right associative , that is it applieds the parameter on the left side to the iterm on the right

    println(listb)
    println(listc)

    // concatenate :::

    val listd = listb ::: listc

    println(listd)

    println("=====Sequence====")

    // list and Array are both seuqence in scala , subtypes of seq

    val t            = Seq(1, 2, 3)
    val l1: Seq[Int] = rootList
    val l2: Seq[Int] = array2
    val l3: Seq[Int] = Vector(1, 2, 3, 5)
    println(l1)
    println(l2) // wrapped array
    println(array2)
    println(l3)

    // A seq is an orderd collection of homogenous values theat may be repeated
    // a set is an unordered collection of homogenous that are unique

    println(s"======Set======")
    val set1 = Set(1, 2, 3, 4, 4) // set is not subtype of seq
    println(set1)

    println("====mutability====")
    // array are mutable
    val a1 = Array(1, 2, 4)
    println(a1.mkString(","))
    a1(0) = 10
    println(a1.mkString(","))

    // by default get assigned null
    val a2 = new Array[Int](3)
    val a3 = new Array[String](3)
    println(a2.mkString(","))
    println(a3.mkString(","))

    // list and vector are not mutable

    val aList  = List(2, 4, 8)
    val aList1 = List(2, 4, 8)

    println(0 :: aList)      // producing new list
    println(aList :: aList1) // new list but to concatenate use ::: b/c :: is right associative
    println(aList ::: aList1)

    println("=== set has both mutable and immutable =======")

    val setOne = scala.collection.mutable.Set(1, 2, 3, 4)
    val setTwo = scala.collection.immutable.Set(1, 2, 3, 4)

    setOne += 5 // work b/c mutable and has += operator

    println(setOne)
    println(setOne - 2) // setOne -= 2

    println("====Map are mutable and immutable=")

    val mapOne = scala.collection.mutable.Map("a" -> 1, "b" -> 2)

    mapOne += ("c" -> 2)

    println(mapOne)
  }

}
