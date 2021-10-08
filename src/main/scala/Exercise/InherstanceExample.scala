package Exercise
/*
trait MyPredicate[-A]{
  def test(ele: A): Boolean
}

trait MyTransform[-A, B]{
  def transform(ele: A): B
}

abstract class Mylist2[+A] {
  def head: A
  def tail: Mylist2[A]
  def isEmpty: Boolean
  def add[B >: A](i :B): Mylist2[B] //Boundry condition when you add a element B that is subtype/subclass of A to
  // a list[A] then the method returns a list[B] type
  def printList[A](t:Mylist2[A]):String
  override def  toString: String = printList(this)

  def map[B](lists: MyTransform[A,B]): Mylist2[B]
  def filter[A](preicate: MyPredicate[A]): Mylist2[A]
  //def flatmap[A](lists:Mylist2[A]): Mylist2[A]
}
//Nothing Because Nothing is the subclass for all the objects
// val listOfInt = new Cons[Int] should have empty => Empty
// val listOfString = new Cons[String] should have empty => Empty

class Empty2 extends Mylist2[Nothing] {
  def head: Nothing  = throw  new NoSuchElementException
  def tail: Mylist2[Nothing]  = throw  new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](i:B): Mylist2[B] = new Cons2[B](i, this)
  //when we add a element of type B to the list[Nothing] then the function retuns a new cons list[B] type

  override def printList[A](t:Mylist2[A]):String = ""

  def map[B](lists: MyTransform[Nothing,B]): Mylist2[Nothing] = new Empty2
  def filter[A](preicate: MyPredicate[A]): Mylist2[Nothing]  = new Empty2


}

class Cons2[+A](h:A, t: Mylist2[A]) extends Mylist2[A] {
  override def head: A = h

  override def tail: Mylist2[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](i: B): Mylist2[B] = new Cons2[B](i,this)

  override def printList[A](t: Mylist2[A]): String =
    if (t.isEmpty) ""
    else t.head + ", " + printList(t.tail)

  def map[B](lists: MyTransform[A,B]): Mylist2[B] = new Cons2(lists.transform(h), t.map(lists))
  def filter(preicate: MyPredicate[A]): Mylist2[A] =
    if(preicate.test(h)) new Cons2(h, t.filter(preicate))
    else t.filter(preicate)

}

object Cons2 extends App{
    val l1 = new Cons2(1, new Cons2("2", new Cons2("Scala", new Empty2)))
    println(l1)
    val l2 = l1.add(23.23)
    println(l2)
    println(l2.head)
    println(l2.tail)
    println("----------------------")
    val e1 = new Empty2
    println(e1.isEmpty)
    val e2 = e1.add(1).add("scala").add("sakthi")
    println(e2.toString)
    println(e1.isEmpty)

    val listOfInt = new Cons2(1, new Cons2(2, new Cons2(3, new Empty2)))
    println(listOfInt.map(new MyTransform[Int, Double]{
      override def transform(ele: Int)= ele * 2.0
    }).toString)



}
*/