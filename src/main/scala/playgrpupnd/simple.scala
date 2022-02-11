package playgrpupnd

object simple extends App{

  val a = Range(0,9).grouped(3)
  print(a.map(_.mkString(" ")).mkString("|"))


}
