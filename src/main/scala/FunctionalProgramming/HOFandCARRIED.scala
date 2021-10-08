package FunctionalProgramming

object HOFandCARRIED extends App{

  def ntimes(f: Int => Int, n : Int, x: Int): Int = {
    if (n <= 0) x
    else ntimes(f, n-1, f(x))
  }
  val plusOne: Int => Int = (x: Int) => x + 1
  //follows the New Function1(Int , Int) = {overwride def apply(x: Int): Int = x +1}

  val plusTwo: Int => Int = new Function1[Int, Int] {override def apply(x : Int): Int =  x +2}

  println(ntimes(plusOne, 10, 1))
  println(ntimes(plusTwo, 10, 1))


  def nTimesBetter(f: Int => Int, n : Int): (Int => Int) =
    if (n<=0) (x: Int) => x + 100
    else (x:Int) => nTimesBetter(f,n-1)(f(x))

  val plus10 = nTimesBetter(plusOne,3)
  println(plus10(100))
}
