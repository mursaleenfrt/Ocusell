package com.ocusell.ocusellapp.projects.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;;
    private BaseActivity activity;
    private ArrayList<String> spCategoryList;
    private int mResource;

    public SpinnerAdapter(BaseActivity activity, int resource, ArrayList<String> spCategoryList) {
        super(activity, resource, spCategoryList);
        this.activity             = activity;
        mInflater                 = LayoutInflater.from(activity);
        this.spCategoryList = spCategoryList;
        this.mResource            = resource;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView tvCategory = (TextView) view.findViewById(R.id.tv_category_type);
        String category = spCategoryList.get(position);
        tvCategory.setText(category);
        return view;
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

}
