package basicOOP

class Writer(val firstName: String, val lastName: String, val year: Int) {
  def fullname():String = this.firstName + " " + this.lastName
}
class Novel(val name: String, val YofReleas: Int, val author: Writer) {
  def authAge(): Int = this.YofReleas - author.year

  def isWrittenBy():String = this.author.fullname()

  def copy(newYofReleas: Int) = new Novel(this.name, newYofReleas,this.author)

  def booksDetails(): List[String] = {
    val bookName = this.name
    val author = this.isWrittenBy()
    val publishedYear = this.YofReleas
    val ageofAuth = this.authAge()

    return List(bookName,author, publishedYear.toString,ageofAuth.toString)
  }
}

class Counter(val n: Int=0) {
  def inc = {
    println("Incrementing")
    new Counter(n + 1)
  }
  def dec = {
    println("Decrementing")
    new Counter(n - 1)
  }

  def inc(nn: Int):Counter = if (nn <= 0) this else inc.inc(nn-1) //calls inc with no parameter first and calls inc with parameter
  def dec(nn: Int):Counter = if (nn <= 0) this else dec.dec(nn-1)//calls inc with no parameter first and calls inc with parameter

  def print = println(n)
}

object BasicOOp extends App{


  val jayamohan = new Writer("Bahuleyan","Jeyamohan",1962)
  val JK = new Writer("Jayakanthan","",1934)

  val venmurusu = new Novel("Venmurusu",2014,jayamohan)
  val omovou = new Novel("Oru man̲itan̲, oru vīṭu, oru ulakam",2007,JK)

  val count = new Counter
  count.print
  count.inc.inc.print
  count.dec(19).print
}
