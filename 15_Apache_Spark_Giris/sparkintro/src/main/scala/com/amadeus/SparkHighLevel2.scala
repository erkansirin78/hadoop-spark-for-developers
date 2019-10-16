package com.amadeus
import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.log4j.{Logger, Level}

object SparkHighLevel2 extends App {
Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    .appName("SparkHighLevel2")
    .master("local[4]")
    .getOrCreate()

  val df = spark.read.format("csv")
    .option("header","true")
    .option("inferSchema","true")
    .option("sep","|")
    .load("D:/simple_data_spark_write")

  df.show()

}
