package com.naracsinc.sidemu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
      WebView browser;
      EditText urledit;
      Button go,forward,back,clear,reload;
      ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.prg_bar);

        urledit=(EditText)findViewById(R.id.etxt_url);
         browser=(WebView)findViewById(R.id.wv_browser);
           go=(Button)findViewById(R.id.btn_go);
            forward=(Button)findViewById(R.id.btn_fwd);
             back=(Button)findViewById(R.id.btn_bck);
              clear=(Button)findViewById(R.id.btn_clr);
               reload=(Button)findViewById(R.id.btn_rsh);

        //Allows to display view in same in SiDeMu Browser not the default with progress Bar(Circle)
              browser.setWebViewClient(new ourViewClient());
              browser.setWebChromeClient(new WebChromeClient(){
                  @Override
                  public void onProgressChanged(WebView view, int newProgress) {
                      progressBar.setProgress(newProgress);
                        if(newProgress==100){
                            progressBar.setVisibility(view.GONE);
                        }else{
                            progressBar.setVisibility(view.VISIBLE);
                        }
                  }
              });

        //Allows to display JavaScript Content
                WebSettings webconf = browser.getSettings();
                 webconf.setJavaScriptEnabled(true);
        //Allows for keyboard to disapeared after input in EditText
        InputMethodManager inputmantoken=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
         inputmantoken.hideSoftInputFromInputMethod(urledit.getWindowToken(),0);

                  go.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                            String edittextvalue=urledit.getText().toString();
                                if(!edittextvalue.startsWith("http://"))
                                    edittextvalue="http://"+edittextvalue;
                            String url=edittextvalue;
                             browser.loadUrl(url);
                      }
                  });
         // Forward button Configuration
         forward.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                if(browser.canGoForward()) browser.goForward();
             }
         });
        // Back button Configuration
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(browser.canGoBack()) browser.goBack();
            }
        });
        // Clear button Configuration
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browser.clearHistory();
            }
        });
        // Reload button Configuration
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browser.reload();
            }
        });



    }
}
