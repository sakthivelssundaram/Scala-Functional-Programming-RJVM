package playgrpupnd

object PatternMatching extends App{

  /*
  Patterns can also be nested, e.g. this example matches a string pattern within a case class pattern:
   */

  case class Person(name:String, title: String)

  def greet(p: Person) = p match {
    case Person(s"$fName-$lName",title) => println(s"Hi $title $lName")
    case Person(name, title)   => println(s"Hello $title $name")
  }

  greet(Person("sakthivel-Somasundaram","Mr"))
  greet(Person("Somasundaram","Mr"))


  /*
  Patterns can be nested arbitrarily deeply. The following example matches string patterns, inside a case
class, inside a tuple:
   */

  def greetFamily(Husband: Person, wife: Person = Person("single","Not Married")) = (Husband, wife) match {
    case (Person(name, title),Person("single", "Not Married"))=> println(s"Hi $title Single $name")
    case (Person(s"$fName1 $lName1",_),Person(s"$fName2 $lName2",_)) if (lName2 == lName1) => println(s"Hello Mr & Mrs $lName1")
    case(Person(s"$name",_),Person(s"$name2",_)) => println(s"Hello $name and $name2")

  }

  greetFamily(Person("sakthivel Somasundaram", "Mr"), Person("Kiruthika Somasundaram", "Mrs"))
  greetFamily(Person("sakthivel Somasundaram", "Mr"), Person("Kiruthika shree", "Mrs"))
  greetFamily(Person("sakthivel Somasundaram", "Mr"))



}
