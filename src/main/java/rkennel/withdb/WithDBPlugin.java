package rkennel.withdb;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.invocation.Gradle;

public class WithDBPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        registerTasks(project);
    }

    private void registerTasks(Project project) {

        registerSetupClass(project);

        for (TaskEnum taskEnum : TaskEnum.values()) {
            project.getTasks().register(taskEnum.task,WithDBTask.class,taskEnum);

            dependsOnSetupTask(project, taskEnum);
        }
    }

    private void dependsOnSetupTask(Project project, TaskEnum taskEnum) {
        Task newTask = project.getTasks().getByName(taskEnum.task);
        newTask.dependsOn(project.getTasks().getByName(WithDBTaskDependencySetup.class.getName()));
    }

    private void registerSetupClass(Project project) {
        project.getTasks().register(WithDBTaskDependencySetup.class.getName(),WithDBTaskDependencySetup.class);
        project.getTasks().getByName("compileJava").dependsOn(project.getTasks().getByName(WithDBTaskDependencySetup.class.getName()));
    }


}
