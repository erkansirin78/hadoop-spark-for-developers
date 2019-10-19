package com.veribilimiokulu

import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.log4j.{Logger, Level}

object ReadFromKafka extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    .appName("ReadFromKafkaApp")
    .master("yarn")
  

}
