package playgrpupnd

object MergeSort extends App {
  sealed trait Expr
  case class BinOp(left: Expr, op: String, right: Expr) extends Expr
  case class Litral(values: Int) extends Expr
  case class Variable(name: String) extends Expr


  def stringParser(expr: Expr): Int = expr match {
    case BinOp(left, op, right) => stringParser(left) + stringParser(right)
    case Litral(value) => value.toInt
    case Variable(name) => name.toInt
  }

  val simpleTest = BinOp(Variable("124"),"#",Litral(124))

  println(stringParser(simpleTest))

  def mergsort(arr: Array[Int]): Array[Int] = {
    if (arr.length <= 1) arr
    else {
      val (left, right) = arr.splitAt(arr.length/2)
      val (sortedLeft, sortedRight) = (mergsort(left),mergsort(right))
      var (leftIndex,rightIndex) = (0,0)
      val output = Array.newBuilder[Int]
      while (leftIndex < sortedLeft.length || rightIndex < sortedRight.length){
        val takeleft = (leftIndex < sortedLeft.length, rightIndex < sortedRight.length) match {
            case(true, true) => sortedLeft(leftIndex) < sortedRight(rightIndex)
            case(true, false) => true
            case(false, true) => false
        }
        if(takeleft){
          output += sortedLeft(leftIndex)
          leftIndex += 1

        }else{
          output += sortedRight(rightIndex)
          rightIndex += 1
        }
      }
      output.result()
    }
  }

 mergsort(Array(4, 0, 1, 15, 2, 3,5,9,3,2,1,1)).toList.foreach(println)

}
