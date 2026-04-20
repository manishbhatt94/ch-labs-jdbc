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
