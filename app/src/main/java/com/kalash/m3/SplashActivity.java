package com.kalash.m3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalash.m3.FirstStart.FirstStartActivity;
import com.kalash.m3.FirstStart.LoginActivity;
import com.kalash.m3.Main.MainActivity;
import com.kalash.m3.Util.Log_m3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class SplashActivity extends AppCompatActivity  {


    private static boolean log_on = true; // включение логирования
    private static boolean first_start = true; // первый запуск
    private static boolean loginUser = false; // вход User
    public static final String mSet = "mSet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


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
                if (res != 0) {
                    lU = res % 10;
                    res = res / 10;
                    log = res % 10;
                    res = res / 10;
                    fs = res % 10;


                    if (log == 1)
                        log_on = true;
                    else
                        log_on = false;
                    if (fs == 1)
                        first_start = true;
                    else
                        first_start = false;

                    if (lU == 1)
                        loginUser = true;
                    else
                        loginUser = false;
                }
            }
            new Log_m3("Данные восстановлены").show("d");
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    //Место для Getter and Setter
    public static boolean getLog_on() {
        return log_on;
    }

    public static void setLog_on(boolean log_on) {
        SplashActivity.log_on = log_on;
    }

    public static boolean getFirst_start() {
        return first_start;
    }

    public static void setFirst_start(boolean first_start) {
        SplashActivity.first_start = first_start;
    }

    public static boolean getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(boolean loginUser) {
        SplashActivity.loginUser = loginUser;
    }
    //Конец для Getter and Setter

    @Override
    protected void onStart() {

        if (first_start) {
            new Log_m3("Первый запуск, переход в FirstStartActivity")
                    .show("d");
            startActivity(new Intent(SplashActivity.this, FirstStartActivity.class));
        }
        else if(loginUser == false){
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
        super.onStart();

    }


}
