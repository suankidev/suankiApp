package com.suanki.designPattern.expenseTracker.Validation

class User(val name: String) {
  val userController: UserController = new UserController(this)
  userController.validateUser
}
