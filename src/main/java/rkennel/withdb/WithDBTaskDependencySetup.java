package rkennel.withdb;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;

public class WithDBTaskDependencySetup extends DefaultTask {

    public static final String NAME = WithDBTaskDependencySetup.class.getName();

    @TaskAction
    public void runTask() {
        DependencyHandler dependencies = this.getProject().getDependencies();

        for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
            if (inTaskGraph(taskEnum)) {
                getProject().getTasks().getByName("compileJava").dependsOn(getProject().getTasks().getByName(taskEnum.task));

                String driver = taskEnum.driver;
                dependencies.add("runtimeOnly", driver);
                System.out.println(String.format("%s runtime dependency added", driver));
            }
        }
    }

    private boolean inTaskGraph(WithDBTaskEnum taskEnum) {
        for (Task task : getProject().getGradle().getTaskGraph().getAllTasks()) {
            if (taskEnum.task.equals(task.getName())) {
                return true;
            }
        }

        return false;
    }

    @Internal
    @Override
    public String getName() {
        return NAME;
    }
}
