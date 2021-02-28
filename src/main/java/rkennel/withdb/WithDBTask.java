package rkennel.withdb;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class WithDBTask extends DefaultTask {

    private final TaskEnum taskEnum;

    @Inject
    public WithDBTask(TaskEnum taskEnum){
        this.taskEnum = taskEnum;
    }

    @TaskAction
    public void runTask(){
        DependencyHandler dependencies = this.getProject().getDependencies();
        String driver = taskEnum.driver;
        dependencies.add("runtimeOnly", driver);
        System.out.println(String.format("%s runtime dependency added",driver));
    }

}
