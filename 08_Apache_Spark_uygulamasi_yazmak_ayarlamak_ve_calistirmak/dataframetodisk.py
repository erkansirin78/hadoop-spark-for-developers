from pyspark.sql import SparkSession

spark = SparkSession.builder \
.master("yarn") \
.appName("DataframeToDisk") \
.getOrCreate()


df = spark.read.format("csv") \
.option("header",True) \
.option("sep", ",") \
.option("inferSchema",True) \
.load("hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/simple_data.csv")


df2 = df.withColumn("maas_carpi_yas", df.yas * df.aylik_gelir)

df2 \
.write \
.mode("overwrite") \
.option("sep",",") \
.option("header","True") \
.csv("/user/maria_dev/simple_data_transform")
