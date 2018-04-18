package com.ocusell.ocusellapp.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.ProjectsActivity;
import com.ocusell.ocusellapp.projects.adapters.SpinnerAdapter;
import com.ocusell.ocusellapp.projects.models.ProjectModel;
import com.ocusell.ocusellapp.utils.GeneralUtil;
import java.util.ArrayList;

public class CreateProjectDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener{


    private EditText etPName;
    private EditText etDescription;
    private Spinner spCategory;
    private EditText etCategoryName;
    private Button btnCancel;
    private Button btnOK;

    private String name;
    private String description;
    private String categoryName;
    private ArrayList<String> spCategoryList = new ArrayList<String>();
    private ProjectsActivity activity;
    private ClickCallback clickCallback;


    public CreateProjectDialog(Context context) {
        super(context);
    }

    public CreateProjectDialog New(ProjectsActivity activity, ClickCallback clickCallback){
        this.activity = activity;
        this.clickCallback = clickCallback;
        if(this.getWindow() != null){
            this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.create_project_popup_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = this.getWindow();
        if(window != null){
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

        init();
        setClickListeners();
        setupSpinner();
    }

    private void setupSpinner() {

        spCategoryList.add("Automotive");
        spCategoryList.add("Home");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Hotel");
        spCategoryList.add("Create New Category");
        SpinnerAdapter adapter = new SpinnerAdapter(activity,
                R.layout.project_spinner_item_layout, spCategoryList);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(this);

    }

    private void setClickListeners() {
        btnCancel.setOnClickListener(this);
        btnOK.setOnClickListener(this);
    }

    private void init() {
        etPName             = (EditText)    this.findViewById(R.id.et_project_name);
        etDescription       = (EditText)    this.findViewById(R.id.et_project_description);
        spCategory          = (Spinner)     this.findViewById(R.id.spinner_category);
        etCategoryName      = (EditText)    this.findViewById(R.id.et_category_name);
        btnCancel           = (Button)      this.findViewById(R.id.btn_cancel_project);
        btnOK               = (Button)      this.findViewById(R.id.btn_create_project);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel_project:
                GeneralUtil.loadClickAnimation(activity);
                CreateProjectDialog.this.dismiss();
                break;

            case R.id.btn_create_project:
                if(validate()){
                    ProjectModel pModel = new ProjectModel();
                    pModel.setName(name);
                    pModel.setDescription(description);
                    pModel.setCategory(categoryName);
                    GeneralUtil.loadClickAnimation(activity);
                    clickCallback.createProjectCallback(pModel);
                    CreateProjectDialog.this.dismiss();
                }
                break;
        }
    }

    private boolean validate() {

        name = etPName.getText().toString();
        description  = etDescription.getText().toString();

        if(spCategoryList.size() - 1 == spCategory.getSelectedItemPosition()){
            categoryName = etCategoryName.getText().toString();
        }else {
            categoryName = spCategoryList.get(spCategory.getSelectedItemPosition());
        }

        if(name.isEmpty() || description.isEmpty() || categoryName.isEmpty()){

            if(name.isEmpty()){
                etPName.setError("Please enter name.");
            }else if(description.isEmpty()){
                etDescription.setError("Please enter project description.");
            }else {
                etCategoryName.setError("Please enter category name.");
            }
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == spCategoryList.size()-1){
            etCategoryName.setVisibility(View.VISIBLE);
        }else {
            etCategoryName.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface ClickCallback{
        void createProjectCallback(ProjectModel projectModel);
    }

}