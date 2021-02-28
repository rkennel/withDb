package rkennel.withdb;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class WithDBPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        registerTasks(project);
    }

    private void registerTasks(Project project) {
        for (TaskEnum taskEnum : TaskEnum.values()) {
            project.getTasks().register(taskEnum.task,WithDBTask.class,taskEnum);

            Task newTask = project.getTasks().getByName(taskEnum.task);
            project.getTasks().getByName("assemble").dependsOn(newTask);
        }
    }

}
