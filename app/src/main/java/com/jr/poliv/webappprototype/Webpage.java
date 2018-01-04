package com.jr.poliv.webappprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webpage extends AppCompatActivity {

    WebView webView;

    SharedPreferences file;
    String file_name;
    String url;
    String turl = "https://evision.utech.edu.jm/sipr/sits.urd/run/siw_lgn";
    //String turl = "https://evision.utech.edu.jm/sipr/sits.urd/run/siw_lgn", turl1 = "https://evision.utech.edu.jm/sipr/sits.urd/run/SIW_LGN", turl2 = "https://evision.utech.edu.jm/sipr/sits.urd/run/SIW_PQS", turl3 = "", turl4 = "";
    String webAddress;
    boolean turl1 = false, turl2 = false, turl3 = false, turl4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);




        //String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";

//        file_name = getString(R.string.url_file_name);
//        url = getString(R.string.url);
//        file = this.getSharedPreferences(file_name, Context.MODE_PRIVATE);

        //getURL();
       webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        //webView.getSettings().setUserAgentString("ua");
        if (Build.VERSION.SDK_INT >= 11)
            webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(turl4){
                    Log.d("Paul","turl4");
                    view.loadUrl("javascript:document.getElementsByName('REPORTS.DUMMY.MENSYS.1')[0].value = \"SIWSTU001\"; document.getElementsByName('RUN.DUMMY.MENSYS.1')[0].click();");
                    turl4 = false;
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



                //view.loadUrl("javascript:document.getElementById('ofq').value = 'RS567871745NL';" + "document.getElementById('btnZoek-revised').click();");
            }
        });
        webView.loadUrl(turl);
        //webView.loadUrl("javascript:document.write(document.getElementById('fb-root'));");
    }


    private void getURL(){
        if(!file.contains(url)) {
            Intent intent = new Intent(Webpage.this, ChangeUrl.class);
            startActivity(intent);
        }
        else
            webAddress = getFromFile(file, url, "");
    }

    private String getFromFile(SharedPreferences file, String variable, String defaultValue){
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
