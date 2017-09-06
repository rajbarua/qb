package com.pj.console
import java.io.File
import scala.Console.CYAN
import scala.Console.RESET
import scala.io.Source
import com.github.tototoshi.csv._

sealed trait Action{
  def act(): Unit
}

case class PADAction() extends Action {
  def act(): Unit = {
    Console.printf(s"\n[Instruction] Copy the folder where the PAD csv file is located. For example ${RESET}${CYAN}C:\\Users\\604890502\\Documents\\ioc${RESET}: ")
    var dir = (new File(scala.io.StdIn.readLine()))
    if(dir.isDirectory()) dir else dir.getParentFile()
    //println(s"\nDEBUG: file read is dir: ${dir.isDirectory()}")
    val files =  dir.listFiles((dir: File, name: String) => name.endsWith(".csv"))
    println("\n[Instruction] Type the number against the file that should be processed")
    files.zipWithIndex.foreach{case (file, i) => println(s"[${i+1}]: ${file}")}
    readAndLoad(files(scala.io.StdIn.readInt().ensuring(i => i > 0 && i < files.size+1)-1))
    //println(s"DEBUG: Zipped files ${files.zipWithIndex}")
  }
  
  def readAndLoad(file: File): Unit = {
    val itr = CSVReader.open(file).iterator
    for(row <- itr.buffered)
      row match {
        case List("Gujarat SO", "", no, text, "Customer ECollection", date, "","","","0",credit,balance ) => println("case Customer ECollection")
		    case List("Gujarat SO", "", no, "AUTO TRANSFER SVTV Balan", "Inter-Customer Clrng", date, "","","",debit,"0",balance ) => println("case AUTO TRANSFER SVTV Balan")
		    case List("Gujarat SO", "LPG BP -Ahmedabad", no, text, "Billing doc.transfer", date, material,qty,"EA",debit,"0",balance ) => 
		      //println(s"purchase $material")
		      //itr.
        case _ => println("case default")
      }
    
    
  }
  //case class PADRow(cpnNm: String, plat: String, doc: Long, text: String, docType: String, docDate: Date, item: String, qty: Int, debit: Float, credit: Float, balance: Float)
}

case class NoAction() extends Action {
  def act(): Unit = {}
}

/*
val data = List(List("r1c1","r1c2","10"), List("r2c1","r2c2","20"), List("","","30"), List("","","40"), List("r5c1","r5c2","50"))

def recurse(prev: List[String], curr: List[List[String]]): Unit = curr match {
case Nil => println("Reached end of list")
case head @ List("","",qty) :: tail => println(s"child row with prev as $prev and head as ${head.head} and tail as $tail. ZIP is ${prev.zip(head.head).map{case (a,"") => a; case (a,b) => a+"|"+b; case _ => ""}}"); 
recurse(prev.zip(head.head).map{case (a,"") => a; case (a,b) => a+"|"+b; case _ => ""} ,tail)
case head :: tail => println(s"Processing row $head with prev as $prev"); recurse(head, tail)
}

recurse(null,data)
*/