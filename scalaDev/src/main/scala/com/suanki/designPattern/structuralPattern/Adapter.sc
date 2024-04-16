/** We have an existing object which provides the functionality that client need, but
  * client code can't use this object because it expects an object with different
  * interface
  *
  * using adapter design pattern we make this existing object work with client by adapting
  * the object to client's expected interface.
  */

//adaptee: existing object
//adapter : which provide bridge b/w existing object and other object by extending and implementing both
/** but then there is another way where adapter form has a relation with adapteer and
  * implement other interface which client needs
  */

//client expect this interface

//*****************Class Adapter *************************//

//adaptee
class Employee {
  private var _name: String           = ""
  private var _jobTitle: String       = ""
  private var _officeLocation: String = ""

  def name: String           = _name
  def jobTitle: String       = _jobTitle
  def officeLocation: String = _officeLocation

  def name_=(newName: String): Unit       = _name = newName
  def jobTitle_=(title: String): Unit     = _jobTitle = title
  def officeLocation_=(off: String): Unit = _officeLocation = off

  def apply(name: String, jobTitle: String, offcLocation: String): Employee = {
    this._name = name
    this._jobTitle = jobTitle
    this._officeLocation = offcLocation
    new Employee
  }
}

//client wants customer along with Employee class
trait Customer {
  def name: String
  def designation: String
  def address: String
}

//Adapter
class EmployeeClassAdapter extends Employee with Customer {
  // we have available in employee but with diff name
  override def designation = jobTitle

  override def address = officeLocation
}

//client code which require customer interface
class BusinessCardDesigner {

  def designCard(customer: Customer): String = {
    customer.name + " " + customer.address + " " + customer.designation
  }

}

def populateEmployeeData(employee: Employee): Unit = {

  employee.name = "new name"
  employee.jobTitle = "sDE"
  employee.officeLocation = "EON-2"
}

/** Using class /Two-way adapter */
val n = new EmployeeClassAdapter
populateEmployeeData(n)
val b = new BusinessCardDesigner
b.designCard(n)

class EmployeeObjectAdapter(emp: Employee) extends Customer {

  // we have available in employee but with diff name
  override def designation: String = emp.jobTitle

  override def address: String = emp.officeLocation

  override def name: String = emp.name
}

val e1 = new Employee
populateEmployeeData(e1)
val n1 = new EmployeeObjectAdapter(e1)
val b1 = new BusinessCardDesigner()
b1.designCard(n)

//Example of java api

// InputStreamReader
