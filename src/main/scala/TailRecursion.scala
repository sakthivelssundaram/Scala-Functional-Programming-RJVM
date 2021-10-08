import scala.annotation.tailrec
/*
Things to consider for recursion
1. find the base condidtion to exit the loop;
  in order to find the base condition figure out the smallest input you can pass as input
2. think of the least amount of work you can do before you call the function once again*/

object TailRecursion extends App{
  //1. Concat a String n times

  def stringConcat(n: Int): String = {
    if(n <= 1) " hello"
    else " hello " + stringConcat(n-1)
  }
  //print(stringConcat(50000))

  //Tail Recursion: Fuction call is the last line of the function,
  // this eleminated the call of stack frame and can reduce the memory used

  def anotherStringConcat(n: Int) = {
    def stringConcatHelper(n:Int, accumulator: String): String = {
      if (n <= 1) accumulator
      else stringConcatHelper(n-1," hello " + accumulator)
    }

    stringConcatHelper(n," hello ")
  }
  //print(anotherStringConcat(50000))
  //Another Version of the string Rec
  @tailrec
  def stringContatTailrec(aString: String, n: Int, accumo: String) : String = {
    if(n <= 0) accumo
    else(stringContatTailrec(aString, n-1, aString + accumo))
  }
  println(stringContatTailrec("hello ",4,""))

  //2. isPrime as Tail Recursion
  def isPrime(n : Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, accmulo: Boolean): Boolean = {
      if(!accmulo) false
      else if(t <= 1) true
      else isPrimeHelper(t-1, n % t != 0 && accmulo)
    }
    isPrimeHelper(n-1, true)
  }

  println(isPrime(678))

  //3. Fibonachi seried in Tail Recusrsion

  def fibonachi(n: Long): Long  = {
    @tailrec
    def fibbnoHelper(i: Long, last: Long, next:Long) : Long = {
      if (i >= n) last
      else fibbnoHelper(i+1,last+next, last)
    }

    if (n <=2 ) n
    else fibbnoHelper(2,1,1)
  }
  /*println(fibonachi(4032480L))
  println(fibonachi(4032481L))
  println(fibonachi(4032482L))
  println(fibonachi(4032483L))*/

  def fibbnoTest(n: Long): Long = {
    if(n<2) n
    else fibbnoTest(n-1) + fibbnoTest(n-2)
  }


  //reverse a string
  def reverseString(input: String): String = {
    println("Input= " +input)
    if (input.length == 0 | input == "") ""
    else reverseString(input.substring(1,input.length)) + input(0)
  }

  println(reverseString("Hello"))
  //palindrome check

  def palindrom(input: String): Boolean = {
    println("input "+ input)
    if(input.length == 0 | input.length == 1)
      true
    else if(input(0) == input(input.length-1))
      palindrom(input.substring(1,input.length-1))
    else
      false
  }

  println(palindrom("racecar"))

  //decimal to Binary conversion
  /*
  1. smallest possible input "0" | "1" => "0" | "1"
  2. smallest possible work to do. concept number%2 keep the remainder for decimal conversion
   */

  def decimalToBinary(input: Int): String = {
    @tailrec
    def go(in: Int, result: String): String =
      if (in == 0) result
      else go(in / 2, (in % 2).toString + result)

    go(input,"")
  }
  println(decimalToBinary(8))
  //println("fibbnoTest"+fibbnoTest(500000L).toString)


  //sum of Natural numbers
  @tailrec
  def sumOfNatural(input: Int, result: Int): Int = if(input == 0) result
  else sumOfNatural(input-1, result+input)

  println(sumOfNatural(1,0))

/*
  //Merge Sort
  def mergSort(input: List[Int],start: Int, end: Int):List[Int] = {
    if(start < end ){
      val mid = (start + end)/2
      mergSort(input,start,mid)
      mergSort(input,mid+1,end)
      //merge(input,start,mid,end)
    }

  }
  def merge(input: List[Int],start: Int, mid: Int, end: Int) = {

  }*/
}
