# DB Storage App

## Intro
Load data from a file, convert it to csv format and then insert it into the database which must be complaint with provided dump file. This means, the program will check for every entry in the file if it exists, and need to be updated. If not, it should create new record and if any record only exists in the db but not in the provided file, it should be deleted. 

## Requirements
- Java 17
- MySQL Workbench
- mySQL Server

If you want to configure different database, please refer application.properties file.

## Instruction to create database
open workbench (mysql server must be in running) or use cli tool to create a database:

open new Query tab from File Menu  : File->New Query Tab

Type following

```sql
create database empdb;
use empdb;
```

select all and press Ctrl+Enter (or choose run from the UI to execute commands)

## Two ways to run this application/utilty
## 1. Automated
Keep the (*required) Datatape file in the resource folder and execute the program.
This will do the parsing stuff on the fly and perform CRUD operations while reading individual lines in the file. 

## 2. Manual
Locate the file 'csv_converter.sh' from 'src/main/resources' and execute it in a shell terminal against given input file.

```bash
sh csv_converter.sh dataset

```

This will convert it to CSV file which should be then transfered to the resource folder of the application.

uncomment 'dService.saveRecordFromCSV();' in the 'DbStorageApplication.java file' and execute the program.

 [note:] You must choose either Autmated or Manual. So comment either vice versa. 


## Run App
This application is SpringBootApplication and hence required to run as "Spring Boot App" from your IDE such as Eclipse.

## application.properties
If tables and data need to be purged before insertion, set following:

```bash
spring.jpa.hibernate.ddl-auto=create-drop

```


If tables data only need to be updated, set following:


```bash
spring.jpa.hibernate.ddl-auto=update

```
 
