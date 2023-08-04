package edu.huflit.app_music.Activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import edu.huflit.app_music.Model.Song;
import edu.huflit.app_music.R;

public class PlayMusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private Song currentSong;

    private ImageView songImageView;
    private TextView songNameTextView;
    private TextView artistNameTextView;
    private ImageView playPauseButton;
    private SeekBar seekBar;
    private TextView currentTimeTextView;
    private TextView totalTimeTextView;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        // Set up the custom toolbar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
       // Hide the default title

        // Handle the back button click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Get the Song object from the Intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentSong = (Song) extras.getSerializable("song");
        }

        // Initialize views
        songImageView = findViewById(R.id.songImageView);
        songNameTextView = findViewById(R.id.songTitleTextView);
        artistNameTextView = findViewById(R.id.artistname);
        playPauseButton = findViewById(R.id.playPauseButton);
        seekBar = findViewById(R.id.seekBar);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        totalTimeTextView = findViewById(R.id.totalTimeTextView);

        handler = new Handler();

        // Load and display song information
        if (currentSong != null) {
            songNameTextView.setText(currentSong.getTenBH());
            artistNameTextView.setText(currentSong.getTenArtist());
            Glide.with(this)
                    .load(currentSong.getHinhAnhUrl())
                    .placeholder(R.drawable.theloai)
                    .error(R.drawable.theloai)
                    .into(songImageView);
        }

        // Set click listener for the play/pause button
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    pauseSong();
                } else {
                    playSong();
                }
            }
        });

        // Set the listener for seekbar changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Play the song
        playSong();


    }
    private void playSong() {
        if (currentSong != null) {
            if (mediaPlayer == null) {
                // Create a new MediaPlayer if not already initialized
                Uri songUri = Uri.parse(currentSong.getBHUrl());
                mediaPlayer = MediaPlayer.create(this, songUri);

                // Set the maximum value for seekbar as the duration of the song
                seekBar.setMax(mediaPlayer.getDuration());
            }

            mediaPlayer.start();
            isPlaying = true;
            playPauseButton.setImageResource(R.drawable.ic_pause); // Change icon to pause

            // Start updating seekbar and time
            updateSeekBarAndTime();
        }
    }

    private void pauseSong() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            playPauseButton.setImageResource(R.drawable.ic_play); // Change icon to play
        }
    }

    private void updateSeekBarAndTime() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            currentTimeTextView.setText(getFormattedTime(mediaPlayer.getCurrentPosition()));
            totalTimeTextView.setText(getFormattedTime(mediaPlayer.getDuration()));

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateSeekBarAndTime();
                }
            }, 1000);
        }
    }

    private String getFormattedTime(int timeInMillis) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeInMillis),
                TimeUnit.MILLISECONDS.toSeconds(timeInMillis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillis)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
