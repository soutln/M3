package com.kalash.m3.AppData;

import android.app.Application;

import com.kalash.m3.Util.Log_m3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataFragment extends Application {

    File fragmentFile;

    public DataFragment(String nameFragment){

        try {
            fragmentFile = File.createTempFile(nameFragment, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
        }
    }

    public  ArrayList<String> onRecoveryFragment() {
        ArrayList<String> data = new ArrayList<>();
        try {

            new Log_m3("onRecoveryFragment " + fragmentFile.getCanonicalPath()).show("i");


                FileReader fr = new FileReader(fragmentFile);
                BufferedReader reader = new BufferedReader(fr);

                String line;
                for(int i = 0;(line = reader.readLine()) != null; i++){
                    data.add(i, line);
                }

                reader.close();
                return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            new Log_m3("onRecoveryFragment " + e).show("e");
        }
        return null;
    }

    public  void onSaveFragment(ArrayList<String> data) {
        try {

            new Log_m3("onSaveFragment " + fragmentFile.getCanonicalPath()).show("i");

            FileWriter fw = new FileWriter(fragmentFile);
            BufferedWriter writer = new BufferedWriter(fw);

            for(int i = 0; i < data.size(); i++){
                writer.write(data.get(i)+ "\n");
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile(){
        return fragmentFile;
    }
}
