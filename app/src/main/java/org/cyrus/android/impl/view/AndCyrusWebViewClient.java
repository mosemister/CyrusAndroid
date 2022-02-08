package org.cyrus.android.impl.view;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import org.cyrus.android.impl.AndCyrusMain;
import org.cyrus.android.impl.view.context.AndCyrusRequestContext;
import org.cyrus.android.impl.view.context.DataHandler;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

public class AndCyrusWebViewClient extends WebViewClient {

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest (WebView view, WebResourceRequest request) {
        Uri url = request.getUrl();
        if (url.getPath() == null) {
            return super.shouldInterceptRequest(view, request);
        }
        AndCyrusRequestContext context = new AndCyrusRequestContext(RequestType.valueOf(request.getMethod()), url.getPath());
        Optional<WebRequest> opRequestHandler = AndCyrusMain.getInstance().getServerManager().getRequestHandler(context);
        if (! opRequestHandler.isPresent()) {
            return super.shouldInterceptRequest(view, request);
        }
        try {
            opRequestHandler.get().onRequest(context);
        } catch (IOException e) {
            e.printStackTrace();
            return super.shouldInterceptRequest(view, request);
        }
        Optional<DataHandler> opHandler = context.getHandler();
        if (! opHandler.isPresent()) {
            return super.shouldInterceptRequest(view, request);
        }
        ByteArrayInputStream is = new ByteArrayInputStream(opHandler.get().asString().getBytes());

        return new WebResourceResponse(context.getContentType(), "UTF-8", is);
    }
}
