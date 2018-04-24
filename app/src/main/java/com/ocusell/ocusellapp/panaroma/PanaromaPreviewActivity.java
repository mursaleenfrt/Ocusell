package com.ocusell.ocusellapp.panaroma;

import android.content.Intent;
import android.support.v4.view.ViewPager;
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
import com.ocusell.ocusellapp.panaroma.adapter.CustomPagerAdapter;
import com.ocusell.ocusellapp.utils.dialogs.GeneralAlertDialog;

import java.util.ArrayList;

public class PanaromaPreviewActivity extends AppCompatActivity implements View.OnClickListener, CustomPagerAdapter.OnItemClickListener, GeneralAlertDialog.OnClickCallback{

    ImageView ivDeletePano, ivEditPano, ivFullScreen;
    ViewPager viewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    ArrayList<String> listImages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panaroma_preview);
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
        listImages.add("");
//        listImages.add("");
        mCustomPagerAdapter = new CustomPagerAdapter(this, listImages,this);
        viewPager.setAdapter(mCustomPagerAdapter);
    }

    private void init() {

        viewPager           = findViewById(R.id.pager);
        ivDeletePano        = findViewById(R.id.iv_delete_panaroma);
        ivEditPano          = findViewById(R.id.iv_edit_panaroma);
        ivFullScreen        = findViewById(R.id.iv_full_screen__panaroma);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete_panaroma:
                ivDeletePano.startAnimation(GeneralUtil.loadClickAnimation(this));
                showDeleteDialog();
                break;
            case R.id.iv_edit_panaroma:
                ivEditPano.startAnimation(GeneralUtil.loadClickAnimation(this));
                moveToPanaromaEditActivity();
                break;
            case R.id.iv_full_screen__panaroma:
                ivFullScreen.startAnimation(GeneralUtil.loadClickAnimation(this));
                break;
        }
    }

    private void showDeleteDialog() {
        GeneralAlertDialog generalAlertDialog = new GeneralAlertDialog(PanaromaPreviewActivity.this);
        generalAlertDialog = generalAlertDialog.New(PanaromaPreviewActivity.this,
                true, R.color.alert_detail_text_color, getString(R.string.are_you_sure_you_want_to_delete_this_photo),
                false, 0, null,
                getString(R.string.cancel), getString(R.string.delete), this);
        generalAlertDialog.show();
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


    @Override
    public void onItemClick() {
        moveToPanaromaEditActivity();
    }

    private void moveToPanaromaEditActivity() {
        int currentPagePosition = viewPager.getCurrentItem();
        startActivity(new Intent(PanaromaPreviewActivity.this, PanaromaEditActivity.class));
    }

    // delete the panaroma
    @Override
    public void onActionClick(String emailAddress) {
        int currentPagePosition = viewPager.getCurrentItem();
        finish();
    }
}
