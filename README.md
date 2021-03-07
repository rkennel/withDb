# withDb
Gradle Plugin to add runtime dependencies for your favorite database

## Scenario
Often times the database that you use in production is not the same database that you use on your desktop due to the hassle 
of running a production representative database locally.  Many developers fix this problem by just including both jdbc drivers
in the gradle dependency.  This is (somewhat) wasteful and could also lead to driver conflicts.

## Solution
The WithDB plugin allows for the engineer to insert the gradle runtime dependency dynamically using your favorite gradle dependencies.

## Using the Plugin
In your build.gradle file, make sure to include this Plugin

**build.gradle**

`
plugins {
id 'java'
id 'rkennel.withdb' version '1.0.0'
}
`

Then just add your favorite db to your gradle script.  For example, to use MySQL.
`./gradlew bootRun withMySql`

Note: order of gradle tasks does not matter.

## Supported DBs
- H2 - withH2
- MySQL - withMySql
- Postgresql - withPostgres
- Micrsoft SQL Server - withMsSql
- SQLite - withSqlite
- MongoDB - withMongo
- MariaDB - withMaria
- Oracle - withOracle
- DB2 -withDb2
- Google Cloud SQL (MySQL) - withGoogleMySql
- Google Cloud SQL (Postgres) - withGooglePostgres
- Google Spanner - withGoogleSpanner
- Google BigQuery - withGoogleBigQuery
