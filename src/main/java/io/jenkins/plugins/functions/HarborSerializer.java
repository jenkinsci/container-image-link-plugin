package io.jenkins.plugins.functions;

import hudson.model.Run;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class HarborSerializer implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void passValues(Run<?, ?> build, String URL){
        try {
            URL url = new URL(URL);
            URI uri =url.toURI();
            HarborToAction.passValues(build,uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ;
    }
}
