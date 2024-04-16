/** Singleton class has only one instance, accessible globally throught a single point via method/filed
  *
  * Any state you add in your singleton become available globaly
  */

object DemoSingleton {

  private var counter: Int = 1

  def incrementor: Unit = counter += 2
  def getcounter: Int   = counter

}

DemoSingleton.getcounter
DemoSingleton.incrementor
DemoSingleton.getcounter
DemoSingleton
