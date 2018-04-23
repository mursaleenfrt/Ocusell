package com.ocusell.ocusellapp.panaroma;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.camera.ImageCaptureActivity;
import com.ocusell.ocusellapp.projects.ProjectsActivity;
import com.ocusell.ocusellapp.utils.GeneralUtil;

public class PanaromaCreateActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivDeletePano, ivEditPano, ivFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panaroma_create);
        final ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.blackbg));
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle(getString(R.string.panaroma_image));
        }
        init();
        setClickListener();
    }

    private void setClickListener() {

        ivDeletePano.setOnClickListener(this);
        ivEditPano.setOnClickListener(this);
        ivFullScreen.setOnClickListener(this);

    }

    private void init() {

        ivDeletePano        = findViewById(R.id.iv_delete_panaroma);
        ivEditPano          = findViewById(R.id.iv_edit_panaroma);
        ivFullScreen        = findViewById(R.id.iv_full_screen__panaroma);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_panaroma:
                ivDeletePano.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
            case R.id.iv_edit_panaroma:
                ivEditPano.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
            case R.id.iv_full_screen__panaroma:
                ivFullScreen.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_done:
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
