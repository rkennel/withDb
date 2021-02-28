package rkennel.withdb;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.tasks.TaskAction;

public class WithDBTaskDependencySetup extends DefaultTask {

    @TaskAction
    public void runTask() {
        DependencyHandler dependencies = this.getProject().getDependencies();

        for (TaskEnum taskEnum : TaskEnum.values()) {
            if (inTaskGraph(taskEnum)) {
                getProject().getTasks().getByName("compileJava").dependsOn(getProject().getTasks().getByName(taskEnum.task));

                String driver = taskEnum.driver;
                dependencies.add("runtimeOnly", driver);
                System.out.println(String.format("%s runtime dependency added", driver));
            }
        }
    }

    private boolean inTaskGraph(TaskEnum taskEnum) {
        for (Task task : getProject().getGradle().getTaskGraph().getAllTasks()) {
            if (taskEnum.task.equals(task.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getName() {
        return WithDBTaskDependencySetup.class.getName();
    }
}