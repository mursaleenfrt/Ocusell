package com.ocusell.ocusellapp.projects.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.AlbumActivity;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    private String[] categories;
    private Context context;


    public AlbumsAdapter(Context context, String[] categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumsAdapter.ViewHolder holder, final int position) {
        String category = categories[position];
        holder.tvAlbumName.setText(category);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AlbumActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlbumName;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvAlbumName = (TextView) itemView.findViewById(R.id.tv_album_name);
            imageView = (ImageView) itemView.findViewById(R.id.iv_album_photo);
        }
    }
}
