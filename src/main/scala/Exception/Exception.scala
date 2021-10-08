package Exception

class OverFlowException extends RuntimeException
class UnderFlowException extends RuntimeException

object PocketCalculator extends App{
  def add(x: Int, y: Int) = {
    val result = x + y
    if(x > 0 && y> 0 && result < 0 ) throw new OverFlowException
    else if(x < 0 && y <  0 && result > 0 ) throw new OverFlowException
    else result
  }

  def subract(x: Int, y: Int) = {
    val result = x - y
    if(x > 0 && y < 0 && result < 0 ) throw new OverFlowException
    else if(x < 0 && y >  0 && result < 0 ) throw new OverFlowException
    else result
  }

  def Multiply(x: Int, y: Int) = {
    val result = x * y
    if(x > 0 && y > 0 && result < 0 ) throw new OverFlowException
    else if(x < 0 && y <  0 && result < 0 ) throw new OverFlowException
    else if(x > 0 && y <  0 && result > 0 ) throw new OverFlowException
    else if(x < 0 && y >  0 && result < 0 ) throw new OverFlowException
    else result
  }

  println(PocketCalculator.add(Int.MaxValue,100))
}
