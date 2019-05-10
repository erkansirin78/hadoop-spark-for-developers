package paket1

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Logger, Level}
object SparkJarDeneme {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("SparkJarDeneme")
      .getOrCreate()

    val df = spark.read.format("csv")
      .option("header",true)
      .option("inferSchema", true)
      .option("sep",",")
      .load("D:\\Datasets\\simple_data.csv")

    df.show()
  }
}
