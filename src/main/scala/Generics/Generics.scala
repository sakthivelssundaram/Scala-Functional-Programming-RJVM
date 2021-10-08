package Generics

trait MyPrediacte[-T]{
  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] {
  def transformer(ele: A): B
}

abstract class genericMylist[+A] {
  def add[B >: A](element: B): genericMylist[B]
  def head:A
  def tail: genericMylist[A]
  def isgenericEmpty: Boolean
  def printList: String
  override def toString: String = "[" + printList + "]"
  def map[B](transform: MyTransformer[A, B]): genericMylist[B]
  def filter(predicate: MyPrediacte[A]): genericMylist[A]
  def fMap[B](transform: MyTransformer[A,genericMylist[B]]): genericMylist[B]
  def ++[B >: A](ele: genericMylist[B]): genericMylist[B] //same as add()
}

class genericEmpty extends genericMylist[Nothing] {
  def isgenericEmpty = true
  def head: Nothing = throw new NoSuchElementException
  def tail: genericMylist[Nothing]= throw new NoSuchElementException
  def add[B >: Nothing](element: B): genericMylist[B] = new genericCons(element, new genericEmpty)
  def printList: String = "NILL"

  def map[B](transform: MyTransformer[Nothing, B]): genericMylist[B] = new genericEmpty
  def filter(predicate: MyPrediacte[Nothing]): genericMylist[Nothing] = new genericEmpty

  def fMap[B](transform: MyTransformer[Nothing,genericMylist[B]]): genericMylist[B] = new genericEmpty
  def ++[B >: Nothing](ele: genericMylist[B]): genericMylist[B] = ele
}

class genericCons[+A](h:A, t: genericMylist[A]) extends genericMylist[A]{
  def printList: String = {
    if (t.isgenericEmpty) "" +h
    else h + " " +t.printList
  }
  def isgenericEmpty = false
  def head:A = h
  def tail: genericMylist[A] = t
  def add[B >: A](element: B):genericMylist[B] = new genericCons(element, this)

  /*
  [1,2,3].map(n => n*2)
  = new genericCons("1*2", [2,3].map(n => n*2)
  = new genericCons(2, new genericCons("2*2",[3].map(n => n*2))
  = new genericCons(2, new genericCons(4,new genericCons("3*2", genericEmpty.map(n => n*2))
  = new genericCons(2, new genericCons(4,new genericCons(6, new genericEmpty)))
   */

  def map[B](trans: MyTransformer[A, B]): genericMylist[B] = {
    new genericCons[B](trans.transformer(h),t.map(trans))
  }
 /*
 [1,2,4].filter(n => n%2 == 0)
 =[2,4].filter(n => n%2 == 0)
 =new genericCons(2, [4].filter(n => n%2 == 0))
 =new genericCons(2, new genericCons(4, genericEmpty.filter(n => n%2 == 0))
 =new genericCons(2, new genericCons(4, genericEmpty))
  */
  def filter(predicate: MyPrediacte[A]): genericMylist[A] = {
    if(predicate.test(h)) new genericCons(h,t.filter(predicate))
    else t.filter(predicate)
  }
  /*
  [1,2,3] ++ [4] //where [4] => new genericCons(4, new genericEmpty)
  = new genericCons(1,[2,3] ++ [4])
  = new genericCons(1,new genericCons(2,[3] ++ [4]))
  = new genericCons(1,new genericCons(2,new genericCons(3, genericEmpty ++ [4]))
  = new genericCons(1,new genericCons(2,new genericCons(3, [4]))
  = new genericCons(1,new genericCons(2,new genericCons(3, new generiCons(4, new genericEmpty))))
   */
  def ++[B >: A](ele: genericMylist[B]): genericMylist[B] = new genericCons[B](h, t ++ ele)

  /*
  [1,2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ genericEmpty.fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ genericEmpty
   */

  def fMap[B](trans: MyTransformer[A,genericMylist[B]]): genericMylist[B] =
    trans.transformer(h) ++ t.fMap(trans)



}

object Generics extends App{

  val listOfIntegers: genericMylist[Int] = new genericCons(1, new genericCons(2, new genericCons(3, new genericEmpty)))
  val listOfString: genericMylist[String] = new genericCons("S", new genericCons("A", new genericCons("K", new genericEmpty)))
  val listOfMix: genericMylist[Any] = new genericCons(1, new genericCons("A", new genericCons("K", new genericEmpty)))

  val listOfAnotherInteger: genericMylist[Any] = new genericCons(4, new genericCons(5, new genericCons(6, new genericEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)
  println(listOfMix.toString)
  println(listOfString.head)
  println(listOfString.tail.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] { override def transformer(ele: Int): Int = ele * 33}))

  println(listOfString.map((ele: String) => ele + "-" + 33))

  println(listOfIntegers.filter(new MyPrediacte[Int] { override def test(ele: Int): Boolean = ele % 2 ==0 }))

  println(listOfAnotherInteger ++ listOfIntegers)

  println(listOfIntegers.fMap(new MyTransformer[Int, genericMylist[Int]] {
    override def transformer(ele: Int): genericCons[Int] = new genericCons(ele,new genericCons(ele+1,new genericEmpty))}))

  val newListString:genericCons[String] = new genericCons[String]("ha this is", new genericCons[String]("sakthivel Somasundaram", new genericEmpty))


/*

class genericMylist[+A] {
    def add[B >: A ](element: B): genericMylist[B] = ???
    /*
    A = Cat
    B = Dog = Animal
    so if a Dog is added to a list of Cats then add the new element and give me a new abstract list of Animal
    [B >: A] => list[B]
     */
    //use the type A
  }
  val listOfInt = new genericMylist[Int]
  val listOfString = new genericMylist[String]


  //generic Object:
  object genericMylist {
    def genericEmpty[A]: genericMylist[A] = ???
  }

  val genericEmptyListOfIntegers = genericMylist.genericEmpty[Int]


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
