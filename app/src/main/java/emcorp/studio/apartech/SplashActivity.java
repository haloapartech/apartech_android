package emcorp.studio.apartech;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_DURATION = 3500;
    ProgressBar progressBar;
    TextView tvPresentase;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        tvPresentase = (TextView) findViewById(R.id.tvPresentase);
        getSupportActionBar().hide();

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            tvPresentase.setText(String.valueOf(progressBar.getProgress())+"%");
                        }
                    });
                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        }, SPLASH_DISPLAY_DURATION);


    }
}
