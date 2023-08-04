package edu.huflit.app_music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.app_music.APIServer.APIService;
import edu.huflit.app_music.Activity.PlayMusicActivity;
import edu.huflit.app_music.Adapter.ArtistAdapter;
import edu.huflit.app_music.Adapter.SongAdapter;
import edu.huflit.app_music.Model.Artist;
import edu.huflit.app_music.Model.Song;
import edu.huflit.app_music.R;
import edu.huflit.app_music.Service.MusicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class HomeFragment extends Fragment implements SongAdapter.OnSongClickListener {

    private RecyclerView recyclerView, recyclerViewTopSongs;
    private ArtistAdapter artistAdapter;
    private SongAdapter songAdapter;



// Sử dụng currentSong và isPlaying để hiển thị trạng thái bài nhạc và mini player view trên các Fragment khác hoặc Activity khác.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.topArtistsRecycleView);
        recyclerViewTopSongs = view.findViewById(R.id.topSongsRecycleView);

        // Set LinearLayoutManager với hướng ngang (HORIZONTAL)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagersongs = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTopSongs.setLayoutManager(layoutManagersongs);
        // Gọi API để lấy danh sách Artist
        fetchTopArtists();

        // Gọi API để lấy danh sách bài hát
        fetchTopSongs();

        return view;
    }

    private void fetchTopArtists() {
        APIService.getService().getTopArtists().enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (response.isSuccessful()) {
                    List<Artist> artistList = response.body();

                    // Khởi tạo adapter và đổ dữ liệu vào RecyclerView
                    artistAdapter = new ArtistAdapter(artistList);
                    recyclerView.setAdapter(artistAdapter);
                } else {
                    // Xử lý lỗi khi không lấy được dữ liệu
                    Toast.makeText(getContext(), "Failed to fetch artists.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                // Xử lý lỗi kết nối
                Toast.makeText(getContext(), "Network error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchTopSongs() {
        APIService.getService().getTopSongs().enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                if (response.isSuccessful()) {
                    List<Song> songList = response.body();

                    // Khởi tạo adapter và đổ dữ liệu vào RecyclerView
                    songAdapter = new SongAdapter(getContext(), songList);
                    songAdapter.setOnSongClickListener(HomeFragment.this);
                    recyclerViewTopSongs.setAdapter(songAdapter);
                } else {
                    // Xử lý lỗi khi không lấy được dữ liệu
                    Toast.makeText(getContext(), "Failed to fetch songs.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                // Xử lý lỗi kết nối
                Toast.makeText(getContext(), "Network error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSongClick(Song song) {
        // Khi bạn click vào một bài hát trong danh sách, gửi thông tin bài hát vào Service
        Intent serviceIntent = new Intent(getContext(), MusicService.class);
        serviceIntent.putExtra("song", song);
        getActivity().startService(serviceIntent);

        // Chuyển tới trang PlayMusicActivity để hiển thị thông tin và điều khiển nhạc
        Intent playMusicIntent = new Intent(getContext(), PlayMusicActivity.class);
        playMusicIntent.putExtra("song", song);
        startActivity(playMusicIntent);
    }

    @Override
    public void onSongChanged(Song song, boolean isPlaying) {

    }

}