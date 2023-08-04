package edu.huflit.app_music.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import edu.huflit.app_music.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView splashImageView = findViewById(R.id.splashImageView);
        Glide.with(this).asGif().load(R.drawable.splashspotify1).into(splashImageView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,OptionActivity.class);
                startActivity(intent);
                finish();
            }
        },6000);
    }
}