# Project: `04-callable-statement`

Covers usage of **CallableStatement** interface to execute SQL Stored Procedures using JDBC.

## Data for `employee` table:
Use below INSERT statement:

```sql
INSERT INTO `ch_labs_jdbc_01`.`employee`
(`username`,`password`,`fullname`,`address`,`salary`)
VALUES
('raju123', '1234', 'Raju Raja', 'Vasanthapura, Bengaluru', 10500),
('rani123', '1234', 'Rani Rana', 'Koramangala, Bengaluru', 12000),
('kaju123', '1234', 'Kaju Kishmish', 'Madiwala, Bengaluru', 11000),
('kaur123', '1234', 'Kaur Kent', 'Uttarahalli, Bengaluru', 13000),
('ramu123', '1234', 'Ramu Orange', 'Basavanagudi, Bengaluru', 14000),
('raut123', '1234', 'Raut Tata', 'Malleshwaram, Bengaluru', 11400),
('kalu123', '1234', 'Kalu Apple', 'Halasuru, Bengaluru', 14500),
('kapu123', '1234', 'Kapu Kant', 'Bommanahalli, Bengaluru', 13900);
```

## Create Stored Procedure:
Use `CREATE PROCEDURE` SQL statement to create a new stored procedure called `employees_nameStartsWith_salaryGte`
which returns records of employees whose `fullname` attribute starts with the string `name_prefix` and whose
`salary` is at least equal to or greater than `min_salary`.

```sql

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `employees_nameStartsWith_salaryGte`(IN name_prefix VARCHAR(20), IN min_salary INT)
BEGIN
	SELECT * FROM employee WHERE fullname LIKE CONCAT(name_prefix, '%') AND salary >= min_salary;
END$$
DELIMITER ;

```

