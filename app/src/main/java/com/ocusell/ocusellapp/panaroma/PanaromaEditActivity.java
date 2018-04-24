package com.ocusell.ocusellapp.panaroma;

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
import com.ocusell.ocusellapp.utils.GeneralUtil;

public class PanaromaEditActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivCropPano, ivWowPano, ivBrightnessPano, ivRotatePano;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panaroma_edit);
        actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blackbg));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(getString(R.string.panaroma));
        }
        init();
        setClickListener();
    }

    private void setClickListener() {
        ivCropPano.setOnClickListener(this);
        ivWowPano.setOnClickListener(this);
        ivBrightnessPano.setOnClickListener(this);
        ivRotatePano.setOnClickListener(this);
    }

    private void init() {

        ivCropPano                = findViewById(R.id.iv_crop_panaroma);
        ivWowPano                 = findViewById(R.id.iv_wow_panaroma);
        ivBrightnessPano          = findViewById(R.id.iv_brightness_panaroma);
        ivRotatePano              = findViewById(R.id.iv_rotate_panaroma);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_crop_panaroma:
                ivCropPano.startAnimation(GeneralUtil.loadClickAnimation(this));
                buttonSelection(1);
                break;
            case R.id.iv_wow_panaroma:
                ivWowPano.startAnimation(GeneralUtil.loadClickAnimation(this));
                buttonSelection(2);
                break;
            case R.id.iv_brightness_panaroma:
                ivBrightnessPano.startAnimation(GeneralUtil.loadClickAnimation(this));
                buttonSelection(3);
                break;
            case R.id.iv_rotate_panaroma:
                ivRotatePano.startAnimation(GeneralUtil.loadClickAnimation(this));
                buttonSelection(4);
                break;
        }
    }

    private void buttonSelection(int i) {
        switch (i){
            case 1:
                ivCropPano.setImageResource(R.drawable.crop_white_icon);
                ivWowPano.setImageResource(R.drawable.wow_gray_icon);
                ivBrightnessPano.setImageResource(R.drawable.brightness_gray_icon);
                ivRotatePano.setImageResource(R.drawable.rotate_gray_icon);
                break;
            case 2:
                ivCropPano.setImageResource(R.drawable.crop_gray_icon);
                ivWowPano.setImageResource(R.drawable.wow_white_icon);
                ivBrightnessPano.setImageResource(R.drawable.brightness_gray_icon);
                ivRotatePano.setImageResource(R.drawable.rotate_gray_icon);
                break;
            case 3:

                ivCropPano.setImageResource(R.drawable.crop_gray_icon);
                ivWowPano.setImageResource(R.drawable.wow_gray_icon);
                ivBrightnessPano.setImageResource(R.drawable.brightness_white_icon);
                ivRotatePano.setImageResource(R.drawable.rotate_gray_icon);
                break;
            case 4:
                ivCropPano.setImageResource(R.drawable.crop_gray_icon);
                ivWowPano.setImageResource(R.drawable.wow_gray_icon);
                ivBrightnessPano.setImageResource(R.drawable.brightness_gray_icon);
                ivRotatePano.setImageResource(R.drawable.rotate_white_icon);
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
