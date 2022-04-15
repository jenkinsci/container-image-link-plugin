package io.jenkins.plugins.functions;

import hudson.model.Run;

import java.io.Serializable;

public abstract class HarborSerializer implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void passValues(Run<?, ?> build, String URL){
        HarborToAction.passValues(build,URL);
    }
}
