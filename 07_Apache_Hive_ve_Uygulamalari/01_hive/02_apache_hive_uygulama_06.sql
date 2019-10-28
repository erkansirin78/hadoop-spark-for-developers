-- hive uygulama 6

use joindb;

show tables;

SELECT * from joindb.orders;

/*
customer_id|first_name|last_name |email              |address                  |city           |state|zipcode|
-----------|----------|----------|-------------------|-------------------------|---------------|-----|-------|
          1|George    |Washington|gwashington@usa.gov|3200 Mt Vernon Hwy       |Mount Vernon   |VA   |22121  |
          2|John      |Adams     |jadams@usa.gov     |1250 Hancock St          |Quincy         |MA   |02169  |
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |
          4|James     |Madison   |jmadison@usa.gov   |11350 Constitution Hwy   |Orange         |VA   |22960  |
          5|James     |Monroe    |jmonroe@usa.gov    |2050 James Monroe Parkway|Charlottesville|VA   |22902  |
*/

SELECT * from joindb.customers;
/*
order_id|order_date|amount |customer_id|
--------|----------|-------|-----------|
       1|07/04/1776|$234.56|          1|
       2|03/14/1760|$78.50 |          3|
       3|05/23/1784|$124.00|          2|
       4|09/03/1790|$65.50 |          3|
       5|07/21/1795|$25.50 |         10|
       6|11/27/1787|$14.40 |          9|

*/

-- INNER JOIN
select * from joindb.orders o join joindb.customers c 
on o.customer_id = c.customer_id
/*
customer_id|first_name|last_name |email              |address                  |city           |state|zipcode|order_id|order_date|amount |customer_id|
-----------|----------|----------|-------------------|-------------------------|---------------|-----|-------|--------|----------|-------|-----------|
          1|George    |Washington|gwashington@usa.gov|3200 Mt Vernon Hwy       |Mount Vernon   |VA   |22121  |       1|07/04/1776|$234.56|          1|
          2|John      |Adams     |jadams@usa.gov     |1250 Hancock St          |Quincy         |MA   |02169  |       3|05/23/1784|$124.00|          2|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       2|03/14/1760|$78.50 |          3|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       4|09/03/1790|$65.50 |          3|
*/

-- LEFT JOIN
select * from joindb.orders o left join joindb.customers c 
on o.customer_id = c.customer_id
/*
 * customers tablosunda olup da sipariþi olmayan müþterilerin bilgisi boþ kaldý (9 ve 10 numaralý müþteri)
 * 
customer_id|first_name|last_name |email              |address                  |city           |state|zipcode|order_id|order_date|amount |customer_id|
-----------|----------|----------|-------------------|-------------------------|---------------|-----|-------|--------|----------|-------|-----------|
          1|George    |Washington|gwashington@usa.gov|3200 Mt Vernon Hwy       |Mount Vernon   |VA   |22121  |       1|07/04/1776|$234.56|          1|
          2|John      |Adams     |jadams@usa.gov     |1250 Hancock St          |Quincy         |MA   |02169  |       3|05/23/1784|$124.00|          2|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       2|03/14/1760|$78.50 |          3|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       4|09/03/1790|$65.50 |          3|
          4|James     |Madison   |jmadison@usa.gov   |11350 Constitution Hwy   |Orange         |VA   |22960  |        |          |       |           |
          5|James     |Monroe    |jmonroe@usa.gov    |2050 James Monroe Parkway|Charlottesville|VA   |22902  |        |          |       |           |
 */

-- RIGHT JOIN
select * from joindb.orders o right join joindb.customers c 
on o.customer_id = c.customer_id

/*
 * Bu sefer tersi oldu 
 
customer_id|first_name|last_name |email              |address                  |city           |state|zipcode|order_id|order_date|amount |customer_id|
-----------|----------|----------|-------------------|-------------------------|---------------|-----|-------|--------|----------|-------|-----------|
          1|George    |Washington|gwashington@usa.gov|3200 Mt Vernon Hwy       |Mount Vernon   |VA   |22121  |       1|07/04/1776|$234.56|          1|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       2|03/14/1760|$78.50 |          3|
          2|John      |Adams     |jadams@usa.gov     |1250 Hancock St          |Quincy         |MA   |02169  |       3|05/23/1784|$124.00|          2|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       4|09/03/1790|$65.50 |          3|
           |          |          |                   |                         |               |     |       |       5|07/21/1795|$25.50 |         10|
           |          |          |                   |                         |               |     |       |       6|11/27/1787|$14.40 |          9|
 */

-- OUTER JOIN
select * from joindb.orders o full outer join joindb.customers c 
on o.customer_id = c.customer_id

/*
customer_id|first_name|last_name |email              |address                  |city           |state|zipcode|order_id|order_date|amount |customer_id|
-----------|----------|----------|-------------------|-------------------------|---------------|-----|-------|--------|----------|-------|-----------|
          1|George    |Washington|gwashington@usa.gov|3200 Mt Vernon Hwy       |Mount Vernon   |VA   |22121  |       1|07/04/1776|$234.56|          1|
          2|John      |Adams     |jadams@usa.gov     |1250 Hancock St          |Quincy         |MA   |02169  |       3|05/23/1784|$124.00|          2|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       4|09/03/1790|$65.50 |          3|
          3|Thomas    |Jefferson |tjefferson@usa.gov |931 Thomas Jefferson Pkwy|Charlottesville|VA   |22902  |       2|03/14/1760|$78.50 |          3|
          4|James     |Madison   |jmadison@usa.gov   |11350 Constitution Hwy   |Orange         |VA   |22960  |        |          |       |           |
          5|James     |Monroe    |jmonroe@usa.gov    |2050 James Monroe Parkway|Charlottesville|VA   |22902  |        |          |       |           |
           |          |          |                   |                         |               |     |       |       6|11/27/1787|$14.40 |          9|
           |          |          |                   |                         |               |     |       |       5|07/21/1795|$25.50 |         10|
*/


-- STREAMTABLE JOIN
select /*+ STREAMTABLE (o)*/ * from joindb.orders o join joindb.customers c 
on o.customer_id = c.customer_id


-- MAP JOIN
select /*+ MAPJOIN (c)*/ * from joindb.orders o join joindb.customers c 
on o.customer_id = c.customer_id


-- MAPJOIN HIVE OTOMATIK
set hive.auto.convert.join = true;
set hive.mapjoin.smalltable.filesize;


--4.3. Bucketed mapjoin 

set hive.input.format = org.apache.hadoop.ql.io.BucketizedHiveInputFormat;
set hive.optimize.bucketmapjoin=true;
set hive.auto.convert.sortmerge.join=true;
set hive.optimize.bucketmapjoin.sortedmerge=true;



-- views
create view vw_customers as select * from joindb.customers;

SELECT * from vw_customers;











