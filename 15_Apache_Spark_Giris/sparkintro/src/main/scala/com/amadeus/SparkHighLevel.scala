package com.amadeus
import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.log4j.{Logger, Level}

object SparkHighLevel {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .appName("SparkHighLevel")
      .master("local[4]")
      .getOrCreate()

    val df = spark.read.format("csv")
      .option("header", true)
      .option("inferSchema",true)
      .load("D:/Datasets/simple_data.csv")

    df.show(5)

    df.printSchema()

    //salaries greater than 4000
    df.filter(df.col("aylik_gelir").gt(4000)).show()

    df.filter(df.col("meslek").isNull).show()

    val df2 = df
      .withColumn("meslek_filled",
      F.when(df.col("meslek").isNull, "Unknown")
    .otherwise(df.col("meslek")))
      .drop("meslek")
      .withColumnRenamed("meslek_filled","meslek")
      //.show()

    // group by ops
    val df3 = df2.groupBy("meslek")
      .agg(F.mean("aylik_gelir").as("avg_salary"))
      .orderBy(F.desc("avg_salary"))
      //.show()

    df3.withColumn("avg_salary_formatted",
      F.format_number(df3.col("avg_salary"), 2))
      .show()



  }
}
