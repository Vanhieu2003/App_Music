package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.app_music.R;

public class ArtistHomePageActivity extends AppCompatActivity {
    private TextView tvArtist;
    private boolean isLogin;
    private String userName;
    private androidx.appcompat.widget.AppCompatButton btnLogoutArtist,btn_insermusic_artist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_home_page);
        tvArtist = findViewById(R.id.tv_artist);
        btnLogoutArtist = findViewById(R.id.btn_logout_artist);

        btn_insermusic_artist=findViewById(R.id.btn_insertMusic_artist);
        btn_insermusic_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtistHomePageActivity.this,InsertMusicActivity.class);
                startActivity(intent);

            }
        });

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        isLogin = intent.getBooleanExtra("islogin", false);
        userName = intent.getStringExtra("userName");

        // Hiển thị nội dung dựa trên dữ liệu từ Intent
        if (isLogin) {
            tvArtist.setText("Xin chào, " + userName);
            btnLogoutArtist.setVisibility(View.VISIBLE);
            btnLogoutArtist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performLogout();
                }
            });
        } else {
            tvArtist.setText("Bạn chưa đăng nhập");
            btnLogoutArtist.setVisibility(View.GONE);
        }
    }

    private void performLogout() {
        // Thực hiện đăng xuất
        isLogin = false;
        userName = "";

        // Chuyển trở lại LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}