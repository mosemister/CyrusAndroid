package org.cyrus.android.impl;

import org.cyrus.android.impl.view.AndCyrusUser;
import org.cyrus.android.impl.view.AndWebState;
import org.cyrus.webserver.CyrusWebServerManager;
import org.cyrus.webserver.request.WebRequest;
import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AndCyrusWebManager implements CyrusWebServerManager {

    private final Set<WebRequest> requests = new HashSet<>();
    private final AndWebState webState = new AndWebState(new AndCyrusUser());

    public AndWebState getWebState(){
        return this.webState;
    }

    public AndCyrusUser getUser(){
        return this.webState.getUser();
    }

    @Override
    public Set<User> getUsers() {
        return Collections.singleton(this.webState.getUser());
    }

    @Override
    public Set<WebState> getActiveStates() {
        return Collections.singleton(this.webState);
    }

    @Override
    public Set<WebRequest> getRequestHandlers() {
        return this.requests;
    }

    @Override
    @Deprecated
    public User register(InetSocketAddress address) {
        return this.webState.getUser();
    }

    @Override
    @Deprecated
    public void register(WebState state) {

    }

    @Override
    public void register(WebRequest request) {
this.requests.add(request);
    }

    @Override
    @Deprecated
    public WebState register(User user) {
        return this.webState;
    }
}
