# DB Storage App

## Requirements
- Java 17
- MySQL Workbench
- mySQL Server

If you want to configure different database, please refer application.properties file.

## Instruction to create database
open workbench (mysql server must be in running)

open new Query tab from File Menu  : File->New Query Tab

Type following

```sql
create database empdb;
use empdb;
```

select all and press Ctrl+Enter

## csv_converter.sh
Locate the file from src/main/resources folder and run this script as follows:

```bash
sh csv_converter.sh dataset.txt

```
against a text input file containing the data to be converted to csv format.
Make sure that dataset.csv (generated with this script) must be in the same folder (i.e, src/main/resources).

## Run App
This application is SpringBootApplication and hence required to run as "Spring Boot App" from your IDE such as Eclipse.
