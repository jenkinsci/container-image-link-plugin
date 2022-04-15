package io.jenkins.plugins.functions;

import hudson.EnvVars;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import java.io.IOException;

public abstract class HarborSynchronousNonBlockingStepExecution<String> extends SynchronousNonBlockingStepExecution<String> {
    protected static final long serialVersionUID = 1L;

    protected transient TaskListener listener;
    protected transient Launcher launcher;
    protected transient WorkflowRun build;
    // The step's root workspace
    protected transient FilePath rootWs;
    // The step's working directory
    protected transient FilePath ws;
    protected transient EnvVars env;

    protected HarborSynchronousNonBlockingStepExecution(StepContext context) throws IOException, InterruptedException {
        super(context);
        this.listener = context.get(TaskListener.class);
        this.build = context.get(WorkflowRun.class);
        this.launcher = context.get(Launcher.class);
        this.ws = context.get(FilePath.class);
        this.rootWs = io.jenkins.plugins.utils.Utils.extractRootWorkspace(context, this.build, this.ws);
        this.env = context.get(EnvVars.class);
    }

    protected abstract String runStep() throws Exception;

    @Override
    protected String run() throws Exception {
        try {
            return runStep();
        } finally {
            listener.getLogger().flush();
        }
    }

}