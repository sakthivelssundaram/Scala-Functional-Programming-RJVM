package basicOOP

import scala.language.postfixOps


object MethodanOperators extends App{

  class Person(val name: String, favouriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = favouriteMovie == movie
    def +(nickName: String) = new Person(s"$name ($nickName)", favouriteMovie,100 )
    def unary_+ : Person = new Person(name,favouriteMovie,age + 1)
    def learns(lear: String = "Scala") = s"$name learns $lear"
    def learnsScala:String = this learns "scala"
    def apply(): String = s"Hi my name is $name and I like $favouriteMovie"
    def apply(i: Int): String = s"Hi my name is $name and I watched my favourite movie $favouriteMovie $i times"

  }

  val mary = new Person("Mary","Inception",100)
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  val maryWithTitle = ""
  println((mary + "The new Women")(4))
  val newmary = +mary
  println(newmary.age)
  println(mary learnsScala)



}
