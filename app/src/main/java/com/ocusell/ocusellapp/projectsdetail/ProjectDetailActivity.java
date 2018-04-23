package com.ocusell.ocusellapp.projectsdetail;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projectsdetail.adapter.MultiSelectAdapter;
import com.ocusell.ocusellapp.projectsdetail.model.SampleModel;
import com.ocusell.ocusellapp.utils.AlertDialogHelper;
import com.ocusell.ocusellapp.utils.GridSpacingItemDecoration;
import com.ocusell.ocusellapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;

public class ProjectDetailActivity extends AppCompatActivity implements AlertDialogHelper.AlertDialogListener{


    ActionMode mActionMode;
    Menu context_menu;

    RecyclerView recyclerView;
    MultiSelectAdapter multiSelectAdapter;
    boolean isMultiSelect = false;

    ArrayList<SampleModel> panaromasList = new ArrayList<>();
    ArrayList<SampleModel> multiselect_list = new ArrayList<>();

    AlertDialogHelper alertDialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.transparent_black_bg));
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle(getString(R.string.projects));
        }
        init();
        setClickListener();
    }

    private void setClickListener() {
        multiselect_list.add(new SampleModel("", ""));
        multiselect_list.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        panaromasList.add(new SampleModel("", ""));
        multiSelectAdapter = new MultiSelectAdapter(this, panaromasList,multiselect_list);
        // 3 columns
        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_16);
        Log.d("TAG", "spacing " + spacing);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(multiSelectAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect)
                    multi_select(position);
                else
                    Toast.makeText(getApplicationContext(), "Details Page", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                multiselect_list = new ArrayList<SampleModel>();
                isMultiSelect    = true;
                mActionMode      = startActionMode(mActionModeCallback);
                multi_select(position);
            }
        }));
    }

    private void init() {

        recyclerView = findViewById(R.id.recycler_view);

    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_multi_select, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(getApplicationContext(),"Delete Click",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_share:
                    Toast.makeText(getApplicationContext(),"Share Click",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };



    public void multi_select(int position) {
        if (mActionMode != null) {
            if (multiselect_list.contains(panaromasList.get(position)))
                multiselect_list.remove(panaromasList.get(position));
            else
                multiselect_list.add(panaromasList.get(position));

            if (multiselect_list.size() > 0)
                mActionMode.setTitle("" + multiselect_list.size());
            else
                mActionMode.setTitle("");

            refreshAdapter();

        }
    }

    public void refreshAdapter()
    {
        multiSelectAdapter.selected_usersList=multiselect_list;
        multiSelectAdapter.usersList= panaromasList;
        multiSelectAdapter.notifyDataSetChanged();
    }

    // AlertDialog Callback Functions

    @Override
    public void onPositiveClick(int from) {
        if(from==1)
        {
            if(multiselect_list.size()>0)
            {
                for(int i=0;i<multiselect_list.size();i++)
                    panaromasList.remove(multiselect_list.get(i));

                multiSelectAdapter.notifyDataSetChanged();

                if (mActionMode != null) {
                    mActionMode.finish();
                }
                Toast.makeText(getApplicationContext(), "Delete Click", Toast.LENGTH_SHORT).show();
            }
        }
        else if(from==2)
        {
            if (mActionMode != null) {
                mActionMode.finish();
            }

            SampleModel mSample = new SampleModel("Name"+ panaromasList.size(),"Designation"+ panaromasList.size());
            panaromasList.add(mSample);
            multiSelectAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onNegativeClick(int from) {

    }

    @Override
    public void onNeutralClick(int from) {

    }

}
