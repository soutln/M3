package com.kalash.m3;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonView;
import io.rmiri.skeleton.SkeletonViewGroup;
import io.rmiri.skeleton.master.AdapterSkeleton;
import io.rmiri.skeleton.master.SkeletonModel;
import io.rmiri.skeleton.master.SkeletonModelBuilder;
import io.rmiri.skeleton.utils.ConverterUnitUtil;

public class TestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ConstraintLayout constraintLayout = findViewById(R.id.testView);

        TextView textView = findViewById(R.id.textView);

        SkeletonViewGroup skeletonViewGroup = new SkeletonViewGroup(getApplicationContext());
        ArrayList<SkeletonModel> skeletonModels = new ArrayList<>();

        skeletonModels.add(new SkeletonModelBuilder()
        .setChildView(textView)
                .setCornerRadius(ConverterUnitUtil.dpToPx(getApplicationContext(), 5))
                .setPaddingTop(ConverterUnitUtil.dpToPx(getApplicationContext(), 2))
                .build());


        skeletonViewGroup.setSkeletonModels(skeletonModels);

        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        constraintLayout.addView(skeletonViewGroup, layout);


        skeletonViewGroup.setSkeletonListener(new SkeletonViewGroup.SkeletonListener() {
            @Override
            public void onStartAnimation() {

            }

            @Override
            public void onFinishAnimation() {

            }
        });
        skeletonViewGroup.startAnimation();

    }
}
