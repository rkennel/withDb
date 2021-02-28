package rkennel.withdb;

public enum WithDBTaskEnum {
    MYSQL("withMySql","mysql:mysql-connector-java:8.0.+"),
    POSTGRES("withPostgres","org.postgresql:postgresql:42.2.+"),
    MSSQL("withMsSql","com.microsoft.sqlserver:mssql-jdbc:9.2.0.jre8"),
    SQLITE("withSqlite","org.xerial:sqlite-jdbc:3.34.+"),
    MONGODB("withMongo","org.mongodb:mongo-db-driver-sync:4.2.+"),
    MARIADB("withMaria","mysql:mysql-connector-java:8.0.+"),
    ORACLE("withOracle","com.oracle.database.jdbc:ojdbc8:21.1.+"),
    DB2("withDb2","com.ibm.db2:jcc:11.+"),
    GOOGLE_MYSQL("withGoogleMySql","com.google.cloud.sql:mysql-socket-factory:1.2.+"),
    GOOGLE_POSTGRES("withGooglePostgress","com.google.cloud.sql:postgres-socket-factory:1.2.+"),
    GOOGLE_SPANNER("withGoogleSpanner","com.google.cloud:google-cloud-spanner:4.0.+"),
    GOOGLE_BIGQUERY("withGoogleBigQuery","com.google.cloud:google-cloud-bigquery:1.127.5");

    public final String task;
    public final String driver;

    WithDBTaskEnum(String task, String driver) {
        this.task = task;
        this.driver = driver;
    }
}



