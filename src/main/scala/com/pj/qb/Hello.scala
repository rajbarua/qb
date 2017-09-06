package com.pj.qb

//import com.intuit.oauth2.config.OAuth2Config._
//import com.intuit.oauth2.config.Environment
//import com.intuit.oauth2.config.Scope
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import scala.io._
import kantan.csv.engine._
import kantan.csv.java8._
import java.time.format.DateTimeFormatter
import java.time.LocalDate



object Hello extends Greeting with App {
  println(greeting)
  //val oauth2Config = new OAuth2ConfigBuilder("Q0hqjMkLPfJfNwDCzdJfRuUC86aZRFSgIB7jEJaunFxP48r04A", "LY6dsRtv1r1g3GzShl93NB1NEKBBr6U1Zwb8nbj6")
  //.callDiscoveryAPI(Environment.SANDBOX).buildConfig()
  //val csrf = oauth2Config.generateCSRFToken()
  //val scopes = List(Scope.OpenIdAll, Scope.Accounting)
  //oauth2Config.prepareUrl(scopes, redirectUri, csrf)
  
  
  //val reader = Source.fromFile("C:\\Users\\604890502\\Documents\\misc\\Pranjal\\Tax\\GST\\Pranjal_Indane_July_17_Sales_Basic.csv").mkString.asCsvReader[Row](rfc.withHeader)
  //reader.
  //val dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
  //implicit val dtDecoder: CellDecoder[LocalDate] = localDateDecoder(dtFormat)
  /*final case class Car(year: Int, make: String, model: String, desc: Option[String], price: Float)
  val rawData = """
Year,Make,Model,Description,Price
1997,Ford,E350,"ac, abs, moon",3000.00
1999,Chevy,"Venture ""Extended Edition"" ","",4900.00
1999,Chevy,"Venture ""Extended Edition, Very Large"" ",,5000.00
1996,Jeep,Grand Cherokee,"MUST SELL!
air, moon roof, loaded",4799.00
"""
  implicit val carDecoder: RowDecoder[Car] = RowDecoder.decoder(0, 1, 2, 3, 4)(Car.apply)
  val reader = rawData.asCsvReader[Car](rfc.withHeader)*/
  //reader.foreach(println _)
  
}

case class Row (date: String, txType: String, number: Int, customer: String, clazz: String, SKU: String, qty: Double, rate: Double, taxPerc: String, amount: Double, 
    tax: Double, taxable: Double)

trait Parser{
 
  def parse: List[Row]
}

class SalesDetailParser extends Parser {
   def stringToInt(s: String): Option[Int] = {
    try{
      Some(Integer.parseInt(s.trim()))
    }catch{
      case e: Exception => None
    }
  }
  implicit def stringToDouble(s: String): Option[Double] = {
    try{
      Some(s.trim.toDouble)
    }catch{
      case e: Exception => None
    }
  }
  
  def parse: List[Row] = {
    implicit val decoder = RowDecoder.ordered { (date: String, tx: String, number: String, customer: String, clazz: String, sku: String, qty: String, 
      rate: String, taxPrc: String, amount: String, tax: String, taxable: String) =>
      new Row(date, tx, stringToInt(number).getOrElse(0), customer, clazz, sku, qty.getOrElse(0), rate.getOrElse(0), taxPrc, amount.getOrElse(0), 
          tax.getOrElse(0), taxable.getOrElse(0))
    }
    List[Row]()
  }
}

trait Greeting {
  lazy val greeting: String = "hello...lets make a start"
}
