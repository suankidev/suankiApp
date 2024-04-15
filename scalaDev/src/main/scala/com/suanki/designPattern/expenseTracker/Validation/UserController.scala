package com.suanki.designPattern.expenseTracker.Validation

class UserController(user: User) {

  def validateUser: Boolean = {
    println(s"checking user: ${user.name}")
    println("validation completed!")
    true
  }

}
