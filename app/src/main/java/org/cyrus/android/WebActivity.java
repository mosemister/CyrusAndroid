package org.cyrus.android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import org.cyrus.android.impl.AndCyrusMain;
import org.cyrus.android.impl.view.context.AndCyrusRequestContext;
import org.cyrus.android.impl.view.AndCyrusWebViewClient;
import org.cyrus.android.impl.view.context.DataHandler;
import org.cyrus.webserver.request.RequestType;
import org.cyrus.webserver.request.WebRequest;

import java.io.IOException;
import java.util.Optional;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndCyrusMain main = new AndCyrusMain();
        main.init();

        AndCyrusRequestContext requestContext = new AndCyrusRequestContext(RequestType.GET, "/");
        Optional<WebRequest> opHandler = main.getServerManager().getRequestHandler(requestContext);
        if(!opHandler.isPresent()){
            System.err.println("Unknown handler");
            return;
        }

        try {
            opHandler.get().onRequest(requestContext);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Optional<DataHandler> opData = requestContext.getHandler();
        if(!opData.isPresent()){
            System.err.println("Unknown data");
            return;
        }

        WebView view = new WebView(this.getApplicationContext());
        WebViewClient client = new AndCyrusWebViewClient();
        view.setWebViewClient(client);
        WebSettings settings = view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        view.loadDataWithBaseURL("http://localhost/", opData.get().asString(), requestContext.getContentType(), "UTF-8", null);
        setContentView(view);
    }
}