# JAR(s) Required for using HikariCP library for JDBC Connection Pooling

1. **HikariCP**:<br>
    - We will use version `3.1.0`.<br>
    - MVN Repository Link: [com.zaxxer/HikariCP/3.1.0](https://mvnrepository.com/artifact/com.zaxxer/HikariCP/3.1.0)
2. **SLF4J**:<br>
    (Since `org.slf4j.LoggerFactory` class is used by HikariCP)<br>
    - We will use version `1.7.5`.<br>
    - MVN Repository Link: [org.slf4j/slf4j-api/1.7.5](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/1.7.5)

## Download the Library JAR file
- Go to the provided (above) MVN Repository Link(s).
- Under the "Overview" tab, there is a "Files" line item.
- This has a button called "bundle" (if not then "jar"). Click this button.
- Clicking it, will start the download of the JAR file in your Downloads folder.


## Adding the JAR to the project manually

- Right-click the project, and select Properties from the context menu.
- From the Project Properties popup, select Java Build Path menu option from
the left side-bar.
- Then select the "Libraries" tab from the main section. This Libraries tab
represents the classpath.
- Now, the right-hand side has some buttons - select the "Add Library" button.
- This opens the "Add Library" popup. Select "User Library" from the list and
click Next.
- Now we see a list of previously created "User Libraries" - which can be empty
if we have not yet added any.
- If we have not previously added any external JAR to a user library, then
we will do that now.
- Click the "User Libraries..." button on the right-hand side.
- A new popup opens up. Here click the "New..." button from the right-hand side.
- Which opens a tiny dialog box "New User Library" - where we need to enter a
name for our library. Enter any name and click "OK" button. E.g. I used the
name "pool-hikaricp-3.1.0" and clicked "OK".
- And "slf4j-api-1.7.5" name for the SLF4J Library name.
- This creates a new entry with this name in the previous popup's list of
"Defined user libraries:" with the name we provided in previous step, i.e.
"pool-hikaricp-3.1.0".
- Select this "pool-hikaricp-3.1.0" item.
- And click on the "Add External JARs..." button from the right-hand side.
- Select the downloaded JAR from the file-picker.
- Then click "Apply and Close" button at the bottom of the popup.
- Click "Finish" button from the previous popup.
- Finally, click the "Apply and Close" button from the original project
properties "Java Build Path" popup.

---

# Program Output

Program's sample run output:

```txt
Reading configuration properties from .properties file...
Loaded Properties object -- {mysql.password=manish, mysql.url=jdbc:mysql://localhost:3306/ch_labs_jdbc_01, mysql.user=root}

Creating HikariConfig object using loaded configuration properties...
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Added configuration to HikariConfig object -- com.zaxxer.hikari.HikariConfig@6e0be858

Creating HikarDataSource object using the HikariConfig object...
Constructed (with config) new HikariDataSource object -- HikariDataSource (HikariPool-1)

Getting a Connection object from the pool managed by HikariDataSource...
Connection object: HikariProxyConnection@1122805102 wrapping com.mysql.cj.jdbc.ConnectionImpl@52f759d7
Connection with database: ch_labs_jdbc_01

Returned Connection object back to the pool.
```
