//package com.suanki.com.suanki.dev.oops
//
//object GenericsInScala extends App {
//
//  class MyList[A] {
//    //use the type A
//    def add[B >: A](element: A): MyList[B] = ???
//
//    def apply()=List(1,2,3,5)
//  }
//
//  class MyMap[key, value] {
//  }
//
//  val listOfIntegers = new MyList[Int]
//  val listOfStrings = new MyList[String]
//  val mapOfString = new MyMap[String, String]
//
//  //generic method
//  object MyList {
//    def empty[A]: MyList[Int] = new MyList[Int]
//    def test[A](name:String) = s"this is test method!"
//  }
//
//  val emptyListOfInteger = MyList.empty[Int]
////  val somTest = MyList.test[String]("test string")
//
//  //Variance problem
//  class Animal
//  class Cat extends Animal
//  class Dog extends Animal
//
//  /**
//   * List[Cat] extends List[Animal] = COVARIANCE
//   */
//
//  class CovariantList[+A]
//  val animal:Animal = new Cat
//  //1.
//  val animalList: CovariantList[Animal] = new CovariantList[Cat]
//
//  //2. NO = INVARIANCE
//  class InvariantList[A]
//  val invariantAnimal:InvariantList[Animal] = new InvariantList[Cat]
//
//
//  //3. Hell, no! CONTRAVARIANCE
//  class ContravariantList[-A]
//  //or
//  class Trainer[A]
//  val contravariantList:ContravariantList[Cat] = new ContravariantList[Animal]
//  val trainer:Trainer[Cat]= new Trainer[Animal]
//
//
//  /**
//   * bounded types
//   * A <: Animal  ==> Cage receive only type of A which are subtype of Animal
//   *[A >: Animal]  ==> A should be supre of Animal
//   * */
//  class Cage[A <: Animal](animal: Animal)
//  val cage = new Cage(new Dog)
//
// //this won't run
// class Car
////  val newCage= new Cage(new Car) //will through erro
//
//}
//
