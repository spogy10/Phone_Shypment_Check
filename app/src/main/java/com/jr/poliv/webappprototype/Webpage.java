package com.jr.poliv.webappprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webpage extends AppCompatActivity {

    WebView webView;

    SharedPreferences file;
    String url;
    String turl = "http://evision.utech.edu.jm/sipr/sits.urd/run/siw_lgn";
    String webAddress;
    boolean turl1 = false, turl2 = false, turl3 = false, turl4 = false, turl5 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //turl = "https://evision.utech.edu.jm/sipr/";
        setContentView(R.layout.activity_webpage); Log.d("Paul", "app starts");
        String mobileUserAgent = "Mozilla/5.0 (Linux; <Android Version>; <Build Tag etc.>) AppleWebKit/<WebKit Rev> (KHTML, like Gecko) Chrome/<Chrome Rev> Mobile Safari/<WebKit Rev>";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";





       webView = findViewById(R.id.webView);
       webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setUserAgentString(mobileUserAgent);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(turl5){
                    Log.d("Paul","turl5");
                    view.loadUrl("javascript:document.getElementsByName('BP103.DUMMY_B.MENSYS.1')[0].click();");
                    turl5 = false;
                    return;
                }

                if(turl4){
                    Log.d("Paul","turl4");
                    view.loadUrl("javascript:document.getElementsByName('REPORTS.DUMMY.MENSYS.1')[0].value = \"SIWSTU001\"; document.getElementsByName('RUN.DUMMY.MENSYS.1')[0].click();");
                    turl4 = false;
                    turl5 = true;
                    return;
                }

                if (view.getUrl().equals(turl)){
                    Log.d("Paul","turl");

                    view.loadUrl("javascript:document.getElementById('MUA_CODE.DUMMY.MENSYS').value = '1500747'; document.getElementById('PASSWORD.DUMMY.MENSYS').value = 'pass4utech'; document.getElementsByName('BP101.DUMMY_B.MENSYS.1')[0].click();");

                    turl1 = true;
                    return;
                }

                if(turl1){
                    Log.d("Paul","turl1");
                    view.loadUrl("javascript:document.getElementsByName('ANSWER.MUQ.MENSYS.1')[0].value = '050997'; document.getElementsByName('STORE.DUMMY_BUTT.MENSYS.1')[0].click();");
                    turl1 = false;
                    turl2 = true;
                    return;
                }

                if(turl2){
                    Log.d("Paul","turl2");
                    turl2 = false;
                    turl3 = true;
                }

                if(turl3){
                    Log.d("Paul","turl3");
                    view.loadUrl("javascript:document.getElementById('STU1').click();");
                    turl3 = false;
                    turl4 = true;
                    return;
                }


            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.d("Paul", "web error: 0"+error.toString());
            }
        });
        webView.loadUrl(turl);


    }


    private void getURL(){
        if(!file.contains(url)) {
            Intent intent = new Intent(Webpage.this, ChangeUrl.class);
            startActivity(intent);
        }
        else
            webAddress = getFromFile(url, "");
    }

    private String getFromFile(String variable, String defaultValue){
        return file.getString(variable, defaultValue);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.change_homepage) {

            Intent intent = new Intent(Webpage.this, ChangeUrl.class);
            startActivity(intent);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
