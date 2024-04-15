package com.suanki.designPattern.expenseTracker

import com.suanki.designPattern.expenseTracker.Validation.User
import com.suanki.designPattern.expenseTracker.expense._

object Main {

  def main(args: Array[String]): Unit = {

    println("=" * 50)

    val user: User = new User("R kumar")

    val expense: ExpenseDetails =
      ExpenseDetails(144.30, ExpenseType.FOOD, "vegetables: lady finger, spinach , Methi")

    val logExpenses: LogExpenses = new LogExpenses(user, expense)
    logExpenses.log()

    println("=" * 50)
  }

}
