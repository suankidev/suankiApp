abstract class Product {
  val name: String
}

class ProductA(val name: String) extends Product
class ProductB(val name: String) extends Product

//can be static since does not require status of object
object SimpleFactory {

  def createProduct(productType: String): Product = {
    productType match {
      case "A" => new ProductA("ProductA")
      case "B" => new ProductA("ProductB")
    }
  }
}

class client {

  val productA = SimpleFactory.createProduct("A")
  val productB = SimpleFactory.createProduct("B")

}

//Example
// java.text.NumberFormat has getInstance method

val numberFormate: java.text.NumberFormat = java.text.NumberFormat.getInstance()

numberFormate.format(101010)
