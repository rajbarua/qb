package com.pj.console

//the companion object
object MyConsole extends App{
  def con = new MyConsole()
  def what = "start"
  def qa = con.question(what)
  con.answer(qa).act()
  Console.print("Press any key to exit...")
  scala.io.StdIn.readLine()
}

//the console class
class MyConsole {
  
  def question(what: String): QA = what match {
    case "start" => 
      Console.printf("""
[Question] What do you want to do?
[1] Read the PAD file?
[Respond (1)]:""")
      PAD()
    case _ => 
      Console.printf("Not sure what to ask") 
      Unsure()
  }
  
  def answer(qa: QA): Action = qa match {
    case PAD() => 
      def i = scala.io.StdIn.readInt().ensuring(i => i > 0 && i < 2)
      println(s"captured int is $i")
      PADAction()
    case _ => NoAction()
  }
}

