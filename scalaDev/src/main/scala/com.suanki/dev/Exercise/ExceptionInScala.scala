package com.suanki.dev.Exercise

object ExceptionInScala {

  /**   1. crash your program with OutOfMemory Error 2. crash with stackOverflowError 3. PocketCalculator --add(x,y)
    *      --subtract(x,y) --multiply(x,y) --divide(x,y)
    *
    * Throw --overflowWxception when add excede Int.MAX_VALUE --underlowExceptionif sub excede Int.MIN_VALUE
    * -MAtchCaluationException or division by 0
    */

  def main(args: Array[String]): Unit = {
    val heapSize: Long     = Runtime.getRuntime.totalMemory()
    val heapMaxSize: Long  = Runtime.getRuntime.maxMemory()
    val heapFreeSize: Long = Runtime.getRuntime.freeMemory()

    println(heapMaxSize)
    println(heapSize)
    println(heapFreeSize)

    // outofmem
    //    val arr = Array.ofDim(Int.MaxValue)

    // stackoverflow
    //    def incre:Int = 1 + incre

    class OverflowException  extends RuntimeException
    class UnderFlowException extends RuntimeException
    class PocketCalculator {

      def add(x: Int, y: Int) = {

        val result = x + y
        if (x > 0 && y > 0 && result < 0) throw new OverflowException
        else if (x < 0 && y < 0 && result > 0) throw new UnderFlowException
      }

    }
  }

}
