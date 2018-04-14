package com.ocusell.ocusellapp.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ocusell.ocusellapp.R;

/**
 * Created by muhammad.mursaleen on 4/7/2018.
 */

public  class GeneralUtil {
    public static Animation loadClickAnimation(Context context)
    {
        return AnimationUtils.loadAnimation(context, R.anim.alpha);
    }
}
