package com.ocusell.ocusellapp.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projects.ProjectsActivity;
import com.ocusell.ocusellapp.projects.adapters.ExpandableListAdapter;
import com.ocusell.ocusellapp.projects.models.ProjectModel;
import com.ocusell.ocusellapp.utils.GeneralUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateProjectDialog extends Dialog implements View.OnClickListener{


    private EditText etPName;
    private EditText etDescription;
    private EditText etCategoryName;
    private Button btnCancel;
    private Button btnOK;

    private String name;
    private String description;
    private String categoryName;
    private List<String> spCategoryList = new ArrayList<String>();
    private ProjectsActivity activity;
    private ClickCallback clickCallback;

    private ExpandableListAdapter listAdapter;
    private ScrollView scrollView;
    private ExpandableListView explistCategories;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private DisplayMetrics metrics;
    private int width;


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
        prepareListData();
        listAdapter = new ExpandableListAdapter(activity, listDataHeader, listDataChild);
        explistCategories.setIndicatorBounds(width - GetDipsFromPixel(30), width - GetDipsFromPixel(10));
        explistCategories.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                parent.collapseGroup(0);
                if(childPosition == spCategoryList.size()-1){
                    etCategoryName.setVisibility(View.VISIBLE);
                }else {
                    etCategoryName.setVisibility(View.GONE);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT );
                scrollView.setLayoutParams(params);
                return false;
            }
        });
        explistCategories.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                if(parent.isGroupExpanded(groupPosition)){
                    if(spCategoryList.size()>3){
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400 );
                        scrollView.setLayoutParams(params);
                    }
                }else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT );
                    scrollView.setLayoutParams(params);
                }
                return false;
            }
        });
        explistCategories.setAdapter(listAdapter);
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild  = new HashMap<String, List<String>>();
        listDataHeader.add("Image Categories");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Automotive");
        spCategoryList.add("Home");
        spCategoryList.add("Hotel");
        spCategoryList.add("Create New Category");
        listDataChild.put(listDataHeader.get(0), spCategoryList);
    }

    private void setClickListeners() {
        btnCancel.setOnClickListener(this);
        btnOK.setOnClickListener(this);
    }

    private void init() {
        etPName             = (EditText)    this.findViewById(R.id.et_project_name);
        etDescription       = (EditText)    this.findViewById(R.id.et_project_description);
        scrollView          = (ScrollView) findViewById(R.id.scrollView);
        explistCategories   = (ExpandableListView) findViewById(R.id.list_exp_categories);
        etCategoryName      = (EditText)    this.findViewById(R.id.et_category_name);
        btnCancel           = (Button)      this.findViewById(R.id.btn_cancel_project);
        btnOK               = (Button)      this.findViewById(R.id.btn_create_project);
        metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
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
        if(spCategoryList.size() - 1 == explistCategories.getSelectedItemPosition()){
            categoryName = etCategoryName.getText().toString();
        }else {
            categoryName = spCategoryList.get(explistCategories.getSelectedItemPosition());
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


    public interface ClickCallback{
        void createProjectCallback(ProjectModel projectModel);
    }

    public int GetDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = activity.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
}