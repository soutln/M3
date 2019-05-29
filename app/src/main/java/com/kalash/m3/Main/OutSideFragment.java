package com.kalash.m3.Main;


import android.content.Context;
import android.os.Bundle;
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
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.R;
import com.kalash.m3.Util.OnSelectedButtonListener;
import com.kalash.m3.Util.UpdateUI;

import java.util.ArrayList;

import static com.kalash.m3.Main.MainActivity.animationBtnUpdate;
import static com.kalash.m3.Main.MainActivity.getData;
import static com.kalash.m3.Main.MainActivity.mainDataNotNull;


public class OutSideFragment extends Fragment implements UpdateUI {

    private static Button setting;
    private static Button update;

    private DataFragment dataFragmentOut;
    private final String nameFragment = "outFragment";
    private boolean first_start_fragment;

    public static TextView out_temp_div;
    public static TextView out_temp_mod;
    public static TextView out_humidity;
    public static TextView out_pressure;
    public static TextView out_date;
    public static ImageView img_weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_out_side, container, false);

        setting = view.findViewById(R.id.setting_out);
        setting.setRotation(MainActivity.animationBtnSetting.getAngle());
        update = view.findViewById(R.id.update_out);

        out_temp_div = view.findViewById(R.id.out_temp_div);
        out_temp_mod = view.findViewById(R.id.out_temp_mod);
        out_humidity = view.findViewById(R.id.out_humidity);
        out_pressure = view.findViewById(R.id.out_pressure);
        out_date = view.findViewById(R.id.out_date);
        img_weather = view.findViewById(R.id.weight);

        if(animationBtnUpdate.getStatus()){
            animationUpdateOut(getActivity().getApplicationContext(), true);
        }

        if(first_start_fragment != true){
            try {
                updateData(dataFragmentOut.onRecoveryFragment());
            }
            catch (Exception e){

            }

        }



        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                if(setting.getRotation() == 180)
                    listener.onButtonSelected(0);
                else if(setting.getRotation() == 0){
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
        ArrayList<String> outData = new ArrayList();
        KeyValue keyValue = new KeyValue();

        if (mainDataNotNull) {
            if (data == null) {
                outData = getData(keyValue.getPageOut());
                if (outData != null) {
                    first_start_fragment = false;
                    dataFragmentOut.onSaveFragment(outData);
                }
            } else {
                outData = data;
            }

            if (outData != null) {
                img_weather.setImageResource(Integer.valueOf(outData.get(0)));
                out_temp_div.setText(outData.get(1));
                out_temp_mod.setText(outData.get(2));
                out_humidity.setText(outData.get(3));
                out_pressure.setText(outData.get(4));
                out_date.setText(outData.get(5));

            }
        }
    }


    public static void animationSettingOut(float angle){
        setting.setRotation(angle);
    }

    public static void animationUpdateOut(Context context, boolean status) {
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
    public void onCreate(Bundle savedInstanceState) {
        dataFragmentOut = new DataFragment(nameFragment);
        dataFragmentOut.getFile().deleteOnExit();
        first_start_fragment = true;

        super.onCreate(savedInstanceState);

    }

    @Override
    public void updateUI() {
        updateData(null);
    }
}
