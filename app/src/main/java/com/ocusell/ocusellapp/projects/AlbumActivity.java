package com.ocusell.ocusellapp.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.adapters.AlbumAdapter;
import com.ocusell.ocusellapp.projects.adapters.AlbumsAdapter;
import com.ocusell.ocusellapp.projects.models.Album;

import java.util.ArrayList;

public class AlbumActivity extends BaseActivity {
    private RecyclerView rvAlbum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_activity);
        init();
        setAlbumsAdapter();
    }

    private void init() {
        rvAlbum = (RecyclerView)findViewById(R.id.rv_album);
    }
    private void setAlbumsAdapter() {
        GridLayoutManager layoutManagerAlbums
                = new GridLayoutManager(this, 2);
        rvAlbum.setLayoutManager(layoutManagerAlbums);
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        albums.add(new Album("Property"));
        AlbumAdapter categoriesAdapter = new AlbumAdapter(this, albums);
        rvAlbum.setAdapter(categoriesAdapter);
    }
}
