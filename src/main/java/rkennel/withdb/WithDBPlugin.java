package rkennel.withdb;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.execution.TaskExecutionGraph;

public class WithDBPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        registerTasks(project);
    }

    private void registerTasks(Project project) {

        for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
            project.getTasks().register(taskEnum.task,WithDBTask.class,taskEnum);
        }

        registerRuntimeDependencies(project);
    }

    private void registerRuntimeDependencies(Project project) {
        project.getGradle().getTaskGraph().whenReady(runtimeDependenciesAction(project));
    }

    private Action<TaskExecutionGraph> runtimeDependenciesAction(Project project) {
        return new Action<TaskExecutionGraph>() {
            @Override
            public void execute(TaskExecutionGraph taskExecutionGraph) {
                DependencyHandler dependencies = project.getDependencies();

                for (WithDBTaskEnum taskEnum : WithDBTaskEnum.values()) {
                    if (inTaskGraph(project, taskEnum)) {
                        String driver = taskEnum.driver;
                        dependencies.add("runtimeOnly", driver);
                        System.out.println(String.format("%s runtime dependency added", driver));
                    }
                }
            }

            private boolean inTaskGraph(Project project, WithDBTaskEnum taskEnum) {
                for (Task task : project.getGradle().getTaskGraph().getAllTasks()) {
                    if (taskEnum.task.equals(task.getName())) {
                        return true;
                    }
                }

                return false;
            }
        };
    }




}
