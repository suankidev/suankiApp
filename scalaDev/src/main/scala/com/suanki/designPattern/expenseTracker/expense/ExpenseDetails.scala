package com.suanki.designPattern.expenseTracker.expense

case class ExpenseDetails(money: Double, expenseType: String, description: String) extends Expense {

  val expenseDescription: ExpenseDescription =
    new ExpenseDescription(expenseType, description)

  val expenseCalculator: ExpenseCalculator = new ExpenseCalculator()
}
