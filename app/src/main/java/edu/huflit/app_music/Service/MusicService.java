package edu.huflit.app_music.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

import edu.huflit.app_music.Model.Song;

public class MusicService extends Service {

    private final IBinder binder = new MusicBinder();
    private MediaPlayer mediaPlayer;
    private Song currentSong;
    private boolean isPlaying = false;
    private Handler handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void playSong(Song song) {
        if (song == null) return;

        if (currentSong != null && currentSong.equals(song)) {
            if (isPlaying) {
                pauseSong();
            } else {
                resumeSong();
            }
        } else {
            currentSong = song;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(currentSong.getBHUrl()));
                mediaPlayer.prepare();
                mediaPlayer.start();
                isPlaying = true;
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error playing song", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void pauseSong() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public void resumeSong() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public int getCurrentPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
