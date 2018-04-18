package com.ocusell.ocusellapp.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.FitWindowsLinearLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.adapters.AlbumsAdapter;
import com.ocusell.ocusellapp.projects.adapters.CategoriesAdapter;
import com.ocusell.ocusellapp.projects.models.ProjectModel;
import com.ocusell.ocusellapp.utils.dialogs.CreateProjectDialog;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class ProjectsActivity extends BaseActivity implements View.OnClickListener {
    RecyclerView rvCategories, rvAlbums;
    Button btnCreateProject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects_activity);
        init();
        setClickListeners();
    }

    private void setClickListeners() {
        btnCreateProject.setOnClickListener(this);
    }

    private void init() {
        rvCategories     = (RecyclerView) findViewById(R.id.rv_categories);
        rvAlbums         = (RecyclerView) findViewById(R.id.rv_albums);
        btnCreateProject = (Button) findViewById(R.id.btn_create_project);
        setCategoryAdapter();
        setAlbumsAdapter();
    }

    private void setCategoryAdapter() {
        LinearLayoutManager layoutManagerCategory
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCategories.setLayoutManager(layoutManagerCategory);
        String[]categories = {"All", "Property", "Car","Office","Hotel"};
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categories);
        rvCategories.setAdapter(categoriesAdapter);
    }
    private void setAlbumsAdapter() {
        LinearLayoutManager layoutManagerAlbums
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAlbums.setLayoutManager(layoutManagerAlbums);
        String[]albums = {"All", "Property", "Car","Office","Hotel"};
        AlbumsAdapter categoriesAdapter = new AlbumsAdapter(this, albums);
        rvAlbums.setAdapter(categoriesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_project:
                CreateProjectDialog projectDialog = new CreateProjectDialog(this);
                projectDialog = projectDialog.New(this, new CreateProjectDialog.ClickCallback() {
                    @Override
                    public void createProjectCallback(ProjectModel projectModel) {
                        Toast.makeText(ProjectsActivity.this, "name "+ projectModel.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                projectDialog.show();
                break;
        }
    }
}
