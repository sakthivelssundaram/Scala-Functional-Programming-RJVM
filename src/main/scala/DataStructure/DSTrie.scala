package DataStructure

object DSTrie extends App{

  class Trie{
    class Node(var hasValue: Boolean,
               val children: collection.mutable.Map[Char, Node] = collection.mutable.Map())
    val root = new Node(false)

    def add(s: String) = {
      var current = root
      for (c <- s) current = current.children.getOrElseUpdate(c,new Node(false))
      println(current.children.keys.mkString(", "))
      current.hasValue = true
    }

    def contains(s: String): Boolean = {
      var currnet = Option(root)
      for (c <- s if currnet.nonEmpty) currnet = currnet.get.children.get(c)
      currnet.exists(_.hasValue)
    }

    override def toString() = {
      this.root.children.keys.mkString(", ")
      //this.root.children.values.mkString(", ")
    }

  }
  val t = new Trie()
  t.add("Mango")
  t.add("Mandarin")

  //println(t)


}
