package com.ocusell.ocusellapp.projects.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocusell.ocusellapp.R;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {


    private String[] categories;
    private Context context;
    private TextView lastChecked = null;
    private int lastCheckedPos = 0;

    public CategoriesAdapter(Context context, String[] categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesAdapter.ViewHolder holder, final int position) {
        String category = categories[position];
        holder.textView.setText(category);
        holder.textView.setTag(new Integer(position));
        if (position == 0) {
            lastChecked = holder.textView;
            lastCheckedPos = 0;
            setDrawable(holder.textView, R.drawable.selected_filter_bg);
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

        }
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lastCheckedPos != position) {

                    setDrawable(holder.textView, R.drawable.selected_filter_bg);
                    holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));

                    setDrawable(lastChecked, R.drawable.project_filter_bg);
                    lastChecked.setTextColor(ContextCompat.getColor(context, R.color.project_filter_selected_clr));
                }
                lastChecked = holder.textView;
                lastCheckedPos = position;

            }
        });
    }

    private void setDrawable(TextView view, Integer drawable) {
        view.setBackground(null);
        view.setBackground(ContextCompat.getDrawable(context, drawable));

    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_category);
        }
    }
}
