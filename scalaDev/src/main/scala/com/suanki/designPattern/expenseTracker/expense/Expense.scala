package com.suanki.designPattern.expenseTracker.expense

abstract class Expense {

  val money: Double
  val expenseType: String
  val description: String
  val expenseDescription: ExpenseDescription
  val expenseCalculator: ExpenseCalculator
}
