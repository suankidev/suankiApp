//https://medium.com/@salvipriya97/java-aggregation-and-composition-explained-with-examples-66cbffd21b9c#:~:text=In%20Java%2C%20aggregation%20is%20a,part%20of%20its%20internal%20structure.

//stron association. without whol part can't exist

class House(val name: String) {
  private[this] val _listOfRooms: List[Room] = List(
    new Room("Living room"),
    new Room("dining room"),
    new Room("Strudy Room")
  )
  def room: List[Room] = _listOfRooms

  class Room(val name: String)
}

val newHouse = new House("Vandanam")

newHouse.room.size
