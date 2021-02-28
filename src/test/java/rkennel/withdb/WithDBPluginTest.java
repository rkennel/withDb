package rkennel.withdb;

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WithDBPluginTest {

    TemporaryFolder testProjectDir;

    @BeforeEach
    public void setup() throws IOException {
        testProjectDir = new TemporaryFolder();
        testProjectDir.create();
        File buildFile = testProjectDir.newFile("build.gradle");

        String contents = "plugins {\n" +
                "    id 'java-gradle-plugin'\n" +
                "    id 'rkennel.withdb'\n" +
                "}\n" +
                "group = 'testing.plugin'\n" +
                "archivesBaseName = 'generated'\n" +
                "version = '1.0.0'\n\n" +
                "repositories {\n" +
                "    mavenCentral()\n" +
                "}\n\n"
                + "dependencies {\n" +
                "    implementation 'org.junit.jupiter:junit-jupiter-engine:5.7.1'\n" +
                "}";

        try (FileWriter fw = new FileWriter(buildFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(contents);

        }
    }

    @Test
    public void mysqlDriver() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(TaskEnum.MYSQL.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getOutput()).contains("mysql:mysql-connector-java");
    }

    @Test
    public void postgresqlDriver() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(TaskEnum.POSTGRES.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getOutput()).contains("org.postgresql:postgresql");
    }

    @Test
    public void doesNotIncludeUnrequestedDrivers() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(TaskEnum.MYSQL.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getOutput()).contains("mysql:mysql-connector-java");
        assertThat(result.getOutput()).doesNotContain("org.postgresql:postgresql");
    }

    @Test
    public void includesAllRequestedDrivers() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(TaskEnum.MYSQL.task, TaskEnum.POSTGRES.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getOutput()).contains("mysql:mysql-connector-java");
        assertThat(result.getOutput()).contains("org.postgresql:postgresql");
    }

    @Test
    public void runsBeforeBuild() throws IOException {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("build",TaskEnum.MYSQL.task)
                .withPluginClasspath()
                .build();

        System.out.println(result.getOutput());

    }


}
