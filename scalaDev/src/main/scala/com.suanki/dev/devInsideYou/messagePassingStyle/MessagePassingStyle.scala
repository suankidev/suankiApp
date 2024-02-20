package com.suanki.dev.devInsideYou.messagePassingStyle

object MessagePassingStyle {

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {

    def bankAccount(initialBalance: Int): Symbol => (Int => Int) = {

      var balance: Int = initialBalance

      val withdraw: Int => Int = amount => {
        if (balance > amount) {
          balance = balance - amount
          balance
        } else
          sys.error("Insufficient fund")
      }

      val deposit: Int => Int = amount => {
        if (amount >= 1) {
          balance = balance + amount
          balance
        } else
          sys.error("It's only possible to deposit positive")
      }

      def dispatch: Symbol => (Int => Int) = operation => {
        if (operation == Symbol("withdraw"))
          withdraw
        else if (operation == Symbol("deposit"))
          deposit
        else
          sys.error("invalid operation!")
      }

      dispatch

    }

    val accountOne = bankAccount(initialBalance = 100)

    println(accountOne(Symbol("deposit"))(10))
    println(accountOne(Symbol("withdraw"))(20))

  }

}
