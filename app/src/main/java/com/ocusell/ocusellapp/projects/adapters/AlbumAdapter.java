package com.ocusell.ocusellapp.projects.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.models.Album;

import java.util.ArrayList;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private ArrayList<Album> albums;
    private Context context;


    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_grid_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumAdapter.ViewHolder holder, final int position) {
        Album album = albums.get(position);
//        holder.tvAlbumName.setText(album.getAlbumName());

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvAlbumName;

        public ViewHolder(View itemView) {
            super(itemView);
//            tvAlbumName = (TextView) itemView.findViewById(R.id.tv_al/bum_name);
        }
    }
}
