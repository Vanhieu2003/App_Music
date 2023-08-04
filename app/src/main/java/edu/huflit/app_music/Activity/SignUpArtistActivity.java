package edu.huflit.app_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class SignUpArtistActivity extends AppCompatActivity {

    private EditText edt_email,edt_pass,edt_passcheck,edt_img,edt_hovaten,edt_sdt,edt_brith;
    private androidx.appcompat.widget.AppCompatButton btnsignup_artist_admin,btn_back_admin;
    private static final String url = "https://vanhieu260303.000webhostapp.com/Server/SignUpArtist.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_artist);
        edt_email=findViewById(R.id.edt_mail_signup_admin);
        edt_pass=findViewById(R.id.edt_pass_signup_admin);
        edt_passcheck=findViewById(R.id.edt_passcheck_signup_admin);
        edt_img=findViewById(R.id.edt_imgartist_admin);
        edt_hovaten=findViewById(R.id.edt_hovaten_admin);
        edt_sdt=findViewById(R.id.edt_sdt_admin);
        edt_brith=findViewById(R.id.edt_NgaySinh_artist_admin);
        btn_back_admin=findViewById(R.id.btn_back_signup_admin_artist);
        btn_back_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpArtistActivity.this, AdminHomePageActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnsignup_artist_admin=findViewById(R.id.btn_signup_admin);
        btnsignup_artist_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields())
                {
                    String email = edt_email.getText().toString();
                    if (isValidEmail(email)) {
                    register_artist(email,
                            edt_pass.getText().toString(),
                            edt_img.getText().toString(),
                            edt_hovaten.getText().toString(),
                            edt_sdt.getText().toString(),
                            edt_brith.getText().toString());
                    } else {
                        edt_email.setError("Email không hợp lệ");
                    }
                }
            }
        });

    }

    private void register_artist(final String email, final String password,final String img, final String hovaten,final String sdt, final String birth) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if (response.compareTo("Email đăng nhập đã tồn tại") == 0) {
                    edt_email.setText("");
                    edt_pass.setText("");
                    edt_passcheck .setText("");
                    edt_img.setText("");
                    edt_hovaten .setText("");
                    edt_sdt.setText("");
                    edt_brith .setText("");
                } else {
                    finishAndRemoveTask();
                    Intent i = new Intent(SignUpArtistActivity.this, AdminHomePageActivity.class);
                    startActivity(i);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("HoTenArtist", hovaten);
                map.put("Email", email);
                map.put("NgaySinh", birth);
                map.put("Sdt", sdt);
                map.put("MatKhau", password);
                map.put("HinhArtist", img);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private boolean validateFields() {
        int yourDesiredLength = 6;
        if (edt_pass.getText().length() < yourDesiredLength) {
            edt_pass.setError("Mật khẩu phải có ít nhất 6 kí tự");
            return false;
        } else if (!edt_pass.getText().toString().equalsIgnoreCase(edt_passcheck.getText().toString())) {
            edt_passcheck.setError("Xác nhận mật khẩu của bạn không đúng! Xin vui lòng nhập lại");
            return false;
        }

        int SDTLength = 10;
        if (edt_sdt.getText().length() < SDTLength) {
            edt_sdt.setError("Số điện thoại 10 số");
            return false;
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        // Sử dụng biểu thức chính quy để kiểm tra xem định dạng email có hợp lệ hay không
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}