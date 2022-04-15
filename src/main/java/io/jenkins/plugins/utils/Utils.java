package io.jenkins.plugins.utils;

import hudson.FilePath;
import hudson.model.Node;
import org.apache.commons.lang3.ObjectUtils;
import org.jenkinsci.plugins.workflow.actions.WorkspaceAction;
import org.jenkinsci.plugins.workflow.flow.FlowExecution;
import org.jenkinsci.plugins.workflow.graph.FlowGraphWalker;
import org.jenkinsci.plugins.workflow.graph.FlowNode;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.jenkinsci.plugins.workflow.steps.StepContext;

import java.io.IOException;

public class Utils {


    public static FilePath extractRootWorkspace(StepContext context, WorkflowRun build, FilePath cwd) throws IOException, InterruptedException {
        FilePath flowWorkspace = extractRootWorkspaceFromFlow(build.getExecution());
        if (flowWorkspace != null) {
            return flowWorkspace;
        }
        Node node = context.get(Node.class);
        if (node == null) {
            return cwd;
        }
        FilePath ws = node.getWorkspaceFor(build.getParent());
        return ObjectUtils.defaultIfNull(ws, cwd);
    }

    private static FilePath extractRootWorkspaceFromFlow(FlowExecution execution) {
        if (execution == null) {
            return null;
        }
        FlowGraphWalker flowWalker = new FlowGraphWalker(execution);
        FilePath rootPath = null;
        for (FlowNode node : flowWalker) {
            WorkspaceAction workspaceAction = node.getAction(WorkspaceAction.class);
            if (workspaceAction != null) {
                FilePath rootWorkspace = workspaceAction.getWorkspace();
                if (rootWorkspace != null) {
                    rootPath = rootWorkspace;
                }
            }
        }
        return rootPath;
    }

}
