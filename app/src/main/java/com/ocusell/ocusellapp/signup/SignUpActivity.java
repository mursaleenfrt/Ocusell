package com.ocusell.ocusellapp.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class SignUpActivity extends BaseActivity implements View.OnClickListener {


    private TextView tvSignIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        init();
    }

    private void init() {
        tvSignIn = (TextView)findViewById(R.id.tv_signin);
        tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_signin:
                finish();
                break;
        }
    }


}
