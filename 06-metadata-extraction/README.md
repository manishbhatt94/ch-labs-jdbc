# Project: `06-metadata-extraction`

## JDBC MetaData Extraction

Covers how use JDBC APIs to extract about the DBMS & Driver combination
we are using.

We extract two (2) kinds of metadata, using the below 2 interfaces provided
by the JDBC API in the `java.sql` package:

1. **`DatabaseMetaData` interface**
	- For accessing metadata about the database and the driver.

1. **`ResultSetMetaData` interface**
	- For accessing metadata about a result set, i.e. a SELECT query, like
	columns information.

---

### Sample Program Run Output:

```txt

########## JDBC META-DATA EXTRACTION DEMO ##########


====== Demo of: DatabaseMetaData interface ======

JDBC URL: jdbc:mysql://localhost:3306/ch_labs_jdbc_01

Database UserName: root@localhost

Database Product Name: MySQL

Database Product Version: 8.0.43

Database Major Version: 8

Database Minor Version: 0

Driver Name: MySQL Connector/J

Driver Version: mysql-connector-j-8.2.0 (Revision: 06a1f724497fd81c6a659131fda822c9e5085b6c)

Driver Major Version: 8

Driver Minor Version: 2

Database Max UserName Length: 16

Database Max Columns in Table: 512

Database Max Row Size (bytes): 2147483639

Database Max Columns in Select: 256

Database Catalogs:
• ch_labs_jdbc_01
• information_schema
• jfsseptjava
• jfsseptkart
• laur_spilca_jpa_01
• mysql
• performance_schema
• retail_db
• sakila
• sys
• world

Database Schemas:

Database Catalogs:
• TABLE_CAT [jfsseptkart]; TABLE_SCHEM [null]; TABLE_NAME [product]; TABLE_TYPE [TABLE]; 
• TABLE_CAT [jfsseptkart]; TABLE_SCHEM [null]; TABLE_NAME [product_category]; TABLE_TYPE [TABLE]; 
• TABLE_CAT [jfsseptkart]; TABLE_SCHEM [null]; TABLE_NAME [product_delivery]; TABLE_TYPE [TABLE]; 
• TABLE_CAT [jfsseptkart]; TABLE_SCHEM [null]; TABLE_NAME [product_payment]; TABLE_TYPE [TABLE]; 
• TABLE_CAT [jfsseptkart]; TABLE_SCHEM [null]; TABLE_NAME [product_seq]; TABLE_TYPE [TABLE]; 


====== Demo of: ResultSetMetaData interface ======

Column Count: 6

Column Names:
• sn
• username
• password
• fullname
• address
• salary

Column Class Names:
• java.lang.Integer
• java.lang.String
• java.lang.String
• java.lang.String
• java.lang.String
• java.lang.Integer

Column Display Sizes:
• 10
• 30
• 30
• 100
• 200
• 10

Column Type Names (DB Specific):
• INT
• VARCHAR
• VARCHAR
• VARCHAR
• VARCHAR
• INT

Table Name: employee

Database Name: ch_labs_jdbc_01


```
