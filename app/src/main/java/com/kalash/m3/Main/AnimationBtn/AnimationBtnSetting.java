package com.kalash.m3.Main.AnimationBtn;


import com.kalash.m3.Main.MainActivity;

import static com.kalash.m3.Main.HomeFragment.animationSettingHome;
import static com.kalash.m3.Main.OutSideFragment.animationSettingOut;
import static com.kalash.m3.Main.WindFragment.animationSettingWind;

public class AnimationBtnSetting {

    private static float angle = 0;

    public void onAnimationSetting(float slideOffset){
        angle = (float)((int)(slideOffset*100)*1.8);

        if(MainActivity.HomeFragment.getId() != 0)
        animationSettingHome(angle);
        if(MainActivity.OutFragment.getId() != 0)
        animationSettingOut(angle);
        if(MainActivity.WindFragment.getId() != 0)
        animationSettingWind(angle);
    }

    public float getAngle() {
        return angle;
    }

}
