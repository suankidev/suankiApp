package com.suanki.dev.devInsideYou.messagePassingStyle

object MessagePassingStyleThree {

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {

//    type bankAccount = Symbol => Any

    class BankAccount(initialBalance: Int) {

      var balance: Int = initialBalance

      val withdraw: Int => Unit = amount => {
        if (balance > amount) {
          balance = balance - amount
        } else
          sys.error("Insufficient fund")
      }

      val deposit: Int => Unit = amount => {
        if (amount >= 1) {
          balance = balance + amount
        } else
          sys.error("It's only possible to deposit positive")
      }

      def getBalance: () => Int = () => balance

      def dispatch: Symbol => Any = operation => {
        if (operation == Symbol("withdraw"))
          withdraw
        else if (operation == Symbol("deposit"))
          deposit
        else if (operation == Symbol("balance"))
          getBalance
        else
          sys.error("invalid operation!")
      }

    }

    val accountOne = new BankAccount(initialBalance = 100)
    val deposit1 =
      accountOne.dispatch(Symbol("deposit")).asInstanceOf[Int => Unit]
    val withdraw1 =
      accountOne.dispatch(Symbol("withdraw")).asInstanceOf[Int => Unit]
    val getBalance1 =
      accountOne.dispatch(Symbol("balance")).asInstanceOf[() => Int]

    deposit1(10)
    println(getBalance1())
    withdraw1(20)
    println(getBalance1())

    // what about multiple bank account

    val accountTwo   = new BankAccount(initialBalance = 100)
    val accountThree = new BankAccount(initialBalance = 1000)
    def makeTransfer(
        from: BankAccount,
        amount: Int,
        to: BankAccount
    ): Unit = {
      from.dispatch(Symbol("withdraw")).asInstanceOf[Int => Unit](amount)
      to.dispatch(Symbol("deposit")).asInstanceOf[Int => Unit](amount)
    }

    makeTransfer(accountTwo, 20, accountThree)

    println(
      accountTwo.dispatch(Symbol("balance")).asInstanceOf[() => Int]()
    )
    println(
      accountThree.dispatch(Symbol("balance")).asInstanceOf[() => Int]()
    )
  }

}
