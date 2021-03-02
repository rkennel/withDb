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

        registerSetupClass(project);

        for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
            project.getTasks().register(taskEnum.task,WithDBTask.class,taskEnum);

            dependsOnSetupTask(project, taskEnum);
        }
    }

    private void dependsOnSetupTask(Project project, WithDBTaskEnum taskEnum) {
        Task newTask = project.getTasks().getByName(taskEnum.task);
        newTask.dependsOn(project.getTasks().getByName(WithDBTaskDependencySetup.class.getName()));
    }

    private void registerSetupClass(Project project) {
        project.getTasks().register(WithDBTaskDependencySetup.class.getName(),WithDBTaskDependencySetup.class);
        project.getTasks().forEach(task -> {
            if(notAWithDbTask(task.getName())){
                task.dependsOn(project.getTasks().getByName(WithDBTaskDependencySetup.class.getName()));
            }
        });
    }

    private boolean notAWithDbTask(String taskName) {
        for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
            if(taskName.equals(taskEnum.task)||taskName.equals(WithDBTaskDependencySetup.NAME)){
                return false;
            }
        }
        return true;
    }


}
