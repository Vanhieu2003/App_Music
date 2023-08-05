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

public class InsertMusicActivity extends AppCompatActivity {
    private EditText edt_BH,edt_BHUrl,edt_img_music,edt_mota,edt_id_nghesi;

    private androidx.appcompat.widget.AppCompatButton btn_insertMusic,btn_back_insertMusic;

    private static final String url = "https://vanhieu260303.000webhostapp.com/Server/InsertMusic.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_music);

        edt_BH=findViewById(R.id.edt_nameSong);
        edt_BHUrl=findViewById(R.id.edt_url_song);
        edt_img_music=findViewById(R.id.edt_img_song);
        edt_mota=findViewById(R.id.edt_mota_song);
        edt_id_nghesi=findViewById(R.id.edt_idartist_music);

        btn_back_insertMusic=findViewById(R.id.btn_back_insert_music);
        btn_back_insertMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(InsertMusicActivity.this,ArtistHomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_insertMusic=findViewById(R.id.btn_insert_music);
        btn_insertMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   insert_music(edt_BH.getText().toString(),
                                edt_BHUrl.getText().toString(),
                                edt_img_music.getText().toString(),
                                edt_mota.getText().toString(),
                                edt_id_nghesi.getText().toString());
                   }


        });

    }

    private void insert_music(final String nameSong, final String SongUrl,final String imgurl_song, final String mota,final String idNgheSi) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if (response.compareTo("Bài hát đã tồn tại") == 0) {
                    edt_BH.setText("");
                    edt_BHUrl.setText("");
                    edt_img_music .setText("");
                    edt_mota.setText("");
                    edt_id_nghesi .setText("");
                } else {
                    finishAndRemoveTask();
                    Intent i = new Intent(InsertMusicActivity.this, InsertMusicActivity.class);
                    startActivity(i);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Thêm bài hát thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("TenBH", nameSong);
                map.put("MoTa", mota);
                map.put("BHUrl", SongUrl);
                map.put("HinhAnhUrl", imgurl_song);
                map.put("MaArtist", idNgheSi);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }



}