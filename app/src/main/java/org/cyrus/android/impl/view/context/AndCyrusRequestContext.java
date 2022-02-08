package org.cyrus.android.impl.view.context;

import org.cyrus.CyrusWeb;
import org.cyrus.android.impl.AndCyrusWebManager;
import org.cyrus.file.common.generic.AbstractSerializerObject;
import org.cyrus.webserver.request.ContentType;
import org.cyrus.webserver.request.RequestContext;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.state.WebState;

import java.io.IOException;
import java.util.Optional;

public class AndCyrusRequestContext implements RequestContext {

    private final RequestType type;
    private final String endPoint;

    private DataHandler handler;
    private String mimeType = ContentType.TEXT_HTML.getContentType();

    public AndCyrusRequestContext(RequestType type, String endPoint){
        this.type = type;
        this.endPoint = endPoint;
    }

    public String getContentType(){
        return this.mimeType;
    }

    public Optional<DataHandler> getHandler(){
        return Optional.ofNullable(this.handler);
    }


    @Override
    @Deprecated
    public WebState getSender() {
        return ((AndCyrusWebManager)CyrusWeb.getInstance().getServerManager()).getWebState();
    }

    @Override
    public RequestType getType() {
        return this.type;
    }

    @Override
    public String getEndpoint() {
        return this.endPoint;
    }

    @Override
    public AbstractSerializerObject getJson() throws IOException {
        return null;
    }

    @Override
    public void setJson(AbstractSerializerObject object) {

    }

    @Override
    public void setRaw(String value) {
        this.handler = new RawDataHandler(value);
    }

    @Override
    @Deprecated
    public void setHeader(String key, String value) {

    }

    @Override
    @Deprecated
    public void addHeader(String key, String value) {

    }

    @Override
    public void setContentType(ContentType type) {
        this.mimeType = type.getContentType();
    }
}
