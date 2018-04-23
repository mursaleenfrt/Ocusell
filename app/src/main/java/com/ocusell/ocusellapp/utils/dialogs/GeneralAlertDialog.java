package com.ocusell.ocusellapp.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.utils.GeneralUtil;

public class GeneralAlertDialog extends Dialog {


    private TextView tvMsg;
    private Boolean msgCheck;
    private int msgColor;
    private String msgText;
    private TextView tvMsgDetail;
    private Boolean msgDetailCheck;
    private int msgDetailColor;
    private String msgDetailText;
    private Button btnCancelBtn;
    private String noText;
    private Button btnOk;
    private String yesText;
    private Context myContext;
    private OnClickCallback clickCallback;


    public GeneralAlertDialog(Context context) {
        super(context);
    }

    public GeneralAlertDialog New(Context myContext, Boolean msgCheck, int msgColor, String msgText,
                                  Boolean msgDetailCheck, int msgDetailColor, String msgDetailText,
                                  String noText, String yesText, OnClickCallback clickCallback){
        this.myContext = myContext;
        this.msgCheck  = msgCheck;
        this.msgColor  = msgColor;
        this.msgText   = msgText;
        this.msgDetailCheck  = msgDetailCheck;
        this.msgDetailColor  = msgDetailColor;
        this.msgDetailText   = msgDetailText;
        this.noText          = noText;
        this.yesText         = yesText;
        this.clickCallback   = clickCallback;
        if(this.getWindow() != null){
            this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.general_alert_dialog_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = this.getWindow();
        if(window != null){
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

        init();
        setClickListener();
    }

    private void setClickListener() {

        if(msgCheck){
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msgText);
            tvMsg.setTextColor(msgColor);
        }

        if(msgDetailCheck){
            tvMsgDetail.setVisibility(View.VISIBLE);
            tvMsgDetail.setText(msgDetailText);
            tvMsgDetail.setTextColor(msgDetailColor);
        }

        btnCancelBtn.setText(noText);
        btnOk.setText(yesText);
        btnCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtil.loadClickAnimation(myContext);
                GeneralAlertDialog.this.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GeneralUtil.loadClickAnimation(myContext);
                clickCallback.onClick("ok");
                GeneralAlertDialog.this.dismiss();
            }
        });
    }

    private void init() {
        tvMsg               = (TextView)     this.findViewById(R.id.tv_msg);
        tvMsgDetail         = (TextView)     this.findViewById(R.id.tv_msg_detail);
        btnCancelBtn        = (Button)       this.findViewById(R.id.btn_cancel);
        btnOk               = (Button)       this.findViewById(R.id.btn_ok);
    }

    public interface OnClickCallback {
        void onClick(String emailAddress);
    }

}
