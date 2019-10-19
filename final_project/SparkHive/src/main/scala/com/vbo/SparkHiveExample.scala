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

  // I just need orderitemproductid, orderitemsubtotal, orderstatus
  val order_items_orders_df = spark.sql(
    """
      |select orderitemproductid, orderitemsubtotal, orderstatus from azhadoop.order_items oi
      |inner JOIN azhadoop.orders o
      |ON oi.orderitemorderid = o.orderid
      |""".stripMargin)

  println("order_items_orders_df:")
  order_items_orders_df.show(5)


  // I need productid, categoryname
  val products_categories_df = spark.sql(
    """
      SELECT productid, categoryname from azhadoop.products p
      INNER JOIN azhadoop.categories c
      ON p.productcategoryid = c.categoryid
      """.stripMargin)
    println("products_categories_df:")
  products_categories_df.show(5)


  val all_df = order_items_orders_df.join(products_categories_df,
    order_items_orders_df.col("orderitemproductid") === products_categories_df.col("productid"),
  "left")

  all_df.show()



  println("all_df count", all_df.count()) // 172.198

  val cancelled_df = all_df.filter("orderstatus = 'CANCELED' ")

  val result_df = cancelled_df.groupBy("categoryname")
    .agg(F.sum("orderitemsubtotal").as("total"))
    .orderBy(F.desc("total"))

  println("result_df")
  result_df.show()

  result_df
    .write
    .mode("overwrite")
    .saveAsTable("azhadoop.most_cancelled_catg")


}
