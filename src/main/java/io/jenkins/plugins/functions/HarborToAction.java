package io.jenkins.plugins.functions;

import hudson.model.Run;

public class HarborToAction {
    public static void passValues(Run<?,?> build, String url) {
        synchronized (build.getAllActions()) {
            build.addAction(new HarborBadgeAction(url));
        }
    }
}
