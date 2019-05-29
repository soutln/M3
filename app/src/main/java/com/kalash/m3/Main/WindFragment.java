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
import com.kalash.m3.R;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.OnSelectedButtonListener;
import com.kalash.m3.Util.UpdateUI;

import java.util.ArrayList;

import static com.kalash.m3.Main.MainActivity.animationBtnUpdate;
import static com.kalash.m3.Main.MainActivity.getData;
import static com.kalash.m3.Main.MainActivity.mainDataNotNull;


public class WindFragment extends Fragment implements UpdateUI {

    private static Button setting;
    private static Button update;

    private boolean first_start_fragment;

    private DataFragment dataFragmentWind;
    private final String nameFragment = "windFragment";

    private ImageView wind_direction;
    private TextView wind_direction_text;
    private TextView wind_speed_div;
    private TextView wind_date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        dataFragmentWind = new DataFragment(nameFragment);
        dataFragmentWind.getFile().deleteOnExit();
        first_start_fragment = true;
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_wind, container, false);

        setting = view.findViewById(R.id.setting_wind);
        setting.setRotation(MainActivity.animationBtnSetting.getAngle());
        update = view.findViewById(R.id.update_wind);

        wind_direction = view.findViewById(R.id.wind_direction);
        wind_direction_text = view.findViewById(R.id.wind_direction_text);
        wind_speed_div = view.findViewById(R.id.wind_speed_div);
        wind_date = view.findViewById(R.id.wind_date);

        if(animationBtnUpdate.getStatus()){
            animationUpdateWind(getActivity().getApplicationContext(), true);
        }



        if(first_start_fragment != true){
            try {
                updateData(dataFragmentWind.onRecoveryFragment());
            }
            catch (Exception e){

            }
        }
        else
        {
            updateData(null);
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


  private void updateData(ArrayList<String> data){
        ArrayList<String> windData = new ArrayList<>();
        KeyValue keyValue = new KeyValue();
      if (mainDataNotNull) {
          if (data == null) {
              windData = getData(keyValue.getPageWind());
              if (windData != null) {
                  first_start_fragment = false;
                  dataFragmentWind.onSaveFragment(windData);
              }
          } else {
              windData = data;
          }

          if (windData != null) {
              dataFragmentWind.onSaveFragment(windData);
              wind_direction.setRotation(Integer.valueOf(windData.get(0)));
              wind_direction_text.setText(windData.get(1));
              wind_speed_div.setText(windData.get(2));
              wind_date.setText(windData.get(3));

          }
      }
  }



    public static void animationSettingWind(float angle){
      setting.setRotation(angle);
    }

    public static void animationUpdateWind(Context context, boolean status) {
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
        updateData(null);
    }
}
