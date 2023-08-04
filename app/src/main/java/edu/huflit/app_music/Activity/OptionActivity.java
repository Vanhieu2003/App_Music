package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.app_music.R;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }

    public void onOptionLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onOptionSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void onOptionLoginArtist(View view) {
        Intent intent = new Intent(this, ArtistLoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onOptionLoginAdmin(View view) {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
    }
}