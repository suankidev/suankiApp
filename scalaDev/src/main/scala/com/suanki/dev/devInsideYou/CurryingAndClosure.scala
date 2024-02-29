package com.suanki.dev.devInsideYou

object CurryingAndClosure extends App {

  println("=" * 100)

  // function literal are closure
  // x is free variable
  // y is bound variable
  // openTerm addToX method is openTerm b/c its using free variables
  var x = 1337

  def addToX(y: Int): Int =
    x + y // function depends on outside variable is called closure
  def addToX1: Int => Int = y => x + y

  // calling
  println(addToX(y = 10))
  println(addToX1(10))

  // var freeVariable:Int = 1337
  // produces another function as result call function currying
  def closedTerm(freeVariable: Int): Int => Int =
    boundVariable => freeVariable + boundVariable // anonymous function

  def closedTermOne(freeVariable: Int)(boundVariable: Int): Int =
    freeVariable + boundVariable

  def UncurriedclosedTermOne(freeVariable: Int, boundVariable: Int): Int =
    freeVariable + boundVariable

  println(closedTerm(freeVariable = 7)(8))
  println(UncurriedclosedTermOne(freeVariable = 7, boundVariable = 8))
  println(closedTermOne(freeVariable = 7)(8))

  // more maintainable
  val partialComputation = closedTerm(freeVariable = 1337)

  println(partialComputation(20))

  def code(args: Array[String]): Unit = {

    println(addToX(10)(20))
    addToy(2)(40)
    println("=" * 30)

    def addToX(x: Int): Int => Int = {
      val y: Int => Int = y => x + y
      y
    }

    def addToy(x: Int)(y: Int): Unit = {
      1 to x foreach (_ => println(y))
    }

    def f(g: Int => String): Unit = {
      // g(x)
      println(g(45454))
    }

    def g(x: Int): String = s"${Console.MAGENTA} x ${Console.RESET}"

    f(g)

  }

  // practical example
  def code2(): Unit = {

    // replace spark/dataframe  with string type not to trigger spark session

    //      //createTableDataFrame(buildQuery,dffromQuery)
    //  //            ==> buildQuery(buildSelect,buldWhere)
    //  //                     ==> buildSelect
    //  //                          ==>buildeWhere(fetchPartcolumn)
    //
    def buildSelect(tableName: String) = s"select * from $tableName"

    def buildWhere(
        tableName: String,
        spark: String,
        f: (String) => List[String]
    ) = {
      var where = "1 = 1 AND "
      f(tableName) foreach (
        _ match {
          case "cobDate" => where = s"AND cobDate='2023-12-30'"
        }
      )

    }

    def getPartition(tableName: String) = List("date", "id")

    def buildQuery(
        tableName: String,
        spark: String,
        fun: String => List[String]
    ) =
      s"${buildSelect(tableName)} where ${buildWhere(tableName, spark, fun)}"

    def createDataFrames(
        tableName: String,
        spark: String,
        f: (String) => String,
        tableType: String
    ): String = {

      getDf(buildQuery(tableName, spark, getPartition))

    }

    def getDf(query: String): String = query

    val stgDF =
      createDataFrames("table_stg", "spark", getDf, "tableTypeStg")
    val finalDF = createDataFrames("table", "spark", getDf, "tableTypeStg")

  }

  println("=" * 100)

}
