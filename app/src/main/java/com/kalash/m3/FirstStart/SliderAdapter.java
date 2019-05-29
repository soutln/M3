package com.kalash.m3.FirstStart;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalash.m3.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public static int[] slide_image_res = {
            R.drawable.ic_app,
            R.drawable.ic_icon_co2,
            R.drawable.ic_wind_first_start,
            R.drawable.ic_icon_co2,
            R.drawable.ic_overcast
    };

    public static int[] slide_text_res = {
          R.string.fragment_first_start_one,
          R.string.fragment_first_start_second,
          R.string.fragment_first_start_third,
          R.string.fragment_first_start_fourth,
          R.string.fragment_first_start_fifth
    };

    @Override
    public int getCount() {
        return slide_text_res.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (ConstraintLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.first_start_slide,container, false );

        ImageView slide_image = view.findViewById(R.id.slide_image);
        TextView slide_text = view.findViewById(R.id.slide_text);

        slide_image.setImageResource(slide_image_res[position]);
        slide_text.setText(slide_text_res[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

}
