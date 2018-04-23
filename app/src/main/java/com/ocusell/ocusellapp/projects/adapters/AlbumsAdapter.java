package com.ocusell.ocusellapp.projects.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.AlbumActivity;
import com.ocusell.ocusellapp.projectsdetail.ProjectDetailActivity;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {


    private String[] categories;
    private Context context;
    private ActionCallback actionCallback;


    public AlbumsAdapter(Context context, String[] categories, ActionCallback actionCallback) {
        this.context        = context;
        this.categories     = categories;
        this.actionCallback = actionCallback;
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
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProjectDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView, iVShareProject;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView       = (ImageView) itemView.findViewById(R.id.iv_album_photo);
            iVShareProject  = (ImageView) itemView.findViewById(R.id.iv_share_project);
            imageView.setOnClickListener(this);
            iVShareProject.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_album_photo:
                    actionCallback.item_click();
                case R.id.iv_share_project:
                    actionCallback.share_project();
            }
        }

    }

    public interface ActionCallback {
        void share_project();
        void item_click();
    }

}
