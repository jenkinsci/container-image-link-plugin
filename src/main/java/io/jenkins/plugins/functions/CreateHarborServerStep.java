package io.jenkins.plugins.functions;

import com.google.inject.Inject;
import hudson.Extension;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

public class CreateHarborServerStep extends AbstractStepImpl {
    private String url;

    @DataBoundConstructor
    public CreateHarborServerStep(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static class Execution extends HarborSynchronousNonBlockingStepExecution<String> {
        private static final long serialVersionUID = 1L;
        private transient CreateHarborServerStep step;

        @Inject
        public Execution(CreateHarborServerStep step, StepContext context) throws IOException, InterruptedException {
            super(context);
            this.step = step;
        }

        @Override
        protected String runStep() throws Exception {
            HarborExecutor HarborExecutor = new HarborExecutor(build, step.getUrl());
            HarborExecutor.execute();
            return HarborExecutor.getStatus();
        }
    }

    @Extension
    public static final class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(CreateHarborServerStep.Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "newImageLink";
        }

        @Override
        public String getDisplayName() {
            return "Returns new Harbor server";
        }

        @Override
        public boolean isAdvanced() {
            return true;
        }
    }
}