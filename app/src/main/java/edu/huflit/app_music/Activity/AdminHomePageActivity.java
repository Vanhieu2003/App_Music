package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.app_music.MainActivity;
import edu.huflit.app_music.R;

public class AdminHomePageActivity extends AppCompatActivity {
    private TextView tvAdmin;
    private boolean isLogin;
    private String userName;
    private androidx.appcompat.widget.AppCompatButton btnLogoutAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        tvAdmin = findViewById(R.id.tv_admin);
        btnLogoutAdmin = findViewById(R.id.btn_logout_admin);
        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("status");
        if (bundle != null) {
            isLogin = bundle.getBoolean("islogin", false);
            userName = bundle.getString("userName", "");
        }

        // Hiển thị nội dung dựa trên dữ liệu từ Intent
        if (isLogin) {
            tvAdmin.setText("Xin chào, " + userName);
            btnLogoutAdmin.setVisibility(View.VISIBLE);
            btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performLogout();
                }
            });
        } else {
            tvAdmin.setText("Bạn chưa đăng nhập");
            btnLogoutAdmin.setVisibility(View.GONE);
        }
    }

    private void performLogout() {
        // Thực hiện đăng xuất
        isLogin = false;
        userName = "";

        // Chuyển trở lại AdminLoginActivity
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void onOptionAccountArtist(View view) {
        Intent intent=new Intent(AdminHomePageActivity.this,SignUpArtistActivity.class);
        startActivity(intent);
        finish();
    }

    public void onOptionLogin_admin(View view) {
        Intent intent=new Intent(AdminHomePageActivity.this, MainActivity.class);
        startActivity(intent);
    }

}