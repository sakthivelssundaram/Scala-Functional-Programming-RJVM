package playgrpupnd

import scala.annotation.tailrec

object recursion {

  def fibbno(n: Int) : Int = {
    @tailrec
    def fibbnoHelper(t: Int, first: Int, Last: Int): Int = {
      if (t == 0) Last
      else if(t == 1)  first
      else fibbnoHelper(t-1,first + Last, first)
    }

    fibbnoHelper(n,0,1)
  }

  def main(args: Array[String]) = {
    print(fibbno(11))
  }
}
