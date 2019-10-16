package com.amadeus
import org.apache.spark.sql.{SparkSession}
import org.apache.log4j.{Logger, Level}
object SparkExample extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
/*
  println("Hello first Scala App!")

  var a = 12
  val b = 23
  a = 22

  val c = a + b

  println(s"I am c: $c")
  println("I am c: ", c)

  var cumle = "Ali ata bak."

  for (harf <- cumle){
    print(harf)
  }
println(" ")
  val emelinFisi = " Emel eve gel."

  val combineTwoStr = cumle.concat(emelinFisi)
  println(combineTwoStr)

  emelinFisi.length

  val myArr1 = Array(1,2,3,4,5)

  for (i <- myArr1){
    println(i)
  }

  myArr1(0) = 11
  myArr1.foreach(println)

  val myList1 = List(10, 20, 30,40,50)
  myList1.foreach(println)

  /**
   * myList1(0) = 100
   */

// function example
  def sumOfTwoInt(par1:Int, par2:Int):Int = {

    par1 + par2
  }

  val result = sumOfTwoInt(5,3)
  println(s"result: $result")

  // another way of defining function
  def sumOfTwoInt2 = (x:Int, y:Int) => x + y

  println("somOfTwoInt2", sumOfTwoInt2(100, 340))
  */

  val spark = SparkSession.builder()
    .appName("SparkExample")
    .master("local[4]")
    .getOrCreate()

  println(spark.version)

  val sc = spark.sparkContext
// create rdd from manually
  val sayilar = sc.parallelize(Seq(1,2,3,4,5,6,7,8))

  sayilar.take(2).foreach(println)


  // create rdd from text file
  val myText = sc.textFile("D:\\simple_text.txt")
  //myText.first().foreach(print)

  myText.filter(x => !x.contains("3")).take(3).foreach(println)
  println("map here")
  //myText.map(x => x.toUpperCase).foreach(println)
  myText.map(_.toUpperCase).foreach(println)  // _ is x => x

  //println("Flatmap here")
  myText.flatMap(x => x.split(" ")).foreach(println)


}
