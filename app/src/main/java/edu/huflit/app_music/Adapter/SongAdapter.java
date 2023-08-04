package edu.huflit.app_music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.huflit.app_music.Model.Song;
import edu.huflit.app_music.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private final Context context;
    private List<Song> songList;
    private OnSongClickListener onSongClickListener;

    public SongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    public void setOnSongClickListener(OnSongClickListener listener) {
        this.onSongClickListener = listener;
    }

    public interface OnSongClickListener {
        void onSongClick(Song song);

        void onSongChanged(Song song, boolean isPlaying);
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView songImageView;
        private TextView songNameTextView;
        private TextView artistNameTextView;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songImageView = itemView.findViewById(R.id.songImageView);
            songNameTextView = itemView.findViewById(R.id.songNameTextView);
            artistNameTextView = itemView.findViewById(R.id.artistNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onSongClickListener != null) {
                        onSongClickListener.onSongClick(songList.get(position));
                    }
                }
            });
        }

        public void bind(Song song) {
            songNameTextView.setText(song.getTenBH());
            artistNameTextView.setText(song.getTenArtist());
            Glide.with(itemView.getContext())
                    .load(song.getHinhAnhUrl())
                    .placeholder(R.drawable.theloai)
                    .error(R.drawable.theloai)
                    .into(songImageView);
        }
    }
}
