package com.ocusell.ocusellapp.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.panaroma.PanaromaCreateActivity;
import com.ocusell.ocusellapp.projects.ProjectsActivity;
import com.ocusell.ocusellapp.utils.GeneralUtil;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class ImageCaptureActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivProjects, ivCapture, ivPanaromaCreate;
    private ImageButton ibSetting, ibGuidance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_capture_activity);
        init();
    }

    private void init() {
        ivProjects          = (ImageView) findViewById(R.id.iv_photos);
        ivCapture           = (ImageView) findViewById(R.id.iv_capture);
        ivPanaromaCreate    = (ImageView) findViewById(R.id.iv_panaroma_create);
        ibGuidance          = (ImageButton) findViewById(R.id.ib_guide);
        ibSetting           = (ImageButton) findViewById(R.id.ib_setting);
        ivCapture.setOnClickListener(this);
        ivPanaromaCreate.setOnClickListener(this);
        ivProjects.setOnClickListener(this);
        ibGuidance.setOnClickListener(this);
        ibSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_photos:
                ivProjects.startAnimation(GeneralUtil.loadClickAnimation(this));
                startActivity(new Intent(getApplicationContext(), ProjectsActivity.class));
                break;
            case R.id.iv_capture:
                ivCapture.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
            case R.id.ib_guide:
                ibGuidance.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
            case R.id.ib_setting:
                ibSetting.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
            case R.id.iv_panaroma_create:
                ivPanaromaCreate.startAnimation(GeneralUtil.loadClickAnimation(this));
                startActivity(new Intent(ImageCaptureActivity.this, PanaromaCreateActivity.class));
                break;
        }
    }

}
