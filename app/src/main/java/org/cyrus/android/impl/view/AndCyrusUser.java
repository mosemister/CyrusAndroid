package org.cyrus.android.impl.view;

import org.cyrus.webserver.user.User;

import java.net.InetSocketAddress;

public class AndCyrusUser implements User {
    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(8500);
    }
}
