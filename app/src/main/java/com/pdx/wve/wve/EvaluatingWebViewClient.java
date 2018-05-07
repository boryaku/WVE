package com.pdx.wve.wve;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Iterator;

public class EvaluatingWebViewClient extends WebViewClient {

    private final Iterator<String> evaluations;

    public EvaluatingWebViewClient(Iterator<String> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("WVE", "page finished event "+url);

        WveValueCallback quickTest = new WveValueCallback();
        view.evaluateJavascript("(function () {document.getElementsByName(\"q\")[0].value = 'hello'; return 'ok';})();", quickTest);

        if("ok".equalsIgnoreCase(quickTest.result)){
            Log.d("WVE", "result 1 ->" + doEvaluation(view, evaluations.next()));
            Log.d("WVE", "result 2 ->" + doEvaluation(view, evaluations.next()));
        }

    }

    private String doEvaluation(WebView view, String js){
        WveValueCallback valueCallback = new WveValueCallback();
        view.evaluateJavascript(js, valueCallback);

        return valueCallback.result;
    }

}
