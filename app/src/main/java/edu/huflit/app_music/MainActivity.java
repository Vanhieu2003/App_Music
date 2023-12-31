package edu.huflit.app_music;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import edu.huflit.app_music.Fragment.HomeFragment;
import edu.huflit.app_music.Fragment.LibraryMusicFragment;
import edu.huflit.app_music.Fragment.UserFragment;

public class MainActivity extends AppCompatActivity {
    private boolean isLogin;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nhận dữ liệu đăng nhập từ Intent
        Bundle bundle = getIntent().getBundleExtra("status");
        if (bundle != null) {
            isLogin = bundle.getBoolean("islogin", false);
            userName = bundle.getString("userName", "");
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        replaceFragment(new HomeFragment());

        bottomNavigationView.setSelectedItemId(R.id.homee);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homee:;
                        replaceFragment(new HomeFragment());
                        return true;
                    case R.id.library:;
                        replaceFragment(new LibraryMusicFragment());
                        return true;
                    case R.id.user:;
                        UserFragment userFragment = new UserFragment();
                        Bundle args = new Bundle();
                        args.putBoolean("islogin", isLogin);
                        args.putString("userName", userName);
                        userFragment.setArguments(args);
                        replaceFragment(userFragment);
                        return true;

                }
                return false;
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}