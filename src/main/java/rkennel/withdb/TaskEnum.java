package rkennel.withdb;

public enum TaskEnum {
    MYSQL("withMySql","mysql:mysql-connector-java:8.0.+"),
    POSTGRES("withPostgres","org.postgresql:postgresql:42.2.+");

    public final String task;
    public final String driver;

    TaskEnum(String task, String driver) {
        this.task = task;
        this.driver = driver;
    }
}
