package edu.huflit.app_music.APIServer;

import java.util.List;

import edu.huflit.app_music.Model.Artist;
import edu.huflit.app_music.Model.Listener;
import edu.huflit.app_music.Model.Song;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("nghesicurrent.php")
    Call<List<Artist>> getTopArtists();

    @GET("Song.php")
    Call<List<Song>> getTopSongs();

    @FormUrlEncoded
    @POST("laythongtinUser.php")
    Call<List<Listener>> thongtinnguoidung(@Field("username") String username);

    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<edu.huflit.app_music.Model.Response> login(@Field("Email") String Email, @Field("matkhau") String matkhau);

}
