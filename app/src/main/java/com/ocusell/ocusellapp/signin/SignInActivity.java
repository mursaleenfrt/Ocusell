package com.ocusell.ocusellapp.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.camera.ImageCaptureActivity;
import com.ocusell.ocusellapp.signup.SignUpActivity;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class SignInActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llLogin;
    private TextView tvCreateAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        init();
        setListeners();
    }

    private void setListeners() {
        llLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
    }

    private void init() {
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        tvCreateAccount = (TextView) findViewById(R.id.tv_create_account);
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
        }
    }
}