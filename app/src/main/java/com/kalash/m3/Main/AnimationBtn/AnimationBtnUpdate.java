package com.kalash.m3.Main.AnimationBtn;

import android.content.Context;

import com.kalash.m3.Main.MainActivity;

import static com.kalash.m3.Main.HomeFragment.animationUpdateHome;
import static com.kalash.m3.Main.OutSideFragment.animationUpdateOut;
import static com.kalash.m3.Main.WindFragment.animationUpdateWind;

public class AnimationBtnUpdate{
    private static boolean status;


    public void onAnimationUpdate(boolean status, Context context) {
        this.status = status;
        if (MainActivity.HomeFragment.getId() != 0)
            animationUpdateHome(context, status);
        if (MainActivity.OutFragment.getId() != 0)
            animationUpdateOut(context, status);
        if (MainActivity.WindFragment.getId() != 0)
            animationUpdateWind(context, status);

    }
    public static boolean getStatus() {
        return status;
    }

}

