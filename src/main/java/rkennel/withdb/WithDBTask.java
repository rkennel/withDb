package rkennel.withdb;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class WithDBTask extends DefaultTask {

    private final WithDBTaskEnum taskEnum;

    @Inject
    public WithDBTask(WithDBTaskEnum taskEnum){
        this.taskEnum = taskEnum;
    }

    @TaskAction
    public void runTask(){

    }
}
