package playgrpupnd.GetProgramminginScala.Unit2_OPPS

object read extends App{
  import scala.io.Source

  def readFileIntoString(fileName: String) =
    Source.fromFile("C:\\Users\\sakthivel.s\\IdeaProjects\\untitled\\src\\main\\scala\\playgrpupnd\\GetProgramminginScala\\Unit2_OPPS\\"+fileName).getLines().mkString("\n")


  print(readFileIntoString("sampel.txt"))
}
