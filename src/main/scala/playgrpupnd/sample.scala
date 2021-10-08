package playgrpupnd


trait MyPrediacte[-T]{
  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] {
  def transformer(ele: A): B
  def arrayTransformer(ele: A): Array[B]
}

abstract class Mylist_[+A] {
  def add[B >: A](element: B): Mylist_[B]
  def head:A
  def tail: Mylist_[A]
  def isEmpty: Boolean
  def printList: String
  override def toString: String = "[" + printList + "]"
  def map[B](transform: MyTransformer[A, B]): Mylist_[B]
  def filter(predicate: MyPrediacte[A]): Mylist_[A]
  def fMap[B](transform: MyTransformer[A,Mylist_[B]]): Mylist_[B]
  def ++[B >: A](ele: Mylist_[B]): Mylist_[B] //same as add()
}

class Empty_ extends Mylist_[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException
  def tail: Mylist_[Nothing]= throw new NoSuchElementException
  def add[B >: Nothing](element: B): Mylist_[B] = new Cons_(element, new Empty_)
  def printList: String = "NILL"

  def map[B](transform: MyTransformer[Nothing, B]): Mylist_[B] = new Empty_
  def filter(predicate: MyPrediacte[Nothing]): Mylist_[Nothing] = new Empty_

  def fMap[B](transform: MyTransformer[Nothing,Mylist_[B]]): Mylist_[B] = new Empty_
  def ++[B >: Nothing](ele: Mylist_[B]): Mylist_[B] = ele
}

class Cons_[+A](h:A, t: Mylist_[A]) extends Mylist_[A]{
  def printList: String = {
    if (t.isEmpty) "" +h
    else h + " " +t.printList
  }
  def isEmpty = false
  def head:A = h
  def tail: Mylist_[A] = t
  def add[B >: A](element: B):Mylist_[B] = new Cons_(element, this)

  def map[B](trans: MyTransformer[A, B]): Mylist_[B] = {
    new Cons_[B](trans.transformer(h),t.map(trans))
  }
  def filter(predicate: MyPrediacte[A]): Mylist_[A] = {
    if(predicate.test(h)) new Cons_(h,t.filter(predicate))
    else t.filter(predicate)
  }
  def ++[B >: A](ele: Mylist_[B]): Mylist_[B] = new Cons_[B](h, t ++ ele)

  def fMap[B](trans: MyTransformer[A,Mylist_[B]]): Mylist_[B] =
    trans.transformer(h) ++ t.fMap(trans)

}

object Generics extends App{

  val newListString:Cons_[String] = new Cons_[String]("Hai, there! This is", new Cons_[String]("Scala Basic FP Course",new Cons_[String]("on UDEMY",new Empty_)))

  println(newListString.head)

  /*println(newListString.fMap(new MyTransformer[String, Array[Mylist_[String]]]{
    override def transformer(ele: String): Array[Cons_[String]] = {
      val arrayElements = ele.split(" ")
      val consArray = arrayElements.map(x => new Cons_(x, new Empty_))

      consArray
    }
  }))*/


}

