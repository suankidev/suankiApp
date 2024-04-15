package com.suanki.designPattern.expenseTracker

import com.suanki.designPattern.expenseTracker.Validation.User
import com.suanki.designPattern.expenseTracker.expense._

import java.sql.Connection

trait Writer[T] {

  def writeIntoTable(query: String): T

}
class LogExpenses(user: User, expense: ExpenseDetails) extends Writer[LogExpenses] {

  def log(): Unit =
    println(s"""
               |money spent: ${expense.money}  
               |spentType: ${expense.expenseDescription.expenseType} 
               |spent Description: ${expense.expenseDescription.description}
               |spent Date: ${java.time.LocalDateTime.now()}""".stripMargin)

  override def writeIntoTable(query: String): LogExpenses = {

    val jdbcConnection: Connection = com.suanki.jdbcReader.JdbcReader.getJdbcConnection
    val stmt                       = jdbcConnection.createStatement()

  }

}
