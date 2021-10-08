class Strudent (
  val firstName: String,
  val lastName: String,
  private var _quizzes: List[Int] = Nil,
  private var _assignment: List[Int] = Nil,
  private var _tests: List[Int] = Nil){
}

object Strudent{

  {
    println("Hai this is me Executine with out invoking")
    val S = Set(1,2,3,4,5,6,7,8,9,0)
    for (i <- S) println(i)
  }

  def apply(firstName: String, lastName: String, quizzes: List[Int] = Nil, assignment: List[Int] = Nil, tests: List[Int] = Nil): Strudent={
    new Strudent(firstName, lastName, quizzes,assignment,tests)
  }

  def apply(fileName: String) = {
    val source = io.Source.fromFile(fileName)
    val line = source.getLines()
    val firstName = line.next
    val lastname = line.next()
    val quizzes = line.next().split(",").map(_.toInt).toList
    val assignment = line.next().split(",").map(_.toInt).toList
    val tests = line.next().split(",").map(_.toInt).toList
    source.close()
    new Strudent(firstName,lastname,quizzes,assignment,tests)
  }

  def main(args: Array[String]) = {

    {
      val S = Set("a","B","C","D","E")
      for (i <- S) println(i)
    }
    val S1 = Strudent("Apple","Mango")
    println()
    println(S1.firstName +" "+ S1.lastName)

    val S2 = Strudent("C:\\Users\\sakthivel.s\\Sample - Scala code\\sampletextfile.txt")
    println(S2.firstName +" "+ S2.lastName)
  }
}