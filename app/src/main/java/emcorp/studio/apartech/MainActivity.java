package emcorp.studio.apartech;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private WebView view;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        view = (WebView) findViewById(R.id.webView);
        pbar = (ProgressBar)findViewById(R.id.progressBar1);

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLoadsImagesAutomatically(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setSupportZoom(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setAppCacheEnabled(true);
        view.getSettings().setLoadsImagesAutomatically(true);
        view.getSettings().setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("http://apartech.net");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            pbar.setVisibility(View.VISIBLE);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            pbar.setVisibility(View.GONE);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //method goback(),untuk kembali ke halaman sebelumnya
            return true;
        }
        // Jika tidak ada halaman yang pernah dibuka
        // maka akan keluar dari activity (tutup aplikasi)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }
}