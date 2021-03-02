package rkennel.withdb;

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    @TestFactory
    Collection<DynamicTest> allDrivers() {

        List<DynamicTest> tests = new LinkedList<>();
        for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
            tests.add(DynamicTest.dynamicTest(taskEnum.task, () -> {
                BuildResult result = GradleRunner.create()
                        .withProjectDir(testProjectDir.getRoot())
                        .withArguments(taskEnum.task)
                        .withPluginClasspath()
                        .build();

                assertThat(result.getTasks().contains(taskEnum.task));
                assertThat(result.getOutput()).contains(taskEnum.driver);
                System.out.println(result.getOutput());
            }));
        }

        return tests;
    }

    @Test
    public void doesNotIncludeUnrequestedDrivers() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(WithDBTaskEnum.MYSQL.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getTasks().contains(WithDBTaskEnum.MYSQL.task));
        assertThat(result.getTasks().contains(WithDBTaskEnum.POSTGRES.task)).isFalse();
    }

    @Test
    public void includesAllRequestedDrivers() {

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments(WithDBTaskEnum.MYSQL.task, WithDBTaskEnum.POSTGRES.task)
                .withPluginClasspath()
                .build();

        assertThat(result.getTasks().contains(WithDBTaskEnum.MYSQL.task));
        assertThat(result.getTasks().contains(WithDBTaskEnum.POSTGRES.task));
    }

    @Test
    public void runsBeforeBuildAndOnlyIncludesRequestedDrivers() throws IOException {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("build", WithDBTaskEnum.MYSQL.task)
                .withPluginClasspath()
                .build();

        System.out.println(result.getOutput());

        assertThat(result.getOutput()).contains("mysql:mysql-connector-java");
        assertThat(result.getOutput()).doesNotContain("org.postgresql:postgresql");
    }


}
