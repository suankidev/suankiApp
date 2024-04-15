import java.time.LocalDate

/** ?
  *
  * We have a complex process to construct an object involing multiple states
  *
  * then builder patter can help us
  *
  * In builder we remove the logic related to object construction from client code $ abstract it in seprate class
  */

case class Address(houseNumber: String, street: String, city: String, zipcode: String, state: String)
//What proble builde patter solve
// 1.class constructor requires a lot of info
// 2. object that need other objects or parts of them to construct them
// 3
abstract class UserDTO {
  val name: String
  val address: String
  val age: String
}

case class User(firstName: String, lastName: String, birthdate: LocalDate, address: Address)

abstract class UserDTOBuilder {

  def withFirstName(fname: String): UserDTOBuilder
  def withLastName(lname: String): UserDTOBuilder
  def withBirthday(date: java.time.LocalDate): UserDTOBuilder
  def withAddress(address: Address): UserDTOBuilder

  def build(): UserDTO
  def getUserDTO: UserDTO
}
case class UserWebDTO(name: String, address: String, age: String) extends UserDTO

class UserWebDTOBuilder extends UserDTOBuilder {
  private var firstName: String = _
  private var lastName: String  = _
  private var age: String       = _
  private var address: String   = _
  private var dto: UserDTO      = _

  override def withFirstName(fname: String): UserWebDTOBuilder = {
    firstName = fname
    this
  }

  override def withLastName(lname: String): UserWebDTOBuilder = {

    lastName = lname
    this
  }
  override def withBirthday(date: LocalDate): UserWebDTOBuilder = {
    val ageInYear: java.time.Period = java.time.Period.between(date, java.time.LocalDate.now())
    age = ageInYear.getYears.toString
    this
  }

  override def withAddress(address: Address): UserDTOBuilder = {
    this.address = address.houseNumber + address.street + address.city + address.state
    this
  }

  override def build(): UserDTO = {
    dto = UserWebDTO(firstName + lastName, address, age)
    dto
  }

  override def getUserDTO = dto
}

//main

def createUser(): User = {
  val address: Address = Address(
    houseNumber = "100",
    street = "6",
    city = "Pune",
    zipcode = "2451",
    state = "Maharashtra"
  )
  val user: User =
    User(firstName = "Rajoo", lastName = "Singhania", birthdate = java.time.LocalDate.of(1992, 5, 23), address)
  user
}

def createDTO(userDTOBuilder: UserDTOBuilder, user: User): UserDTO = {
  userDTOBuilder
    .withFirstName(user.firstName)
    .withLastName(user.lastName)
    .withAddress(user.address)
    .withBirthday(user.birthdate)
    .build()

}

val userDTOBuilder = new UserWebDTOBuilder()
val user           = createUser()
val userDTO        = createDTO(userDTOBuilder, user)

println("")
userDTO.name

new java.util.Calendar.Builder().se
