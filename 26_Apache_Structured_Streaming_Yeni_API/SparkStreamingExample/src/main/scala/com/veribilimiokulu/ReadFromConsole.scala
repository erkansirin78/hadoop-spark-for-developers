package com.veribilimiokulu

import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.log4j.{Logger, Level}
object ReadFromConsole extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    //.master("local[4]")
    .master("yarn")
    .appName("ReadFromConsole")
    .getOrCreate()
  import spark.implicits._
  // input / source
  val lines = spark.readStream
    .format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .load()
  // Do the operation in here

  val words = lines.as[String].flatMap(x => x.split("\\W+"))

  val wordCount = words.groupBy("value").count()
    .orderBy(F.desc(("count")))



  // output / target
  val query = wordCount.writeStream
    .format("console")
    .outputMode("complete")
    .trigger(Trigger.ProcessingTime("1 seconds"))
    .start()

  query.awaitTermination()
}
