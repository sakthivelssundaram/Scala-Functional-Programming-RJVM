package playgrpupnd

import scala.io.StdIn.readDouble

object Abstraction extends App{

  /*
  def combination(n: Int, base: Double, op:(Double, Double)=> Double): Double = {
    if(n >1) base else op(readDouble(), combination(n-1, base,op))
  }

  println(combination(5,1,_ + _))
   */

  class Box(var x : Int){
    def update(f : Int => Int) = x = f(x)
    def printMsg(msg : String) = {
      println(msg + x)
    }
  }

  val b = new Box(2)
  b.printMsg("Hello")

  b.update(i => i + 5)
  b.printMsg("Hello")

}
