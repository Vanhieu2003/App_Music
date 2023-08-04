package edu.huflit.app_music.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.huflit.app_music.Model.Artist;
import edu.huflit.app_music.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private List<Artist> artistList;

    public ArtistAdapter(List<Artist> artistList) {
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        holder.bind(artist);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private ImageView imageView;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            imageView = itemView.findViewById(R.id.artistImageView);
        }

        public void bind(Artist artist) {
            nameTextView.setText(artist.getHoTenArtist());
            Glide.with(itemView.getContext())
                    .load(artist.getHinhArtist()) // Đường dẫn hoặc URL hình ảnh
                    .placeholder(R.drawable.theloai) // Hình ảnh tạm thời hiển thị khi đang tải
                    .error(R.drawable.theloai) // Hình ảnh hiển thị khi có lỗi tải
                    .into(imageView); // Hiển thị hình ảnh vào ImageView
        }
    }
}
