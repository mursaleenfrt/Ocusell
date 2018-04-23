package com.ocusell.ocusellapp.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.camera.ImageCaptureActivity;
import com.ocusell.ocusellapp.signup.SignUpActivity;
import com.ocusell.ocusellapp.utils.dialogs.ForgotPasswordDialog;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout llLogin;
    private TextView tvCreateAccount;
    private TextView tvForgotPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.signin_activity);
        init();
        setListeners();
    }

    private void setListeners() {
        llLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    private void init() {
        llLogin              = (LinearLayout) findViewById(R.id.ll_login);
        tvCreateAccount      = (TextView) findViewById(R.id.tv_create_account);
        tvForgotPassword     = (TextView) findViewById(R.id.tv_forgot_pass_id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                startActivity(new Intent(getApplicationContext(), ImageCaptureActivity.class));
                finish();
                break;
            case R.id.tv_create_account:
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                break;
            case R.id.tv_forgot_pass_id:
                ForgotPasswordDialog fd = new ForgotPasswordDialog(this);
                fd = fd.New(this, new ForgotPasswordDialog.OnClickCallback() {
                    @Override
                    public void onClick(String emailAddress) {
                        Toast.makeText(SignInActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                });
                fd.show();
                break;
        }
    }


}
