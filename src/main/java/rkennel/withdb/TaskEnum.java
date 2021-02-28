package rkennel.withdb;

public enum TaskEnum {
    MYSQL("withMySql","mysql:mysql-connector-java"),
    POSTGRES("withPostgres","org.postgresql:postgresql");

    public final String task;
    public final String driver;

    TaskEnum(String task, String driver) {
        this.task = task;
        this.driver = driver;
    }
}
