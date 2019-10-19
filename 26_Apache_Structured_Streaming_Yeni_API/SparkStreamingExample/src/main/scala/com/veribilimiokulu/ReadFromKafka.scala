package com.veribilimiokulu

import org.apache.spark.sql.{functions => F}
import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.streaming.Trigger

object ReadFromKafka extends App {
  Logger.getLogger("org").setLevel(Level.INFO)

  val spark = SparkSession.builder()
    .appName("ReadFromKafkaApp")
    //.master("local")  // lokal modda calistiracak siek
    .master("yarn")  // jar cikarip sandbox'ta calistiracak isek
    .getOrCreate()


  import spark.implicits._


  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "sandbox-hdp.hortonworks.com:6667")  // sandbox kafka sunucusu ve portu
    .option("subscribe", "deneme")
    .load()
/*
  // Ham dataframe'in key ve value sütunlarını stringe çevir
  val df2 = df.select($"key".cast(StringType), $"value".cast(StringType))

  // value sütununu boşluklarından ayır ve kelime sayısını tespit et.
  val df3 = df2.select("value").as[String]
    .flatMap(_.split("\\W+"))
    .groupBy("value").count()
    .sort(F.desc("count"))
*/
  // Streaming başlasın
  val query = df.writeStream
    .outputMode("append") //sorguda aggregation varsa complete
    .format("console")
    .start()

  query.awaitTermination()


}
