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

---

## Code Output:

```txt

########## RESULTSET VS ROWSET DEMO ##########


====== Demo of: ResultSet interface ======

Employee Record: #1 [baju123 / Baju Baja].
Skipping ahead -- Employee Record: #1.
Employee Record: #2 [balu123 / Balu Bala].
Skipping ahead -- Employee Record: #2.
Employee Record: #3 [begu123 / Begu Bega].
Skipping ahead -- Employee Record: #3.
Employee Record: #4 [beku123 / Beku Beka].
Skipping ahead -- Employee Record: #4.
Employee Record: #5 [raju123 / Raju Raja].
Skipping ahead -- Employee Record: #5.
Employee Record: #6 [rani123 / Rani Rana].
Skipping ahead -- Employee Record: #6.
Employee Record: #7 [kaju123 / Kaju Kishmish].
Skipping ahead -- Employee Record: #7.
Employee Record: #8 [kaur123 / Kaur Kent].
Skipping ahead -- Employee Record: #8.
Employee Record: #9 [ramu123 / Ramu Orange].
Skipping ahead -- Employee Record: #9.
Employee Record: #10 [raut123 / Raut Tata].
Skipping ahead -- Employee Record: #10.
Employee Record: #11 [kalu123 / Kalu Apple].
Skipping ahead -- Employee Record: #11.
Employee Record: #12 [kapu123 / Kapu Kant].
Deleting -- Employee Record: #12.


====== Demo of: RowSet interface ======



```
