package org.cyrus.android.impl.view.context;

import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class BootstrapHandler implements WebRequest {

    private final String[] endpoints;
    private final String resource;

    public BootstrapHandler(String path){
        this(path.substring(1), path);
    }

    public BootstrapHandler(String resource, String... endpoints){
        this.resource = resource;
        this.endpoints = endpoints;
    }

    @Override
    public RequestType getType() {
        return RequestType.GET;
    }

    @Override
    public String[] getEndPoints() {
        return this.endpoints;
    }

    @Override
    public void onRequest(RequestContext onRequest) throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.resource);
        if(is != null){
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String html = br.lines().collect(Collectors.joining());
            onRequest.setRaw(html);
            return;
        }
        onRequest.setStatus(404);
    }
}
