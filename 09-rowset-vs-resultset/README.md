# Project: `09-rowset-vs-resultset`

## ResultSet

- ResultSet maintains active connection to DB while the ResultSet is being used.
- Since ResultSet is "ONLINE" with the DB, any updates made to the row which it
  points at, is directly reflected in the DB.
- ResultSet cannot be used directly for "OFFLINE" Data Manipulation & Access.
- This project has a demo of calling `resultSet.deleteRow()` and how this actually
  deletes that record from the DB.


## RowSet

- RowSet is a child interface of the ResultSet interface.
