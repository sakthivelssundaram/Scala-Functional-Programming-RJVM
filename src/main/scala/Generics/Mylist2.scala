package Generics
/*


abstract class genericMylist {
  def head : Int
  def tail : genericMylist
  def isgenericEmpty: Boolean
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def add(i : Int): genericMylist
}

object genericEmpty extends genericMylist {
  def head: Int = throw new NoSuchElementException
  def tail : genericMylist = throw new NoSuchElementException
  def isgenericEmpty: Boolean = true
  def printElements: String = ""
  def add(i : Int): genericMylist = new genericCons(i,genericEmpty)
}

class genericCons(h: Int, t: genericMylist) extends genericMylist {
  def head : Int = h
  def tail : genericMylist = t
  def isgenericEmpty: Boolean = false
  def printElements: String = {

    def go(t: genericMylist, hh: String=null):String =
      if (t.isgenericEmpty) hh
      else go(t.tail,t.head.toString +", "+ hh)

    go(this)
  }

  def add(i : Int): genericMylist = new genericCons(i,this)
}

object genericMylist {
  object genericCons {
    def main(args: Array[String]) = {
      val l1 = new genericCons(1,new genericCons(2, new genericCons(3, genericEmpty)))
      println(l1.head)
      val l2 = l1.add(4)
      println(l2.head)
      println(l2)

    }
    def apply(i: Int) = new genericCons(i, genericEmpty)
  }
}
 */