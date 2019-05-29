package com.kalash.m3.FirstStart;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.kalash.m3.Util.Log_m3;
import com.kalash.m3.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.kalash.m3.FirstStart.SliderAdapter.slide_text_res;
import static com.kalash.m3.SplashActivity.getFirst_start;
import static com.kalash.m3.SplashActivity.getLog_on;
import static com.kalash.m3.SplashActivity.getLoginUser;
import static com.kalash.m3.SplashActivity.mSet;
import static com.kalash.m3.SplashActivity.setFirst_start;
import static com.kalash.m3.SplashActivity.setLog_on;
import static com.kalash.m3.SplashActivity.setLoginUser;

public class FirstStartActivity extends AppCompatActivity {

    private boolean close;

    private ViewPager pager_fs;
    private SliderAdapter sliderAdapter;
    private ConstraintLayout constraintLayout;

    private TabLayout tabLayout_fs;
    private boolean btn2_check = false;

    private Button first_start_btn1;
    private Button first_start_btn2;
    private boolean onPause = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        close = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);


        first_start_btn1 = findViewById(R.id.first_start_btn1);
        first_start_btn2 = findViewById(R.id.first_start_btn2);

        pager_fs = findViewById(R.id.pager_fs);
        constraintLayout = findViewById(R.id.constLay);

        sliderAdapter = new SliderAdapter(this);

        pager_fs.setAdapter(sliderAdapter);

        tabLayout_fs = findViewById(R.id.tab_layout);
        tabLayout_fs.setupWithViewPager(pager_fs, true);

        pager_fs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


                new Log_m3(" " + i + " " ).show("d");
            }

            @Override
            public void onPageSelected(int i) {

                if(i == 0){
                    btn2_check = false;

                    first_start_btn1.setEnabled(false);
                    first_start_btn2.setEnabled(true);
                    first_start_btn1.setVisibility(View.INVISIBLE);
                    first_start_btn2.setText(R.string.Button_Next);
                    first_start_btn1.setText("");
                }
                else if(i == slide_text_res.length - 1){
                    btn2_check = true;

                    first_start_btn1.setEnabled(true);
                    first_start_btn2.setEnabled(true);
                    first_start_btn1.setVisibility(View.VISIBLE);
                    first_start_btn2.setText(R.string.Button_GO);
                    first_start_btn1.setText(R.string.Button_Back);
                }

                else{
                    btn2_check = false;

                    first_start_btn1.setEnabled(true);
                    first_start_btn2.setEnabled(true);
                    first_start_btn1.setVisibility(View.VISIBLE);
                    first_start_btn2.setText(R.string.Button_Next);
                    first_start_btn1.setText(R.string.Button_Back);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // Часть обработчиков нажатий
        first_start_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pager_fs.setCurrentItem(pager_fs.getCurrentItem() - 1);
            }
        });

        first_start_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn2_check){
                    OnStartLoginActivity();
                }
                else{
                    pager_fs.setCurrentItem(pager_fs.getCurrentItem() + 1);
                }
            }
        });
        // Конец части обработчиков нажатий

    }


   @Override
    public boolean onKeyDown(int keyCode,  KeyEvent event)   {
        if (keyCode ==  KeyEvent.KEYCODE_BACK &&  event.getRepeatCount()  ==  0)  {
            if (pager_fs.getCurrentItem() == 0) {
                super.onBackPressed();
            } else {
                pager_fs.setCurrentItem(pager_fs.getCurrentItem() - 1);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void OnStartLoginActivity(){
        Intent intent = new Intent(FirstStartActivity.this, LoginActivity.class);
        startActivity(intent);
        close = true;
        setFirst_start(false);
        finishAffinity();
    }

    @Override
    protected void onPause() {
        saveAppSet();
        onPause = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(onPause)
            recoveryAppSet();
        super.onResume();
    }

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
                new Log_m3(str).show("d");

                res = Integer.parseInt(str);
                lU = res % 10;
                res = res / 10;
                log = res % 10;
                res = res / 10;
                fs = res % 10;
                new Log_m3("Первый запуск " + fs).show("d");
                new Log_m3("Логирование " + log).show("d");
                new Log_m3("Авторизация " + lU).show("d");


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

}
