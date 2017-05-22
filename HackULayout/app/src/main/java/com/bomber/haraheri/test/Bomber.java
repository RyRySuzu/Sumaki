package com.bomber.haraheri.test;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Bomber extends Activity {
    
    int kidou;
    int takasa_point;
    int menseki_point;
    int anzen_point;
    int yane_point;
    int cast_Tourokusoukei;
    
    // ただのループカウンター
    int k = 0;
    int n = 0;
    
    private int height_point;
    private int width__point;
    private int depth_point;
    private int anzensei;
    private int yane;
    
    static int check = 0;
    static int NoGPS;
    
    float shoulder;
    float dress;
    float sleeve;
    float get_tyousei;
    float get_inseam;
    float get_waste;
    float get_hip;
    float get_thight;
    float get_height;
    
    
    double nn = 10000;
    
    //  static String Sundaytime = "15";
    static String shouldersize = "0";
    static String dresssize = "0";
    static String sleevesize = "0";
    static String inseamsize = "0";
    static String wastesize = "0";
    static String hipsize = "0";
    static String thightsize = "0";
    static String heightsize = "0";
    
    
    double height =170;
    double waste =75;
    double hip =80;
    double futomomo =50;
    
    
    
    　　 //理想体型計算
    double T_waste = height * 0.37;
    double T_hip = height * 0.53;
    double T_futomomo = height * 0.3;
    
    double r_waste = waste - T_waste;
    double r_hip = hip - T_hip;
    double r_futomomo = futomomo - T_futomomo;
    
    
    
    /** Called when the activity is first created. */
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        // タイトルバーを消す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ステータスバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // final Toast ts = Toast.makeText(this, "建物を撮影する事で５人の命を救いました！！",
        // Toast.LENGTH_LONG);
        
        setContentView(R.layout.distopia);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Context readwaste = this;
        final Context readdress = this;
        final Context readshoulder = this;
        final Context readsleeve = this;
        final Context readinseam = this;
        final Context readhip = this;
        final Context readthight = this;
        final Context readheight = this;
        
        
        // WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // ピクセル数（width, height）を取得する
        final int widthPx = metrics.widthPixels;
        final int heightPx = metrics.heightPixels;
        // dpi (xdpi, ydpi) を取得する
        final float xdpi = metrics.xdpi;
        final float ydpi = metrics.ydpi;
        // インチ（width, height) を計算する
        final float widthIn = widthPx / xdpi;
        final float heightIn = heightPx / ydpi;
        
        
        BufferedReader inwaste = null;
        try {
            FileInputStream file = readwaste.openFileInput("aa.txt");
            inwaste = new BufferedReader(new InputStreamReader(file));
            wastesize = inwaste.readLine();
            
            //Setwaste.setText(wastesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader indress = null;
        try {
            FileInputStream file2 = readdress.openFileInput("bb.txt");
            indress = new BufferedReader(new InputStreamReader(file2));
            dresssize = indress.readLine();
            
            //Setdress.setText(dresssize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader insleeve = null;
        try {
            FileInputStream file3 = readsleeve.openFileInput("cc.txt");
            insleeve = new BufferedReader(new InputStreamReader(file3));
            sleevesize = insleeve.readLine();
            
            //Setsleeve.setText(sleevesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader ininseam = null;
        try {
            FileInputStream file4 = readinseam.openFileInput("dd.txt");
            ininseam = new BufferedReader(new InputStreamReader(file4));
            inseamsize = ininseam.readLine();
            
            //Setinseam.setText(inseamsize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader inshoulder = null;
        try {
            FileInputStream file5 = readshoulder.openFileInput("ee.txt");
            inshoulder = new BufferedReader(new InputStreamReader(file5));
            shouldersize = inshoulder.readLine();
            
            // Setshoulder.setText(shouldersize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader inhip = null;
        try {
            FileInputStream file6 = readhip.openFileInput("ff.txt");
            inhip = new BufferedReader(new InputStreamReader(file6));
            hipsize = inhip.readLine();
            
            // Setinseam.setText(inseamsize);//ここでせっとてきすと　読み込んで出力
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader inthight = null;
        try {
            FileInputStream file6 = readthight.openFileInput("gg.txt");
            inthight = new BufferedReader(new InputStreamReader(file6));
            thightsize = inthight.readLine();
            
            //Setshoulder.setText(shouldersize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        BufferedReader intheight = null;
        try {
            FileInputStream file8 = readheight.openFileInput("hh.txt");
            intheight = new BufferedReader(new InputStreamReader(file8));
            heightsize = intheight.readLine();
            
            //Setshoulder.setText(shouldersize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        Button btn = (Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(Bomber.this, Result.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
                
            }
        });
        
        
        
        
        
        
    }
    
    
    
    
    
    public static float get_size(float size) {
        float tyousei_2;
        tyousei_2 = 8 / (10 / size);
        return tyousei_2;
    }
}
