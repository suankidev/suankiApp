//Aggregation(weak type association)
//container contains the refrence of other object
//both can co-exist

class Department(val name: String)

class University(val name: String) {
  var _department: List[Department] = List.empty // Aggregation
  def department                    = _department
  def addDepartment(department: Department): Unit =
    this._department = this.department :+ department
}

val shardaUniversity = new University("Sharda University")
shardaUniversity.addDepartment(new Department("Engineering and Technology"))
shardaUniversity.addDepartment(new Department("School of Business"))
