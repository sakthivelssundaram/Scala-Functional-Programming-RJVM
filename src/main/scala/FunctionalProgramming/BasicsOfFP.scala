package FunctionalProgramming

object BasicsOfFP extends App {

  // 1. a function which takes 3 strings and concatenamts them

  val stringContat:(String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b:String): String = a + " " + b
  }

  println(stringContat("sakthivel","kiruthika"))

 //2. functioin that takes a int and returns another function

  val superAdder : Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]{
    override def apply(x :Int) = new Function[Int, Int] {
      override def apply(v1: Int): Int = x + v1}
  }

  println(superAdder(4)(3))
  val adder3 = superAdder(3)
  println(adder3(4))

  //rewrite of SuperAdder to annomous function

  val superAdd2: Int => (Int => Int) = (x : Int) => (y: Int) => x + y
  println(superAdd2(4)(3))
  val adder32 = superAdd2(3)
  println(adder32(4))
}
