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

public class SignUpActivity extends AppCompatActivity {
    private EditText edt_email,edt_pass,edt_passcheck,edt_username,edt_hovaten;
    private androidx.appcompat.widget.AppCompatButton btnsignup;
    private static final String url = "https://vanhieu260303.000webhostapp.com/Server/dangky.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        edt_email=findViewById(R.id.edt_mail_signup);
        edt_pass=findViewById(R.id.edt_pass_signup);
        edt_passcheck=findViewById(R.id.edt_passcheck_signup);
        edt_username=findViewById(R.id.edt_username);
        edt_hovaten=findViewById(R.id.edt_hovaten);
        btnsignup=findViewById(R.id.appcompat_signup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    String email = edt_email.getText().toString();
                    if (isValidEmail(email)) {
                        // Gọi hàm register_user khi định dạng email hợp lệ
                        register_user(email, edt_pass.getText().toString(), edt_username.getText().toString(), edt_hovaten.getText().toString());
                    } else {
                        edt_email.setError("Email không hợp lệ");
                    }
                }
            }
        });
    }



    private void register_user(final String email, final String password,final String username, final String hovaten) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if (response.compareTo("Email đăng nhập đã tồn tại") == 0) {
                    edt_email.setText("");
                    edt_pass.setText("");
                    edt_passcheck .setText("");
                    edt_username.setText("");
                    edt_hovaten .setText("");
                } else {
                    finishAndRemoveTask();
                    Intent i = new Intent(SignUpActivity.this, OptionActivity.class);
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
                map.put("Email", email);
                map.put("MatKhau", password);
                map.put("HoTenListener", hovaten);
                map.put("TenNguoiDung", username);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private boolean validateFields() {
        int yourDesiredLength = 6;
        if (edt_username.getText().length() < yourDesiredLength) {
            edt_username.setError("Tên đăng nhập phải có ít nhất 6 ký tự");
            return false;
        } else if (edt_pass.getText().length() < yourDesiredLength) {
            edt_pass.setError("Mật khẩu phải có ít nhất 6 kí tự");
            return false;
        } else if(!edt_pass.getText().toString().equalsIgnoreCase(edt_passcheck.getText().toString())){
            edt_passcheck.setError("Xác nhận mật khẩu của bạn không đúng! Xin vui lòng nhập lại");
            return false;
        }
        return true;
    }

        private boolean isValidEmail(String email) {
            // Sử dụng biểu thức chính quy để kiểm tra xem định dạng email có hợp lệ hay không
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
