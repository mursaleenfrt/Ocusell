package com.ocusell.ocusellapp.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.utils.GeneralUtil;

public class ForgotPasswordDialog extends Dialog {


    private EditText mEmailEt;
    private Context myContext;


    public ForgotPasswordDialog(Context context) {
        super(context);
    }

    public ForgotPasswordDialog New(Context myContext){
        this.myContext = myContext;
        if(this.getWindow() != null){
            this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.forgot_password_popup_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = this.getWindow();
        if(window != null){
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

        mEmailEt            = (EditText)     this.findViewById(R.id.email_et_id);
        Button mCancelBtn   = (Button) this.findViewById(R.id.cancel_btn_id);
        Button mOkBtn       = (Button) this.findViewById(R.id.send_btn_id);

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtil.loadClickAnimation(myContext);
                ForgotPasswordDialog.this.dismiss();
            }
        });

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailEt.getText().toString();
                if(!email.isEmpty() && email.contains("@")){
                    GeneralUtil.loadClickAnimation(myContext);
                    ForgotPasswordDialog.this.dismiss();

                }else {
                    mEmailEt.setError("Enter correct email id");
                }
            }

        });
    }

}