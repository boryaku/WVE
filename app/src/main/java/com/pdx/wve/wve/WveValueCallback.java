package com.pdx.wve.wve;

import android.webkit.ValueCallback;

public class WveValueCallback implements ValueCallback<String> {

    public String result;

    @Override
    public void onReceiveValue(String value) {
        this.result = value;
    }
}
