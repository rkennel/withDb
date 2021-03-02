package rkennel.withdb;

public enum WithDBTaskEnum {
    MYSQL("withMySql", "MySQL", "mysql:mysql-connector-java:8.0.+"),
    POSTGRES("withPostgres","PostgreSQL","org.postgresql:postgresql:42.2.+"),
    MSSQL("withMsSql","Microsoft SQL Server","com.microsoft.sqlserver:mssql-jdbc:9.2.0.jre8"),
    SQLITE("withSqlite","SQLite","org.xerial:sqlite-jdbc:3.34.+"),
    MONGODB("withMongo","MongoDB","org.mongodb:mongo-db-driver-sync:4.2.+"),
    MARIADB("withMaria","MariaDB","mysql:mysql-connector-java:8.0.+"),
    ORACLE("withOracle","Oracle","com.oracle.database.jdbc:ojdbc8:21.1.+"),
    DB2("withDb2","IBM DB2", "com.ibm.db2:jcc:11.+"),
    GOOGLE_MYSQL("withGoogleMySql","Google Cloud SQL - MySQL","com.google.cloud.sql:mysql-socket-factory:1.2.+"),
    GOOGLE_POSTGRES("withGooglePostgress", "Google Cloud SQL - PostgreSQL","com.google.cloud.sql:postgres-socket-factory:1.2.+"),
    GOOGLE_SPANNER("withGoogleSpanner","Google Cloud Spanner","com.google.cloud:google-cloud-spanner:4.0.+"),
    GOOGLE_BIGQUERY("withGoogleBigQuery","Google Cloud BigQuery", "com.google.cloud:google-cloud-bigquery:1.127.5"),
    H2("withH2","H2","com.h2database:h2:1.4.+");

    public final String task;
    public final String databaseName;
    public final String driver;

    WithDBTaskEnum(String task, String databaseName, String driver) {
        this.task = task;
        this.databaseName = databaseName;
        this.driver = driver;
    }
}



