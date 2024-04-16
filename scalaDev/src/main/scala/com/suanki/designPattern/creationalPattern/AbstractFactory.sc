import javax.xml.parsers.DocumentBuilderFactory
// Abstract factory is used when we have two or more objects which work together forming
//a kit or set and there can be multiple sets or kits that can be created by client code

//so we separate client code from concrete objects forming such a set and also from the code which creates these sets

abstract class Storage {
  def getId: String
}

class GoogleCloudStorage(capacityInMb: Int) extends Storage {

  override def getId: String = s"G-$capacityInMb"
}

class S3CloudStorage(capacityInMb: Int) extends Storage {

  override def getId: String = s"S3-$capacityInMb"
}

abstract class Instance {

  def start(): Unit
  def stop(): Unit
  def attachStorage(storage: Storage): Unit
}

class Ec2Instance(capacity: String) extends Instance {
  override def start(): Unit = println("Ec2 started")

  override def stop(): Unit = println("Ec2 stopped")

  override def attachStorage(storage: Storage): Unit = println(s"Storage is attached ${storage.getId}")
}

class GoogleComputeInstance(capacity: String) extends Instance {
  override def start(): Unit = println("GoogleComputeInstance started")

  override def stop(): Unit = println("GoogleComputeInstance stopped")

  override def attachStorage(storage: Storage): Unit = println(s"Storage is attached ${storage.getId}")
}

//main
abstract class ResourceFactory {

  def createInstance(capacity: String): Instance
  def createStorage(capacity: Int): Storage

}

class GoogleCloudResourceFactory extends ResourceFactory {
  override def createInstance(capacity: String) = {
    new GoogleComputeInstance(capacity)
  }

  override def createStorage(capacity: Int) = {
    new GoogleCloudStorage(capacity)
  }
}

class AwsCloudResourceFactory extends ResourceFactory {
  override def createInstance(capacity: String) = {
    new Ec2Instance(capacity)
  }

  override def createStorage(capacity: Int) = {
    new S3CloudStorage(capacity)
  }
}

//initializaiton
class Client(_resourceFactory: ResourceFactory) {

  val resourceFactory: ResourceFactory = _resourceFactory

  def createServer(capacity: String, storageCapacity: Int): Instance = {

    val instance: Instance = resourceFactory.createInstance(capacity)
    val storage: Storage   = resourceFactory.createStorage(storageCapacity)
    instance.attachStorage(storage)
    instance
  }

}

//
val aws         = new Client(new GoogleCloudResourceFactory)
val instanceOne = aws.createServer("LARGE", 9292372)
instanceOne.start()
instanceOne.stop()

//example
//javax.xml.parsers.DocumentBuilderFactory is good example of abstract factory
//newInstace() method return the object
val documentBuilderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

//abstract factory Vs factory method
/**   1. Hides factories as well as concrete object used from the client code Vs Hide the concrete object which are used
  *      from the client code.
  */

/** 2. suitable when mutlitple objects are designed to work together and client must use products from single family at
  * a time Vs concerned with one product and it's subclasses collaboration of product it self with other object is
  * irrelevant
  */

//Classes that may not be directly related by inheritance but they all work
//together as a group.
// instance ans storage of AwsResource family workin together
