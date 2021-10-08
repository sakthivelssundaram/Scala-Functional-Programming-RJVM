package playgrpupnd

object sample extends App{

  def binarySearch(l1: List[Int], Low: Int, High: Int): Int = {
    println("===============================")
    println("Low "+ Low.toString)
    println("High "+ High.toString)
    if(Low > High) return -1
    val middle  = (Low + High) / 2
    println("Middle " + middle.toString)
    val key = l1(middle)
    (middle > key, middle < key) match {
      case(false, false) => println("key " + key.toString); println("middle " + middle.toString); key
      case(true, false)  => binarySearch(l1,middle+1, High)
      case(false, true)  => binarySearch(l1, Low, middle-1)
    }
  }
  //println(binarySearch(List(-20,-10,0,3,2,8,4,6),0,List(-20,-10,0,3,2,3,4,6).length-1))
  def binarySearchUpdated(l1: List[Int],Key:Int, Low: Int, High: Int): Boolean = {
    println("===============================")
    println("Low "+ Low.toString)
    println("High "+ High.toString)

    if(Low > High) return false
    val middle  = (Low + High) / 2
    println("Middle " + middle.toString)
    val key = Key
    l1 match {
      case(l1:List[Int])  if(l1(middle) == key) => true
      case(l1: List[Int]) if(l1(middle) > key) => binarySearchUpdated(l1,key,middle+1, High)
      case(l1: List[Int]) if(l1(middle) < key) => binarySearchUpdated(l1, key, Low, middle-1)
    }
  }
  //[2,2,2,3,5,5,5] => 3 => n-1 elements being repeated k times

  def mapupdating(a : List[Int]) = {
    val newList = a.map(x => (x,1))
    val newList2 = newList.groupBy(_._1).map(x=> (x._1,x._2.map(z => z._2)))
    val newList3 = newList2.map(x => (x._1, x._2.sum))
    newList3.map(x => x match {
      case(x,1) => x
      case(_,_) => "Nothing exits"
    })
  }

  println(mapupdating(List(2,2,2,3,5,5,5)))
}
