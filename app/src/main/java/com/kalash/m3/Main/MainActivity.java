package com.kalash.m3.Main;


import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kalash.m3.Main.AnimationBtn.AnimationBtnSetting;
import com.kalash.m3.Main.AnimationBtn.AnimationBtnUpdate;
import com.kalash.m3.AppData.AnalysisData;
import com.kalash.m3.AppData.GetDataFromServer;
import com.kalash.m3.Main.RecycleView.RecycleViewHistoryAdapter;
import com.kalash.m3.Main.RecycleView.RecycleViewHourAdapter;
import com.kalash.m3.Util.CheckInternet;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.Log_m3;
import com.kalash.m3.R;
import com.kalash.m3.Util.OnDataAvailability;
import com.kalash.m3.Util.OnHistoryAvailability;
import com.kalash.m3.Util.OnRecycleViewClick;
import com.kalash.m3.Util.OnSelectedButtonListener;
import com.kalash.m3.Util.UpdateUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;


import static android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static com.kalash.m3.SplashActivity.getFirst_start;
import static com.kalash.m3.SplashActivity.getLog_on;
import static com.kalash.m3.SplashActivity.getLoginUser;
import static com.kalash.m3.SplashActivity.mSet;
import static com.kalash.m3.SplashActivity.setFirst_start;
import static com.kalash.m3.SplashActivity.setLog_on;
import static com.kalash.m3.SplashActivity.setLoginUser;


public class MainActivity extends AppCompatActivity implements
        OnSelectedButtonListener, OnDataAvailability, OnHistoryAvailability, OnRecycleViewClick {


    // утилиты
    static KeyValue keyValue = new KeyValue();
    static AnalysisData analysisData = new AnalysisData(keyValue);
    public static GetDataFromServer getDataFromServer = new GetDataFromServer(keyValue, analysisData);
    public static ArrayList<String> mainData;
    // конец утилит

    // Работа с фрагментами
    public ViewPager pager_main;
    private PagerAdapter pagerAdapter_main;
    private final int PAGE_COUNT_MAIN = 3;

    public static Fragment HomeFragment;
    public static Fragment OutFragment;
    public static Fragment WindFragment;

    public static AnimationBtnSetting animationBtnSetting = new AnimationBtnSetting();
    public static AnimationBtnUpdate animationBtnUpdate = new AnimationBtnUpdate();

    public static boolean mainDataNotNull = false;

    //Конец работы с фрагментами

    // Работа с RecycleView
    RecyclerView historyView;
    RecyclerView hourView;
    ProgressBar progressBar;
    private String onlineAdapter = "";

    RecycleViewHistoryAdapter recycleViewHistoryAdapter;
    // Конец работы с RecycleView


    //bottom_sheet
    public static BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout BottomSheet;
    private CircleImageView user_img;
    private TextView user_name;
    private Switch switch_log;

    public void openSetting(){

        bottomSheetBehavior.setState(STATE_EXPANDED);
    }
    public void closeSetting(){
        bottomSheetBehavior.setState(STATE_COLLAPSED);

    }
    // конец работы с bottom_sheet

    ConstraintLayout yes_internet;
    ConstraintLayout no_internet;
    Button no_internet_btn;
    CheckInternet checkInternet = new CheckInternet();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        yes_internet = findViewById(R.id.yesinternet);
        no_internet = findViewById(R.id.nointernet);
        no_internet_btn = findViewById(R.id.nointernet_btn);

        HomeFragment = new HomeFragment();
        OutFragment = new OutSideFragment();
        WindFragment = new WindFragment();

        if(updateMainUI()){
            getDataFromServer.getMainData(this, this, animationBtnUpdate);
        }

        mainData = new ArrayList<>();

        //pager

        pager_main = findViewById(R.id.pager_main);
        pagerAdapter_main = new MainPagerAdapter(getSupportFragmentManager());
        pager_main.setAdapter(pagerAdapter_main);

        pager_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        //конец работы с pager


        historyView = findViewById(R.id.recycle_view_history);
        hourView = findViewById(R.id.recycle_view_hour);
        progressBar = findViewById(R.id.progressBar);

        // bottom_sheet

        BottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(BottomSheet);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

            }
            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                animationBtnSetting.onAnimationSetting(slideOffset);
            }
        });

        user_img = findViewById(R.id.account_image);
        user_name = findViewById(R.id.account_name);
        switch_log = findViewById(R.id.switch_log);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Glide.with(this).load(currentUser.getPhotoUrl()).into(user_img);
            user_name.setText("Егор Калашников");
        }

        if(getLog_on()){
            switch_log.setChecked(true);
        }
        else{
            switch_log.setChecked(false);
        }

        switch_log.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setLog_on(true);
                }
                else{
                    setLog_on(false);
                }
            }
        });
        //конец bottom_sheet

        no_internet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateMainUI()){
                    getDataFromServer.getMainData(MainActivity.this, getApplicationContext(),
                            animationBtnUpdate);
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode,  KeyEvent event){

        if(keyCode ==  KeyEvent.KEYCODE_BACK && onlineAdapter.equals(keyValue.getHourAdapter())){
            updateAdapter(keyValue.getHistoryAdapter());
            if(checkInternet.check(this)){
                getDataFromServer.getMainData(this, this, animationBtnUpdate);
            }
        }
        else if(keyCode ==  KeyEvent.KEYCODE_BACK &&  event.getRepeatCount() == 0
                && bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.setState(STATE_COLLAPSED);
        }
        else if (keyCode ==  KeyEvent.KEYCODE_BACK &&  event.getRepeatCount()  ==  0
                && onlineAdapter.equals(keyValue.getHistoryAdapter())){
            return super.onKeyDown(keyCode, event);
        }
        else{
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }



    @Override
    protected void onPause() {
        saveAppSet();
        super.onPause();
    }

    @Override
    protected void onResume() {
        recoveryAppSet();
        super.onResume();
    }



    // работа с фрагментами
    public class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment;
                case 1:
                    return OutFragment;
                case 2:
                    return WindFragment;
                default:
                    return HomeFragment;
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT_MAIN;
        }
    }

    @Override
    public void onDataAvailabilityListener(ArrayList<String> data) {
        if(data.size() != 0){
            mainData = data;
            mainDataNotNull = true;
            animationBtnUpdate.onAnimationUpdate(false, this);

            UpdateUI upHome = (UpdateUI) HomeFragment;
            UpdateUI upOut = (UpdateUI) OutFragment;
            UpdateUI upWind = (UpdateUI) WindFragment;

            if(HomeFragment.getId() != 0)
                upHome.updateUI();
            if(OutFragment.getId() != 0)
                upOut.updateUI();
            if(WindFragment.getId() != 0)
                upWind.updateUI();

        }
        else
            mainDataNotNull = false;
    }

    public static ArrayList<String> getData(String page){
        if(mainDataNotNull){
            if(page.equals(keyValue.getPageHome())){
                return analysisData.homeView(mainData);
            }
            if(page.equals(keyValue.getPageOut())){
                return analysisData.outView(mainData);
            }
            if(page.equals(keyValue.getPageWind())){
                return analysisData.windView(mainData);
            }
        }
        return null;
    }

    @Override
    public void onButtonSelected(int buttonIndex) {

        if(buttonIndex == 0){
            closeSetting();
        }
        else if(buttonIndex == 1){
            openSetting();
        }
        else if(buttonIndex == 2){
            if(updateMainUI()){
                getDataFromServer.getMainData(this, this, animationBtnUpdate);
            }
            progressBar.setVisibility(View.VISIBLE);
            historyView.setVisibility(View.GONE);
            hourView.setVisibility(View.GONE);
        }

    }

    // конец работы с фрагментами



    // начало работы с RecycleView
    @Override
    public void onHistoryAvailabilityListener(String name, ArrayList<ArrayList<String>> data) {
        progressBar.setVisibility(View.GONE);
        if(name.equals(keyValue.getHistoryAdapter())){
            updateAdapter(keyValue.getHistoryAdapter());
            RecycleViewHistoryAdapter adapter =
                    new RecycleViewHistoryAdapter(this,this, data, keyValue);
            recycleViewHistoryAdapter = adapter;
            historyView.setAdapter(adapter);
            historyView.setLayoutManager(new LinearLayoutManager(this));
        }
        else if(name.equals(keyValue.getHourAdapter())){
            updateAdapter(keyValue.getHourAdapter());
            RecycleViewHourAdapter adapter =
                    new RecycleViewHourAdapter(this,this, data, keyValue);
            hourView.setAdapter(adapter);
            hourView.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    public void onRecycleViewClickListener(String name,ArrayList<String> data){
        if(name.equals(keyValue.getHistoryAdapter())){
            historyView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            getDataFromServer.getDayData(this,this, data.get(4), data.get(5));
        }
        else if(name.equals(keyValue.getHourAdapter())){
            mainData = data;

            UpdateUI upHome = (UpdateUI) HomeFragment;
            UpdateUI upOut = (UpdateUI) OutFragment;
            UpdateUI upWind = (UpdateUI) WindFragment;

            if(HomeFragment.getId() != 0)
                upHome.updateUI();
            if(OutFragment.getId() != 0)
                upOut.updateUI();
            if(WindFragment.getId() != 0)
                upWind.updateUI();

        }

    }

    private void updateAdapter(String adapterName){
        if(adapterName.equals(keyValue.getHistoryAdapter())){
            onlineAdapter = keyValue.getHistoryAdapter();
            historyView.setVisibility(View.VISIBLE);
            hourView.setVisibility(View.GONE);
        }
        else if(adapterName.equals(keyValue.getHourAdapter())){
            onlineAdapter = keyValue.getHourAdapter();
            historyView.setVisibility(View.GONE);
            hourView.setVisibility(View.VISIBLE);
        }
    }
    //конец работы с RecycleView

    private boolean updateMainUI(){
        if(checkInternet.check(getApplicationContext())){
            no_internet.setVisibility(View.GONE);
            yes_internet.setVisibility(View.VISIBLE);
            return true;
        }
        else{
            yes_internet.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            return false;
        }
    }

    // Сохранение и восстановление данных
    private void saveAppSet(){
        int fs;
        int log;
        int lU;
        if(getFirst_start())
            fs = 1;
        else
            fs = 0;

        if(getLog_on())
            log = 1;
        else
            log = 0;

        if(getLoginUser())
            lU = 1;
        else
            lU = 0;

        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(mSet, MODE_PRIVATE)));
            bw.write(fs + "" + log + "" + lU);
            bw.close();
            new Log_m3("Данные сохранены").show("d");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void recoveryAppSet(){
        int fs;
        int log;
        int lU;
        int res;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(mSet)));
            String str = "";
            while ((str = br.readLine()) != null) {

                res = Integer.parseInt(str);
                lU = res % 10;
                res = res / 10;
                log = res % 10;
                res = res / 10;
                fs = res % 10;

                if(log == 1)
                    setLog_on(true);
                else
                    setLog_on(false);

                if(fs == 1)
                    setFirst_start(true);
                else
                    setFirst_start(false);

                if(lU == 1)
                    setLoginUser(true);
                else
                    setLoginUser(false);
            }
            new Log_m3("Данные восстановлены").show("d");
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Сохранение и восстановление данных

 
}
