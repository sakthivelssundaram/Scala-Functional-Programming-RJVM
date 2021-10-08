package FunctionalProgramming

/*
trait MyPrediacte[-T]{ // T => Boolean

  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] { // A => B
  def transformer(ele: A): B
}*/

abstract class genericFPMylist[+A] {
  def add[B >: A](element: B): genericFPMylist[B]
  def head:A
  def tail: genericFPMylist[A]
  def isgenericFPEmpty: Boolean
  def printList: String
  override def toString: String = "[" + printList + "]"
  def map[B](transform: A => B): genericFPMylist[B]
  def filter(predicate: A => Boolean): genericFPMylist[A]
  def fMap[B](transform: A => genericFPMylist[B]): genericFPMylist[B]
  def ++[B >: A](ele: genericFPMylist[B]): genericFPMylist[B] //same as add()
}

class genericFPEmpty extends genericFPMylist[Nothing] {
  def isgenericFPEmpty = true
  def head: Nothing = throw new NoSuchElementException
  def tail: genericFPMylist[Nothing]= throw new NoSuchElementException
  def add[B >: Nothing](element: B): genericFPMylist[B] = new genericFPCons(element, new genericFPEmpty)
  def printList: String = "NILL"

  def map[B](transform: Nothing => B): genericFPMylist[B] = new genericFPEmpty
  def filter(predicate: Nothing => Boolean): genericFPMylist[Nothing] = new genericFPEmpty

  def fMap[B](transform: Nothing => genericFPMylist[B]): genericFPMylist[B] = new genericFPEmpty
  def ++[B >: Nothing](ele: genericFPMylist[B]): genericFPMylist[B] = ele
}

class genericFPCons[+A](h:A, t: genericFPMylist[A]) extends genericFPMylist[A]{
  def printList: String = {
    if (t.isgenericFPEmpty) "" +h
    else h + " " +t.printList
  }
  def isgenericFPEmpty = false
  def head:A = h
  def tail: genericFPMylist[A] = t
  def add[B >: A](element: B):genericFPMylist[B] = new genericFPCons(element, this)

  /*
  [1,2,3].map(n => n*2)
  = new genericFPCons("1*2", [2,3].map(n => n*2)
  = new genericFPCons(2, new genericFPCons("2*2",[3].map(n => n*2))
  = new genericFPCons(2, new genericFPCons(4,new genericFPCons("3*2", genericFPEmpty.map(n => n*2))
  = new genericFPCons(2, new genericFPCons(4,new genericFPCons(6, new genericFPEmpty)))
   */

  def map[B](trans: A => B): genericFPMylist[B] = {
    new genericFPCons[B](trans(h),t.map(trans))
  }
  /*
  [1,2,4].filter(n => n%2 == 0)
  =[2,4].filter(n => n%2 == 0)
  =new genericFPCons(2, [4].filter(n => n%2 == 0))
  =new genericFPCons(2, new genericFPCons(4, genericFPEmpty.filter(n => n%2 == 0))
  =new genericFPCons(2, new genericFPCons(4, genericFPEmpty))
   */
  def filter(predicate: A => Boolean): genericFPMylist[A] = {
    if(predicate(h)) new genericFPCons(h,t.filter(predicate))
    else t.filter(predicate)
  }
  /*
  [1,2,3] ++ [4] //where [4] => new genericFPCons(4, new genericFPEmpty)
  = new genericFPCons(1,[2,3] ++ [4])
  = new genericFPCons(1,new genericFPCons(2,[3] ++ [4]))
  = new genericFPCons(1,new genericFPCons(2,new genericFPCons(3, genericFPEmpty ++ [4]))
  = new genericFPCons(1,new genericFPCons(2,new genericFPCons(3, [4]))
  = new genericFPCons(1,new genericFPCons(2,new genericFPCons(3, new generiCons(4, new genericFPEmpty))))
   */
  def ++[B >: A](ele: genericFPMylist[B]): genericFPMylist[B] = new genericFPCons[B](h, t ++ ele)

  /*
  [1,2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ genericFPEmpty.fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ genericFPEmpty
   */

  def fMap[B](trans: A => genericFPMylist[B]): genericFPMylist[B] =
    trans(h) ++ t.fMap(trans)



}

object HOF_Curries extends App{

  val listOfIntegers: genericFPMylist[Int] = new genericFPCons(1, new genericFPCons(2, new genericFPCons(3, new genericFPEmpty)))
  val listOfString: genericFPMylist[String] = new genericFPCons("S", new genericFPCons("A", new genericFPCons("K", new genericFPEmpty)))
  val listOfMix: genericFPMylist[Any] = new genericFPCons(1, new genericFPCons("A", new genericFPCons("K", new genericFPEmpty)))

  val listOfAnotherInteger: genericFPMylist[Any] = new genericFPCons(4, new genericFPCons(5, new genericFPCons(6, new genericFPEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)
  println(listOfMix.toString)
  println(listOfString.head)
  println(listOfString.tail.toString)

  println(listOfIntegers.map(new Function1[Int, Int] { override def apply(ele: Int): Int = ele * 33}))

  println(listOfString.map((ele: String) => ele + "-" + 33))

  println(listOfIntegers.filter(new Function1[Int,Boolean] { override def apply(ele: Int): Boolean = ele % 2 ==0 }))

  println(listOfAnotherInteger ++ listOfIntegers)

  println(listOfIntegers.fMap(new Function1[Int, genericFPMylist[Int]] {
    override def apply(ele: Int): genericFPCons[Int] = new genericFPCons(ele,new genericFPCons(ele+1,new genericFPEmpty))}))

  val newListString:genericFPCons[String] = new genericFPCons[String]("ha this is", new genericFPCons[String]("sakthivel Somasundaram", new genericFPEmpty))


  /*
  
  class genericFPMylist[+A] {
      def add[B >: A ](element: B): genericFPMylist[B] = ???
      /*
      A = Cat
      B = Dog = Animal
      so if a Dog is added to a list of Cats then add the new element and give me a new abstract list of Animal
      [B >: A] => list[B]
       */
      //use the type A
    }
    val listOfInt = new genericFPMylist[Int]
    val listOfString = new genericFPMylist[String]
  
  
    //generic Object:
    object genericFPMylist {
      def genericFPEmpty[A]: genericFPMylist[A] = ???
    }
  
    val genericFPEmptyListOfIntegers = genericFPMylist.genericFPEmpty[Int]
  
  
    //variance
    class Animal
    class Cat extends Animal
    class Dog extends Animal
  
    //Yes, List[Cat] extends List[Animal] = covariance
    class CovariantList[+A]
    val animal: Animal = new Cat
    val animalList: CovariantList[Animal] = new CovariantList[Cat]
  
    //No, Invariance
    class Invariance[A]
    val animala: Animal = new Dog
    val animalInvarianceList: Invariance[Animal] = new Invariance[Animal]
  
    //Hell NO, Contravariance
    class Trainer[-A]
    val trainer: Trainer[Cat] =  new Trainer[Animal]
  
    //bounced Types
    class Cage[A <: Animal](animal : A)
    val cage = new Cage(new Dog)
  
    class Car
    val newCage = new Cage(new Car)
  
   */



}
