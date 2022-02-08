package org.cyrus.android.impl.view;

import org.cyrus.webserver.state.WebState;
import org.cyrus.webserver.user.User;

public class AndWebState implements WebState {

    private final AndCyrusUser user;

    public AndWebState(AndCyrusUser user){
        this.user = user;
    }
    @Override
    public AndCyrusUser getUser() {
        return this.user;
    }
}
