package org.cyrus.android.impl.view.context;

public class RawDataHandler implements DataHandler {

private final String data;

public RawDataHandler(String data){
    this.data = data;
}

    @Override
    public String asString() {
        return this.data;
    }
}
