package com.kalash.m3.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalash.m3.AppData.DataFragment;
import com.kalash.m3.R;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.OnSelectedButtonListener;
import com.kalash.m3.Util.UpdateUI;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.SkeletonModel;
import io.rmiri.skeleton.master.SkeletonModelBuilder;
import io.rmiri.skeleton.utils.ConverterUnitUtil;

import static com.kalash.m3.Main.MainActivity.animationBtnUpdate;
import static com.kalash.m3.Main.MainActivity.getData;
import static com.kalash.m3.Main.MainActivity.mainDataNotNull;
import static io.rmiri.skeleton.master.SkeletonModel.SHAPE_TYPE_OVAL;


public class HomeFragment extends Fragment implements UpdateUI{

    private static Button setting;
    private static Button update;
    private boolean first_start_fragment;

    private DataFragment dataFragmentHome;
    private final String nameFragment = "homeFragment";

    private boolean press_btn_2 = false;

    private static TextView home_temp_div;
    private static TextView home_temp_mod;
    private static TextView home_humidity;
    private static TextView home_co2;
    private static TextView home_date;
    private static ImageView smile;
    private ConstraintLayout homeView;

    private SkeletonViewGroup skeletonHome;
    private ArrayList<SkeletonModel> homeModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        dataFragmentHome = new DataFragment(nameFragment);
        dataFragmentHome.getFile().deleteOnExit();
        first_start_fragment = true;
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeView = view.findViewById(R.id.homeView);

        setting = view.findViewById(R.id.setting_home);
        update = view.findViewById(R.id.update_home);
        setting.setRotation(MainActivity.animationBtnSetting.getAngle());

        home_temp_div = view.findViewById(R.id.home_temp_div);
        home_temp_mod = view.findViewById(R.id.home_temp_mod);
        home_humidity = view.findViewById(R.id.home_humidity);
        home_co2 = view.findViewById(R.id.home_co2);
        home_date = view.findViewById(R.id.home_date);
        smile = view.findViewById(R.id.smile);

        skeletonHome = new SkeletonViewGroup(getActivity().getApplicationContext());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(smile)
                .setShapeType(SHAPE_TYPE_OVAL)
                .build());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(home_temp_div)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                .build());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(home_temp_mod)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                .build());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(home_humidity)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                .build());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(home_co2)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                .build());

        homeModels.add(new SkeletonModelBuilder()
                .setChildView(home_date)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getContext(), 5))
                .build());





        skeletonHome.setSkeletonModels(homeModels);

        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        if (first_start_fragment){
            homeView.addView(skeletonHome, layout);
        }


        if(animationBtnUpdate.getStatus()){
            animationUpdateHome(getActivity().getApplicationContext(), true);
        }

        if(first_start_fragment != true){
            try {
                updateData(dataFragmentHome.onRecoveryFragment());
            }
            catch (Exception e){

            }
        }

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                if (setting.getRotation() == 180)
                    listener.onButtonSelected(0);
                else if (setting.getRotation() == 0) {
                    listener.onButtonSelected(1);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                listener.onButtonSelected(2);
            }
        });

        return view;
    }

    private void updateData(ArrayList<String> data) {
        ArrayList<String> homeData = new ArrayList<>();
        KeyValue keyValue = new KeyValue();

        if (mainDataNotNull){
            if(data == null){
                homeData = getData(keyValue.getPageHome());
                if(homeData != null){
                    first_start_fragment = false;
                    dataFragmentHome.onSaveFragment(homeData);
                }
            }

            else{
                homeData = data;
            }

            if(homeData != null){
                smile.setImageResource(Integer.valueOf(homeData.get(0)));
                home_temp_div.setText(homeData.get(1));
                home_temp_mod.setText(homeData.get(2));
                home_humidity.setText(homeData.get(3));
                home_co2.setText(homeData.get(4));
                home_date.setText(homeData.get(5));

            }

        }

    }


    public static void animationSettingHome(float angle) {
        setting.setRotation(angle);
    }
    public static void animationUpdateHome(Context context, boolean status) {
        Animation animRotateIn_big = AnimationUtils.loadAnimation(context,
                R.anim.rotate);
        if(status) {
            update.startAnimation(animRotateIn_big);
        }
        else {
            update.clearAnimation();
        }
    }


    @Override
    public void updateUI() {
        skeletonHome.finishAnimation();
        updateData(null);
    }

}

