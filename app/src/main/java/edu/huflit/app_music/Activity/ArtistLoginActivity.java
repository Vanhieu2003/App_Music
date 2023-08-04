package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import edu.huflit.app_music.R;

public class ArtistLoginActivity extends AppCompatActivity {
    private EditText edt_mail, edt_pass;
    private androidx.appcompat.widget.AppCompatButton btnlogin;

    private boolean accept = false;
    static String Email;
    static Boolean isLogin = false;
    private static final String url = "https://vanhieu260303.000webhostapp.com/Server/LoginArtist.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_login);

        edt_mail = findViewById(R.id.edt_mail);
        edt_pass = findViewById(R.id.edt_pass);

        edt_mail = findViewById(R.id.edt_mail);
        edt_pass = findViewById(R.id.edt_pass);
        btnlogin=findViewById(R.id.appcompatlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_artist(edt_mail.getText().toString(), edt_pass.getText().toString());
            }
        });
    }

    public void login_artist(final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if(response.compareTo("Đăng nhập thành công")==0){
                    isLogin = true;
                    Email= email;
                    Intent intent = new Intent(ArtistLoginActivity.this, ArtistHomePageActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putBoolean("islogin",isLogin);
                    bundle.putString("userName",Email);
                    intent.putExtra("status",bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Email", email);
                map.put("MatKhau", password);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}