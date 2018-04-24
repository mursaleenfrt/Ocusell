package com.ocusell.ocusellapp.panaroma.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.ocusell.ocusellapp.R;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter implements View.OnClickListener {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> listImages = new ArrayList<String>();
    OnItemClickListener onItemClickListener;

    public CustomPagerAdapter(Context context, ArrayList<String> listImages, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.onItemClickListener = onItemClickListener;
        this.listImages = listImages;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        imageView.setImageResource(R.drawable.room_image);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView){
            onItemClickListener.onItemClick();
        }
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

}