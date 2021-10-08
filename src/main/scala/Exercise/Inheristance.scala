package Exercise

abstract class Mylist {
  def head : Int
  def tail : Mylist
  def isEmpty: Boolean
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def add(i : Int): Mylist
}

object Empty extends Mylist {
  def head: Int = throw new NoSuchElementException
  def tail : Mylist = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def printElements: String = ""
  def add(i : Int): Mylist = new Cons(i,Empty)
}

class Cons(h: Int, t: Mylist) extends Mylist {
  def head : Int = h
  def tail : Mylist = t
  def isEmpty: Boolean = false
  def printElements: String = {

    def go(t: Mylist, hh: String=null):String =
      if (t.isEmpty) hh
      else go(t.tail,t.head.toString +", "+ hh)

    go(this)
  }

  def add(i : Int): Mylist = new Cons(i,this)
}

object Cons {
  def main(args: Array[String]) = {
    val l1 = new Cons(1,new Cons(2, new Cons(3, Empty)))
    println(l1.head)
    val l2 = l1.add(4)
    println(l2.head)
    println(l2)

  }
  def apply(i: Int) = new Cons(i, Empty)
}
