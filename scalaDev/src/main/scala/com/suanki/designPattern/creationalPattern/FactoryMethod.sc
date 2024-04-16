// simple factory Vs Factory Method
// 1. simple move instantion logic away from code Vs delegate object creation to subclasses
// 2. knows about all classes whose object is can create Vs don't know in adavce about all product subclasses

// we use this pattern when we don not know in adavnce whic class we may need to
// instantate beforehand and also to allow new classes tobe added to system and handle their creation
// without affecting client code
// we let subclasses decide which objec to instantiate by overriding the factory method

abstract class Message {

  def getContent: String
  def addDefaultHeader(): Unit = {}

  def encrypt(): Unit = {}

}

class TextMessage extends Message {

  override def getContent: String = "{\"JSON\":[]}"

}

class JsonMessage extends Message {
  override def getContent: String = "Text Message"
}

abstract class MessageCreator {

  def getMessage: Message = {
    val msg: Message = createMessage()
    msg.addDefaultHeader()
    msg.encrypt()
    msg
  }

  def createMessage(): Message
}

class JsonMessageCreator extends MessageCreator {
  override def createMessage(): Message = {
    new JsonMessage()
  }
}

class TextMessageCreator extends MessageCreator {
  override def createMessage(): Message = new TextMessage()
}

//client

val msg: MessageCreator = new TextMessageCreator()
msg.getMessage //object of textmsg

//java.util.AbstractCollect has an abstract method called iterato().
//this method is an example of a factory method

//**********
//important not

//Subcalssess is providing the acutal instance
//problem we need to have those many factorycreator for each subclass
