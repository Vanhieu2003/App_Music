package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.app_music.MainActivity;
import edu.huflit.app_music.R;

public class AdminHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
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

    public void onOptionLogout_admin(View view) {
        Intent intent=new Intent(AdminHomePageActivity.this, OptionActivity.class);
        startActivity(intent);
        finish();
    }
}