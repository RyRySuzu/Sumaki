
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Result extends Activity {

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


    static int NoGPS;

    double shoulder = 38.8;
    double dress=84.2;
    double sleeve=53.1;
    float get_tyousei;
    double get_inseam=75.6;
    float get_waste=70;
    float get_hip=85;
    double get_thight=15.6;
    double get_height=167.5;
    float get_hanteikun = 0;
    double nn = 10000;
    float check =0;

    //  static String Sundaytime = "15";
    static String shouldersize = "38.8";
    static String dresssize = "84.2";
    static String sleevesize = "53.1";
    static String inseamsize = "75.6";
    static String wastesize = "70";
    static String hipsize = "85";
    static String thightsize = "15.6";
    static String heightsize = "167.5";


    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // タイトルバーを消す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ステータスバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // final Toast ts = Toast.makeText(this, "建物を撮影する事で５人の命を救いました！！",
        // Toast.LENGTH_LONG);

        setContentView(R.layout.haraheri);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Context readcon = this;
        final Context writecon = this;


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

        DataRegist obj = new DataRegist();
        shoulder = obj.gethigh();
        dress = obj.getwidth();
        sleeve = obj.getokuyuki();
        get_inseam = obj.getinseam();


        check=DataRegist.check();

        TextView sethip = (TextView) findViewById(R.id.sethip);
        TextView setthight = (TextView) findViewById(R.id.setthight);
        TextView setheight = (TextView) findViewById(R.id.setheight);

        
        get_tyousei = get_size(widthIn);
        TextView pyse = (TextView) findViewById(R.id.physique);
        pyse.setPadding(50, 60, 5, 5);
        String txtStr = "<font color=#0000FF>B</font>ody size";

        pyse.setText(Html.fromHtml(txtStr));
        pyse.setTextSize(20 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView shoul = (TextView) findViewById(R.id.shoulder);
        shoul.setPadding(50, 60, 5, 5);
        String txtS = "Shoulder";

        shoul.setText(Html.fromHtml(txtS));
        shoul.setTextSize(11 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView sleev = (TextView) findViewById(R.id.sleeve);
        sleev.setPadding(50, 60, 5, 5);
        String txtSleeve = "Sleeve";

        sleev.setText(Html.fromHtml(txtSleeve));
        sleev.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView dres = (TextView) findViewById(R.id.dress);
        dres.setPadding(50, 60, 5, 5);
        String txtDress = "Dress";

        dres.setText(Html.fromHtml(txtDress));
        dres.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView wast = (TextView) findViewById(R.id.waste);
        wast.setPadding(50, 60, 5, 5);
        String txtWaste = "Waste";

        wast.setText(Html.fromHtml(txtWaste));
        wast.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView insea = (TextView) findViewById(R.id.inseam);
        insea.setPadding(50, 60, 5, 5);
        String txtInseam = "Inseam";

        insea.setText(Html.fromHtml(txtInseam));
        insea.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView hi = (TextView) findViewById(R.id.hip);
        hi.setPadding(50, 60, 5, 5);
        String txtHip = "Hip";

        hi.setText(Html.fromHtml(txtHip));
        hi.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView thigh = (TextView) findViewById(R.id.thight);
        thigh.setPadding(50, 60, 5, 5);
        String txtthight = "Thight";

        thigh.setText(Html.fromHtml(txtthight));
        thigh.setTextSize(12 * get_tyousei);

        get_tyousei = get_size(widthIn);
        TextView heigh = (TextView) findViewById(R.id.height);
        heigh.setPadding(50, 60, 5, 5);
        String txtheight = "Height";

        heigh.setText(Html.fromHtml(txtheight));
        heigh.setTextSize(12 * get_tyousei);


        BigDecimal bigdecimal = new BigDecimal(String.valueOf(shoulder));
        shoulder = (float) bigdecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigdecimale = new BigDecimal(String.valueOf(dress));
        dress = (float) bigdecimale.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigdecimalee = new BigDecimal(String.valueOf(sleeve));
        sleeve = (float) bigdecimalee.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigdecimaleee = new BigDecimal(String.valueOf(get_inseam));
        get_inseam = (float) bigdecimaleee.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigdecimaleeee = new BigDecimal(String.valueOf(get_waste));
        get_waste = (float) bigdecimaleeee.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bighip = new BigDecimal(String.valueOf(get_hip));
        get_hip = (float) bighip.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigthight = new BigDecimal(String.valueOf(get_thight));
        get_thight = (float) bigdecimaleeee.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        BigDecimal bigheight = new BigDecimal(String.valueOf(get_height));
        get_height = (float) bigheight.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        Log.v("getwaste", String.valueOf(get_waste));
        Log.v("gethip", String.valueOf(get_hip));
        Log.v("getthight", String.valueOf(get_thight));


        get_tyousei = get_size(widthIn);
        TextView Setshoulder = (TextView) findViewById(R.id.setshoulder);
        TextView Setsleeve = (TextView) findViewById(R.id.setsleeve);
        TextView Setdress = (TextView) findViewById(R.id.setdress);
        TextView Setwaste = (TextView) findViewById(R.id.setwaste);
        TextView Setinseam = (TextView) findViewById(R.id.setinserm);
        TextView Setheight = (TextView) findViewById(R.id.setheight);
        TextView Sethip = (TextView) findViewById(R.id.sethip);
        TextView Setthight = (TextView) findViewById(R.id.setthight);



        Setshoulder.setTextSize(13 * get_tyousei);
        Setsleeve.setTextSize(13 * get_tyousei);
        Setdress.setTextSize(13 * get_tyousei);
        Setwaste.setTextSize(13 * get_tyousei);
        Setinseam.setTextSize(13 * get_tyousei);
        Setheight.setTextSize(13 * get_tyousei);
        Sethip.setTextSize(13 * get_tyousei);
        Setthight.setTextSize(13 * get_tyousei);

        if (get_waste != 0) {


            BufferedWriter outwaste = null;
            try {
                FileOutputStream file = writecon.openFileOutput("aa.txt", Context.MODE_PRIVATE);
                outwaste = new BufferedWriter(new OutputStreamWriter(file));
                outwaste.write(String.valueOf(get_waste));
                outwaste.flush();
                outwaste.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outdress = null;
            try {
                FileOutputStream file2 = writecon.openFileOutput("bb.txt", Context.MODE_PRIVATE);
                outdress = new BufferedWriter(new OutputStreamWriter(file2));
                outdress.write("84.2");
                outdress.flush();
                outdress.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outsleeve = null;
            try {
                FileOutputStream file3 = writecon.openFileOutput("cc.txt", Context.MODE_PRIVATE);
                outsleeve = new BufferedWriter(new OutputStreamWriter(file3));
                outsleeve.write("53.1");
                outsleeve.flush();
                outsleeve.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outinseam = null;
            try {
                FileOutputStream file4 = writecon.openFileOutput("dd.txt", Context.MODE_PRIVATE);
                outinseam = new BufferedWriter(new OutputStreamWriter(file4));
                outinseam.write("75.6");
                outinseam.flush();
                outinseam.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outshoulder = null;
            try {
                FileOutputStream file5 = writecon.openFileOutput("ee.txt", Context.MODE_PRIVATE);
                outshoulder = new BufferedWriter(new OutputStreamWriter(file5));
                outshoulder.write("38.8");
                outshoulder.flush();
                outshoulder.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outhip = null;
            try {
                FileOutputStream file6 = writecon.openFileOutput("ff.txt", Context.MODE_PRIVATE);
                outhip = new BufferedWriter(new OutputStreamWriter(file6));
                outhip.write(String.valueOf(get_hip));
                outhip.flush();
                outhip.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outthight = null;
            try {
                FileOutputStream file7 = writecon.openFileOutput("gg.txt", Context.MODE_PRIVATE);
                outthight = new BufferedWriter(new OutputStreamWriter(file7));
                outthight.write("47.3");
                outthight.flush();
                outthight.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter outheight = null;
            try {
                FileOutputStream file8 = writecon.openFileOutput("hh.txt", Context.MODE_PRIVATE);
                outheight = new BufferedWriter(new OutputStreamWriter(file8));
                outheight.write(String.valueOf(get_height));
                outheight.flush();
                outheight.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(check == 1) {
            BufferedReader inwaste = null;
            try {
                FileInputStream file = readcon.openFileInput("aa.txt");
                inwaste = new BufferedReader(new InputStreamReader(file));
                wastesize = inwaste.readLine();

                Setwaste.setText(wastesize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader indress = null;
            try {
                FileInputStream file2 = readcon.openFileInput("bb.txt");
                indress = new BufferedReader(new InputStreamReader(file2));
                dresssize = indress.readLine();

                Setdress.setText(dresssize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader insleeve = null;
            try {
                FileInputStream file3 = readcon.openFileInput("cc.txt");
                insleeve = new BufferedReader(new InputStreamReader(file3));
                sleevesize = insleeve.readLine();

                Setsleeve.setText(sleevesize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader ininseam = null;
            try {
                FileInputStream file4 = readcon.openFileInput("dd.txt");
                ininseam = new BufferedReader(new InputStreamReader(file4));
                inseamsize = ininseam.readLine();

                Setinseam.setText(inseamsize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader inshoulder = null;
            try {
                FileInputStream file5 = readcon.openFileInput("ee.txt");
                inshoulder = new BufferedReader(new InputStreamReader(file5));
                shouldersize = inshoulder.readLine();

                Setshoulder.setText(shouldersize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader inhip = null;
            try {
                FileInputStream file6 = readcon.openFileInput("ff.txt");
                inhip = new BufferedReader(new InputStreamReader(file6));
                hipsize = inhip.readLine();

                Sethip.setText(hipsize);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader inthight = null;
            try {
                FileInputStream file7 = readcon.openFileInput("gg.txt");
                inthight = new BufferedReader(new InputStreamReader(file7));
                thightsize = inthight.readLine();

                Setthight.setText("15.6");
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader intheight = null;
            try {
                FileInputStream file8 = readcon.openFileInput("hh.txt");
                intheight = new BufferedReader(new InputStreamReader(file8));
                heightsize = intheight.readLine();

                Setheight.setText(heightsize);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
       
        Button btn = (Button) findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tv) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(Result.this, DataRegist.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

        Button ris = (Button) findViewById(R.id.risou);
        ris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // インテントのインスタンス生成
                Intent intent = new Intent(Result.this, Bomber.class);
                // 次画面のアクティビティ起動
                startActivity(intent);
            }
        });

        Button btn2 = (Button)findViewById(R.id.y_waste);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=ウエスト70&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        Button btn3 = (Button)findViewById(R.id.y_height);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=身長167&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        Button btn4 = (Button)findViewById(R.id.y_shoulder);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=肩幅38&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        Button btn5 = (Button)findViewById(R.id.y_sleeve);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=袖丈53&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        Button btn6 = (Button)findViewById(R.id.y_dress);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=着丈84&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        Button btn7 = (Button)findViewById(R.id.y_inserm);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=股下75&x=0&y=0");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
});

        Button btn8 = (Button)findViewById(R.id.y_hip);
        btn8.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        // TODO Auto-generated method stub

        Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=ヒップ85&x=0&y=0");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        }
        });

        Button btn9 = (Button)findViewById(R.id.y_thight);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://auctions.search.yahoo.co.jp/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=太もも15&x=0&y=0");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
 
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public static float get_size(float size) {
        float tyousei_2;
        tyousei_2 = 8 / (10 / size);
        return tyousei_2;
    }
}
