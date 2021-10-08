package FunctionalProgramming

/*
trait MyPrediacte[-T]{ // T => Boolean

  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] { // A => B
  def transformer(ele: A): B
}*/

abstract class HOFMylist[+A] {
  def add[B >: A](element: B): HOFMylist[B]
  def head:A
  def tail: HOFMylist[A]
  def isHOFEmpty: Boolean
  def printList: String
  override def toString: String = "[" + printList + "]"
  def map[B](transform: A => B): HOFMylist[B]
  def filter(predicate: A => Boolean): HOFMylist[A]
  def fMap[B](transform: A => HOFMylist[B]): HOFMylist[B]
  def ++[B >: A](ele: HOFMylist[B]): HOFMylist[B] //same as add()
}

class HOFEmpty extends HOFMylist[Nothing] {
  def isHOFEmpty = true
  def head: Nothing = throw new NoSuchElementException
  def tail: HOFMylist[Nothing]= throw new NoSuchElementException
  def add[B >: Nothing](element: B): HOFMylist[B] = new HOFCons(element, new HOFEmpty)
  def printList: String = "NILL"

  def map[B](transform: Nothing => B): HOFMylist[B] = new HOFEmpty
  def filter(predicate: Nothing => Boolean): HOFMylist[Nothing] = new HOFEmpty

  def fMap[B](transform: Nothing => HOFMylist[B]): HOFMylist[B] = new HOFEmpty
  def ++[B >: Nothing](ele: HOFMylist[B]): HOFMylist[B] = ele
}

class HOFCons[+A](h:A, t: HOFMylist[A]) extends HOFMylist[A]{
  def printList: String = {
    if (t.isHOFEmpty) "" +h
    else h + " " +t.printList
  }
  def isHOFEmpty = false
  def head:A = h
  def tail: HOFMylist[A] = t
  def add[B >: A](element: B):HOFMylist[B] = new HOFCons(element, this)

  /*
  [1,2,3].map(n => n*2)
  = new HOFCons("1*2", [2,3].map(n => n*2)
  = new HOFCons(2, new HOFCons("2*2",[3].map(n => n*2))
  = new HOFCons(2, new HOFCons(4,new HOFCons("3*2", HOFEmpty.map(n => n*2))
  = new HOFCons(2, new HOFCons(4,new HOFCons(6, new HOFEmpty)))
   */

  def map[B](trans: A => B): HOFMylist[B] = {
    new HOFCons[B](trans(h),t.map(trans))
  }
  /*
  [1,2,4].filter(n => n%2 == 0)
  =[2,4].filter(n => n%2 == 0)
  =new HOFCons(2, [4].filter(n => n%2 == 0))
  =new HOFCons(2, new HOFCons(4, HOFEmpty.filter(n => n%2 == 0))
  =new HOFCons(2, new HOFCons(4, HOFEmpty))
   */
  def filter(predicate: A => Boolean): HOFMylist[A] = {
    if(predicate(h)) new HOFCons(h,t.filter(predicate))
    else t.filter(predicate)
  }
  /*
  [1,2,3] ++ [4] //where [4] => new HOFCons(4, new HOFEmpty)
  = new HOFCons(1,[2,3] ++ [4])
  = new HOFCons(1,new HOFCons(2,[3] ++ [4]))
  = new HOFCons(1,new HOFCons(2,new HOFCons(3, HOFEmpty ++ [4]))
  = new HOFCons(1,new HOFCons(2,new HOFCons(3, [4]))
  = new HOFCons(1,new HOFCons(2,new HOFCons(3, new generiCons(4, new HOFEmpty))))
   */
  def ++[B >: A](ele: HOFMylist[B]): HOFMylist[B] = new HOFCons[B](h, t ++ ele)

  /*
  [1,2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3].fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ HOFEmpty.fMap(n => (n, n+1)
  [1,2] ++ [2,3] ++ [3,4] ++ HOFEmpty
   */

  def fMap[B](trans: A => HOFMylist[B]): HOFMylist[B] =
    trans(h) ++ t.fMap(trans)



}

object Generics extends App{

  val listOfIntegers: HOFMylist[Int] = new HOFCons(1, new HOFCons(2, new HOFCons(3, new HOFEmpty)))
  val listOfString: HOFMylist[String] = new HOFCons("S", new HOFCons("A", new HOFCons("K", new HOFEmpty)))
  val listOfMix: HOFMylist[Any] = new HOFCons(1, new HOFCons("A", new HOFCons("K", new HOFEmpty)))

  val listOfAnotherInteger: HOFMylist[Any] = new HOFCons(4, new HOFCons(5, new HOFCons(6, new HOFEmpty)))

  println(listOfIntegers.toString)
  println(listOfString.toString)
  println(listOfMix.toString)
  println(listOfString.head)
  println(listOfString.tail.toString)

  println(listOfIntegers.map(new Function1[Int, Int] { override def apply(ele: Int): Int = ele * 33}))

  println(listOfString.map((ele: String) => ele + "-" + 33))

  println(listOfIntegers.filter(new Function1[Int,Boolean] { override def apply(ele: Int): Boolean = ele % 2 ==0 }))

  println(listOfAnotherInteger ++ listOfIntegers)

  println(listOfIntegers.fMap(new Function1[Int, HOFMylist[Int]] {
    override def apply(ele: Int): HOFCons[Int] = new HOFCons(ele,new HOFCons(ele+1,new HOFEmpty))}))

  val newListString:HOFCons[String] = new HOFCons[String]("ha this is", new HOFCons[String]("sakthivel Somasundaram", new HOFEmpty))


  /*
  
  class HOFMylist[+A] {
      def add[B >: A ](element: B): HOFMylist[B] = ???
      /*
      A = Cat
      B = Dog = Animal
      so if a Dog is added to a list of Cats then add the new element and give me a new abstract list of Animal
      [B >: A] => list[B]
       */
      //use the type A
    }
    val listOfInt = new HOFMylist[Int]
    val listOfString = new HOFMylist[String]
  
  
    //generic Object:
    object HOFMylist {
      def HOFEmpty[A]: HOFMylist[A] = ???
    }
  
    val HOFEmptyListOfIntegers = HOFMylist.HOFEmpty[Int]
  
  
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
