package com.kalash.m3.FirstStart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kalash.m3.Main.MainActivity;
import com.kalash.m3.R;
import com.kalash.m3.SplashActivity;
import com.kalash.m3.Util.Log_m3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import static com.kalash.m3.SplashActivity.getFirst_start;
import static com.kalash.m3.SplashActivity.getLog_on;
import static com.kalash.m3.SplashActivity.getLoginUser;
import static com.kalash.m3.SplashActivity.mSet;
import static com.kalash.m3.SplashActivity.setFirst_start;
import static com.kalash.m3.SplashActivity.setLog_on;
import static com.kalash.m3.SplashActivity.setLoginUser;


public class LoginActivity extends AppCompatActivity {

    private SignInButton signInButton;
    private ProgressBar progressBar;

    private static int RC_SIGN_IN = 123;

    private FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleSignInClient;


    private long back_pressed;
    private boolean onPause = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.button_sign_in);
        progressBar = findViewById(R.id.progressBarLogin);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }


    private void signIn(){
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        new Log_m3("Успешный вход").show("d");
                        FirebaseUser user = mAuth.getCurrentUser();
                        setLoginUser(true);
                        saveAppSet();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                        startActivity(intent);
                        finishAffinity();

                    } else {
                        new Log_m3("Вход не удался " + task.getException()).show("d");
                    }

                });
    }


    @Override
    public boolean onKeyDown(int keyCode,  KeyEvent event)   {
        if (keyCode ==  KeyEvent.KEYCODE_BACK &&  event.getRepeatCount()  ==  0)  {
            if (back_pressed + 2000 > System.currentTimeMillis()){
                return super.onKeyDown(keyCode, event);
            }else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Нажмите еще раз для выхода", Toast.LENGTH_SHORT);
                toast.show();
                back_pressed = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void QuestionOut() {

        if (back_pressed + 2000 > System.currentTimeMillis()){

            finishAffinity();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нажмите еще раз для выхода", Toast.LENGTH_SHORT);
            toast.show();
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
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

}