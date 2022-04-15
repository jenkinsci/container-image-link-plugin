package io.jenkins.plugins.functions;

import hudson.model.BuildBadgeAction;


public class HarborBadgeAction implements BuildBadgeAction {
    private String name;

    public HarborBadgeAction(String name) {
        this.name = name;
    }

    public String getUrlName() {
        return name;
    }

    public String getIconFileName() {
        return "/plugin/container-image-link/images/harbor-icon.png";
    }

    public String getDisplayName() {
        return "Container Image Link";
    }
}
