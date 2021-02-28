package rkennel.withdb;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

public class WithDBPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        registerTasks(project);
    }

    private void registerTasks(Project project) {
        for (TaskEnum taskEnum : TaskEnum.values()) {
            project.getTasks().register(taskEnum.task,WithDBTask.class,taskEnum);
        }
    }

}
