package com.vbo

import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.spark.{SparkConf}
import org.apache.log4j.{Level, Logger}
object SparkHiveExample extends App {
Logger.getLogger("org").setLevel(Level.WARN)

  val conf = new SparkConf()
    .setMaster("yarn")
    .setAppName("SparkHiveExampleApp")


  val spark = SparkSession.builder()
    .config(conf)
    .enableHiveSupport()
    .getOrCreate()

  import spark.implicits._

  val df = spark.sql("SELECT * FROM foodmart.department")
  df.show()

}
