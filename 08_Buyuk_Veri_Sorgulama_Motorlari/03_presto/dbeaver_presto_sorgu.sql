select * from hive.azhadoop.orders limit 10;

select orderstatus, count(*) as sayi from hive.azhadoop.orders 
group by orderstatus
order by sayi desc