package com.suanki.dev.devInsideYou.messagePassingStyle

object MessagePassingStyleFour {

  def main(args: Array[String]): Unit = {
    println("=" * 50)
    code(args)
    println("=" * 50)
  }

  def code(args: Array[String]): Unit = {

    //    type bankAccount = Symbol => Any

    // initialBalance is private[this], so outside not accessible
    class BankAccount(initialBalance: Int) {

      private[this] var _balance: Int = initialBalance

      def withdraw(amount: Int): Unit = {
        if (_balance > amount) {
          _balance = _balance - amount
        } else
          sys.error("Insufficient fund")
      }

      def deposit(amount: Int): Unit = {
        if (amount >= 1) {
          _balance = _balance + amount
        } else
          sys.error("It's only possible to deposit positive")
      }

      def getBalance: Int = _balance
      //
//      def dispatch: Symbol => Any = operation => {
//        if (operation == Symbol("withdraw"))
//          withdraw
//        else if (operation == Symbol("deposit"))
//          deposit
//        else if (operation == Symbol("balance"))
//          getBalance
//        else
//          sys.error("invalid operation!")
//      }

    }

    val accountOne = new BankAccount(initialBalance = 100)

    accountOne.deposit(10)
    println(accountOne.getBalance)
    accountOne.withdraw(20)
    println(accountOne.getBalance)

    // what about multiple bank account

    val accountTwo   = new BankAccount(initialBalance = 100)
    val accountThree = new BankAccount(initialBalance = 1000)
    def makeTransfer(
        from: BankAccount,
        amount: Int,
        to: BankAccount
    ): Unit = {
      from.withdraw(amount)
      to.deposit(amount)
    }

    makeTransfer(accountTwo, 20, accountThree)

    println(
      accountTwo.getBalance
    )
    println(
      accountThree.getBalance
    )
  }

}
