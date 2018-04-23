package com.ocusell.ocusellapp.projectsdetail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.projectsdetail.model.SampleModel;

import java.util.ArrayList;


/**
 * Created by Jaison on 08/10/16.
 */

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MyViewHolder> {

    public ArrayList<SampleModel> usersList=new ArrayList<>();
    public ArrayList<SampleModel> selected_usersList=new ArrayList<>();
    private Context mContext;
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView IvAlreadyPurchased,IvMultiSelect, IvLiked;

         MyViewHolder(View view) {
            super(view);
            IvAlreadyPurchased = (ImageView) view.findViewById(R.id.iv_already_purchase);
            IvLiked            = (ImageView)view.findViewById(R.id.iv_like);

        }
    }


    public MultiSelectAdapter(Context context,ArrayList<SampleModel> userList,ArrayList<SampleModel> selectedList) {
        this.mContext=context;
        this.usersList = userList;
        this.selected_usersList = selectedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_detail_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SampleModel movie = usersList.get(position);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

