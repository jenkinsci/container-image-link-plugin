package io.jenkins.plugins.functions;


import hudson.model.Run;

public class HarborExecutor implements io.jenkins.plugins.interfaces.Executor {

    private Run build;
    private String url;

    public HarborExecutor(Run build, String url) {

        this.build = build;
        this.url = url;
    }

    public static String getStatus() {
        return "list";
    }

    @Override
    public void execute() throws Exception {
        HarborSerializer.passValues(build, url);
    }


}
