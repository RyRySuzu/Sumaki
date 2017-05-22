package com.bomber.haraheri.test;

import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DataRegist extends Activity implements SensorEventListener,
LocationListener, OnClickListener {
    
    private SensorManager manager;
    private Camera myCamera;
    private Button touroku = null; // 結果画面表示ボタン
    private Button map = null; // マップに戻るボタン
    private static final long standTime = 2000;
    private static long ClickTime;
    
    
    final Context context = this;
    Context context_4 = this;
    public String height;
    public String persons;
    LocationManager locman;//
    
    
    // 回転行列
    private static final int MATRIX_SIZE = 16;
    float[] inR = new float[MATRIX_SIZE];
    float[] outR = new float[MATRIX_SIZE];
    float[] I = new float[MATRIX_SIZE];
    
    // センサー値
    private static final int AXIS_NUM = 3;
    float[] gravity = new float[AXIS_NUM];
    float[] geomagnetic = new float[AXIS_NUM];
    float[] orientation = new float[AXIS_NUM];
    float[] attitude = new float[AXIS_NUM];
    
    
    int i = 1;
    int loop = 0;
    int loop_houi = 0;
    int k = 0;
    int k_houi = 0;
    int time;
    
    private int get_high;
    
    static int Max_height;
    static int limit=0;
    
    
    float t_kakudo;
    float ue_kakudo;
    float kyori;
    float K_kyori;
    float y_kakudo;
    float r_yoko;
    float s_houi;
    float r_houi;
    float l_houi;
    float migi_haba;
    float hidari_haba;
    float all_houi;
    float heikin_koudo;
    float O_kyori;
    float O_all_houi;;
    float O_m_houi;
    float O_h_houi;
    float O_migi_haba;
    float O_hidari_haba;
    float migi_kakudo;
    float hidari_kakudo;
    float O_0_0_houi;
    float O_hidari_kakudo;
    float O_migi_kakudo;
    float lat0, long0;
    float get_tyousei;
    float O_0_houi;
    //float allwaste2;
    float r;
    float Δlat, Δlong;
    float d; // 自分のところから建物の右角度まで
    float e; // 自分のところから建物の左角度まで
    float f;// 奥行で自分のところから右角度見
    float sum1;
    float sum2;
    float sum3;
    float sum4;
    float Ave1;
    float Ave2;
    float T_distance;
    float T_kakudo;
    float houi;
    
    
    static float O_yokohaba;
    static float O_yokohaba3;
    static float O_yokohaba2;
    static float takasa;
    static float hiegth;
    static float yokohaba;
    static float o_hip;
    static float hip;
    static float thight;
    static float waste;
    static double allwaste;
    static float lat1;
    static float long1;
    static float lat2;
    static float long2;
    static float lat3, long3;
    static float lat4, long4;
    
    static float sleeve1;
    static float sleeve2;
    static float dress1;
    static float dress2;
    static float inseam;
    static float hantei = 1;
    static float allwaste2;
    static float height1;
    static float hip1;
    static float allhip2;
    static float o_thight;
    static float o_waste;
    static float hipwastelight;
    static double allhip;
    static float thight1;
    static float allthight2;
    static float thightwastelight;
    static double allthight;
    
    static boolean off = false;
    static float checkk=0;
    
    
    double set_koudo;
    static double Radius_long = 6378137.0;
    static double f2 = 1/298.257222101;
    static double Radius_short = Radius_long * (1 - f2);// 6356752.314
    
    float Mystature;
    float Mystature2;
    
    static double set_lat;
    static double set_lon;
    static double set_high;
    static double LAT2;
    static double LON2;
    
    
    
    static String get_id_2;
    static String stature;
    static String Version;
    
    private String get_id;
    
    private static String latnow;
    private static String lngnow;
    private static String highnow;
    
    
    float[][] YangleArray = new float[30][2];
    float[] distance = new float[30];
    float[] tate_kakudo = new float[30];
    
    
    
    private SurfaceHolder.Callback mSurfaceListener = new SurfaceHolder.Callback()
    {
    public void surfaceCreated(SurfaceHolder holder)
    {
    // TODO Auto-generated method stub
    myCamera = Camera.open();
    // myCamera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
    
    try
    {
        myCamera.setPreviewDisplay(holder);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}


public void surfaceDestroyed(SurfaceHolder holder)
{
// TODO Auto-generated method stub
myCamera.release();
myCamera = null;





}


public void surfaceChanged(SurfaceHolder holder, int format, int width,int height)
{
// TODO Auto-generated method stub
Camera.Parameters parameters = myCamera.getParameters();
parameters.setPreviewSize(width, height);

List<Size> sizes = parameters.getSupportedPreviewSizes();
Camera.Size size = sizes.get(0);
myCamera.setDisplayOrientation(90);
parameters.setPreviewSize(size.width, size.height);
myCamera.setParameters(parameters);
myCamera.startPreview();

}
};


protected boolean isPortrait()
{
return (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
}


private Camera.ShutterCallback mShutterListener = new Camera.ShutterCallback()
{
public void onShutter() {
// TODO Auto-generated method stub
}
};


// JPEGイメージ生成後に呼ばれるコールバック
private Camera.PictureCallback mPictureListener = new Camera.PictureCallback()
{
public void onPictureTaken(byte[] data, Camera camera) {
// SDカードにJPEGデータを保存する
if (data != null) {
FileOutputStream myFOS = null;
try {
myFOS = new FileOutputStream("/sdcard/camera_test.jpg");
myFOS.write(data);
myFOS.close();
} catch (Exception e) {
e.printStackTrace();
}

camera.startPreview();
}
}
};


/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState)
{

final Context context = this;

//get_id = JintoriGame.ValueA();
//stature = JintoriGame.stature();
stature = "158";
Mystature = Float.valueOf(stature).floatValue();
Mystature2 = (Mystature-30)/100;
//Mystature2 = (float) 1.5;

get_id_2 = String.valueOf(get_id);
highnow = String.valueOf(set_high);

lat0 = (float) set_lat;
long0 = (float) set_lon;


// タイトルバーを消す
requestWindowFeature(Window.FEATURE_NO_TITLE);

// ステータスバーを消す
getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
super.onCreate(savedInstanceState);
setContentView(R.layout.camera);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

this.touroku = (Button) findViewById(R.id.touroku1);
this.touroku.setOnClickListener(this);
this.map = (Button) findViewById(R.id.end);
this.map.setOnClickListener(this);


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


locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
manager = (SensorManager) getSystemService(SENSOR_SERVICE);


final Toast ts = Toast.makeText(this, "お疲れサマンサ",
Toast.LENGTH_LONG);


SurfaceView mySurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
SurfaceHolder holder = mySurfaceView.getHolder();
holder.addCallback(mSurfaceListener);
holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


get_tyousei = get_size(widthIn);
TextView low_1 = (TextView) findViewById(R.id.textView1);
low_1.setPadding(50, 60, 5, 5);
String txtStr = "<font color=#ff0000>足元</font>を撮影して下さい";

low_1.setText(Html.fromHtml(txtStr));
low_1.setTextSize(9 * get_tyousei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_asimoto);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);

Button back = (Button) findViewById(R.id.back);
back.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View b) {
if (i == 2) {

TextView low_1 = (TextView) findViewById(R.id.textView1);
low_1.setPadding(50, 60, 5, 5);
String txtStr = "<font color=#ff0000>足元</font>を撮影して下さい";

low_1.setText(Html.fromHtml(txtStr));
low_1.setTextSize(9 * get_tyousei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_asimoto);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);

Button back = (Button) findViewById(R.id.back);

} else if (i == 3) {
Button asimoto = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);
TextView high_1 = (TextView) findViewById(R.id.textView1);
high_1.setPadding(50, 60, 5, 5);
String txtStr = "<font color=#ff0000>頭のてっぺん</font>を撮影して下さい";
high_1.setText(Html.fromHtml(txtStr));

high_1.setTextSize(9 * get_tyousei);
ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_atama);
image.setVisibility(View.VISIBLE);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setBackgroundResource(R.drawable.camera_decision1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);

} else if (i == 4) {
Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
Button Oku_syomen = (Button) findViewById(R.id.satuei);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView left_2 = (TextView) findViewById(R.id.textView1);
left_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>左端</font>を撮影して下さい";
left_2.setText(Html.fromHtml(txtStr));
left_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_uesutohidari);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);
} else if (i == 5) {


Button migi = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);
TextView right_1 = (TextView) findViewById(R.id.textView1);
right_1.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>右端</font>を撮影して下さい";
right_1.setText(Html.fromHtml(txtStr));
right_1.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_wastemigi);
image.setVisibility(View.VISIBLE);
} else if (i == 6) {
Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
@SuppressWarnings("unused")
Button hidari = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);

TextView low_2 = (TextView) findViewById(R.id.textView1);
low_2.setPadding(50, 60, 5, 5);

String txtStr = "<font color=#ff0000>側面</font>へ移動して、<font color=#ff0000>下部</font>を撮影して下さい";
low_2.setText(Html.fromHtml(txtStr));
low_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_side_asimoto);
image.setVisibility(View.VISIBLE);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);
} else if (i == 7) {
Button Oku_syomen = (Button) findViewById(R.id.satuei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView left_2 = (TextView) findViewById(R.id.textView1);
left_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>左端</font>を撮影して下さい";
left_2.setText(Html.fromHtml(txtStr));
left_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_sidekosimae);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);


} else if (i == 8) {

Button Oku_migi = (Button) findViewById(R.id.satuei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView right_2 = (TextView) findViewById(R.id.textView1);
right_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>右端</font>を撮影して下さい";
right_2.setText(Html.fromHtml(txtStr));
right_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_side_kosiusiro);
image.setVisibility(View.VISIBLE);


}


i--;
}
});

Button asimoto = (Button) findViewById(R.id.satuei);
asimoto.setOnClickListener(new View.OnClickListener()
{

@Override
public void onClick(View a)
{
if (!DataRegist.oneClickEvent())
return;
// TODO Auto-generated method stub
if (i <= 0)
{
i = 1;
}

if (i == 1)
{

K_kyori = kyori;
O_0_houi = y_kakudo;

vincenty(set_lat,set_lon,houi,K_kyori);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
@SuppressWarnings("unused")
Button asimoto = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);
TextView high_1 = (TextView) findViewById(R.id.textView1);
high_1.setPadding(50, 60, 5, 5);
String txtStr = "<font color=#ff0000>頭のてっぺん</font>を撮影して下さい";
high_1.setText(Html.fromHtml(txtStr));

high_1.setTextSize(9 * get_tyousei);
ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_atama);
image.setVisibility(View.VISIBLE);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setBackgroundResource(R.drawable.camera_decision1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);
i++;
}




else if (i == 2)
{

r_houi = y_kakudo;
migi_kakudo = r_houi - O_0_houi;

if (migi_kakudo < 0)
{
migi_kakudo = (float) (360.0+migi_kakudo);
}



if(migi_kakudo > 0)
{

migi_haba = (float) (K_kyori * Math
.tan((migi_kakudo) / 180.0 * Math.PI));
}
else
{

migi_haba = (float) (K_kyori * Math
.tan((migi_kakudo + 360) / 180.0 * Math.PI));

}

yokohaba = (float) (2*K_kyori * Math.tan((migi_kakudo+hidari_kakudo)/2 * Math.PI  / 180.0));

e = (float) (Math.sqrt(K_kyori * K_kyori + migi_haba
* migi_haba));
Δlat = (float) (0.0001 * e) / 10;
Δlong = (float) (0.0001 * e) / 10;
lat2 = lat0 + Δlat;

long2 = long0 + Δlong;



Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision2);
touroku.setEnabled(true);
@SuppressWarnings("unused")
Button Oku_syomen = (Button) findViewById(R.id.satuei);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView left_2 = (TextView) findViewById(R.id.textView1);
left_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>左端</font>を撮影して下さい";
left_2.setText(Html.fromHtml(txtStr));
left_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_uesutohidari);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);
i++;



} else if (i == 3)
{

l_houi = y_kakudo;
hidari_kakudo = O_0_houi - l_houi;

if (hidari_kakudo < 0)
{
hidari_kakudo = (float) (360.0+hidari_kakudo);
}



if (hidari_kakudo > 0)
{
hidari_haba = (float) (K_kyori * Math
.tan((hidari_kakudo) / 180.0 * Math.PI));

}

else
{
hidari_haba = (float) (K_kyori * Math
.tan((hidari_kakudo + 360.0) / 180.0 * Math.PI));
}




Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
@SuppressWarnings("unused")

Button migi = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);
TextView right_1 = (TextView) findViewById(R.id.textView1);
right_1.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>右端</font>を撮影して下さい";
right_1.setText(Html.fromHtml(txtStr));
right_1.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_front_wastemigi);
image.setVisibility(View.VISIBLE);

d = (float) (Math.sqrt(K_kyori * K_kyori + hidari_haba
* hidari_haba));
Δlat = (float) (0.0001 * d) / 10;
Δlong = (float) (0.0001 * d) / 10;
lat1 = lat0 + Δlat;
long1 = long0 + Δlong;




LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);



i++;

}


else if (i == 4)
{

r_houi = y_kakudo;
migi_kakudo = r_houi - O_0_houi;

if (migi_kakudo < 0)
{
migi_kakudo = (float) (360.0+migi_kakudo);
}



if(migi_kakudo > 0)
{

migi_haba = (float) (K_kyori * Math
.tan((migi_kakudo) / 180.0 * Math.PI));
}
else
{

migi_haba = (float) (K_kyori * Math
.tan((migi_kakudo + 360) / 180.0 * Math.PI));

}


waste = (float) (2*K_kyori * Math.tan((migi_kakudo+hidari_kakudo)/2 * Math.PI  / 180.0)*0.5);

e = (float) (Math.sqrt(K_kyori * K_kyori + migi_haba
* migi_haba));
Δlat = (float) (0.0001 * e) / 10;
Δlong = (float) (0.0001 * e) / 10;
lat2 = lat0 + Δlat;

long2 = long0 + Δlong;



Button hidari = (Button) findViewById(R.id.satuei);
get_tyousei = get_size(widthIn);

TextView low_2 = (TextView) findViewById(R.id.textView1);
low_2.setPadding(50, 60, 5, 5);

String txtStr = "<font color=#ff0000>側面</font>へ移動して、<font color=#ff0000>下部</font>を撮影して下さい";
low_2.setText(Html.fromHtml(txtStr));
low_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_side_asimoto);
image.setVisibility(View.VISIBLE);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.width);

i++;

}



else if (i == 5)
{

O_kyori = kyori;
O_0_0_houi = y_kakudo;


@SuppressWarnings("unused")
Button Oku_syomen = (Button) findViewById(R.id.satuei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView left_2 = (TextView) findViewById(R.id.textView1);
left_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>左端</font>を撮影して下さい";
left_2.setText(Html.fromHtml(txtStr));
left_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_sidekosimae);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);
i++;

}
else if (i == 6)
{

O_h_houi = y_kakudo;

O_hidari_kakudo = O_0_0_houi - O_h_houi;

if (O_hidari_kakudo < 0)
{
O_hidari_kakudo = (float) (360.0+O_hidari_kakudo);
}


@SuppressWarnings("unused")
Button Oku_migi = (Button) findViewById(R.id.satuei);

Button touroku = (Button) findViewById(R.id.touroku1);
touroku.setBackgroundResource(R.drawable.camera_decision1);
touroku.setEnabled(false);
get_tyousei = get_size(widthIn);

TextView right_2 = (TextView) findViewById(R.id.textView1);
right_2.setPadding(50, 60, 5, 5);

String txtStr = "ウエストの<font color=#ff0000>右端</font>を撮影して下さい";
right_2.setText(Html.fromHtml(txtStr));
right_2.setTextSize(9 * get_tyousei);

ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setImageResource(R.drawable.hito_guide_side_kosiusiro);
image.setVisibility(View.VISIBLE);

f = (float) (Math.sqrt(O_kyori * O_kyori + O_hidari_haba
* O_hidari_haba));
Δlat = (float) (0.0001 * f) / 10;
Δlong = (float) (0.0001 * f) / 10;
lat3 = lat0 + Δlat;
long3 = long0 + Δlong;

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setEnabled(false);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setBackgroundResource(R.drawable.height);

i++;

}

else if (i == 7)
{

O_m_houi = y_kakudo;
O_migi_kakudo = O_m_houi - O_0_0_houi;

if (O_migi_kakudo < 0)
{
O_migi_kakudo = (float) (360.0+O_migi_kakudo);
}



o_thight = (float) (2*K_kyori * Math.tan((O_migi_kakudo+O_hidari_kakudo)/2 * Math.PI  / 180.0)*0.5);


allthight= (float)Math.PI*(thight + o_thight)*(1+(3*(thight - o_thight)/(thight + o_thight)/(thight + o_thight)/(10+Math.sqrt(4-(3*Math.pow((thight - o_thight)/(thight + o_thight), 2))))));

allthight2 =(float)allthight;
Log.v("allthight2", String.valueOf(allthight2));


checkk = 1;
@SuppressWarnings("unused")ImageView image = (ImageView) findViewById(R.id.Guidance);
image.setVisibility(View.INVISIBLE);

get_tyousei = get_size(widthIn);
TextView endtouroku = (TextView) findViewById(R.id.textView1);
String txtStr = "<font color=#ff0000>OKボタン</font>が使用可能になりました";
endtouroku.setText(Html.fromHtml(txtStr));
endtouroku.setTextSize(9 * get_tyousei);

Button tourok = (Button) findViewById(R.id.touroku1);
tourok.setBackgroundResource(R.drawable.camera_decision2);
tourok.setEnabled(true);

LinearLayout target = (LinearLayout) findViewById(R.id.target);
target.setVisibility(View.INVISIBLE);
i++;



}


if (myCamera != null)
{
myCamera.takePicture(mShutterListener, null,
mPictureListener);
}

return;
}

});
}

public static boolean oneClickEvent()
{
long time = System.currentTimeMillis();
if (time - ClickTime < standTime)
{
return false;
}
ClickTime = time;
return true;
}

public static float get_size(float size)
{
float tyousei_2;
tyousei_2 = 8 / (10 / size);
return tyousei_2;
}

@Override
protected void onResume()
{
if (locman != null)
{


//locman.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

}

// Listenerの登録
manager.registerListener(this,
manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
SensorManager.SENSOR_DELAY_UI);
manager.registerListener(this,
manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
SensorManager.SENSOR_DELAY_UI);
manager.registerListener(this,
manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
SensorManager.SENSOR_DELAY_UI);

super.onResume();



}

@Override
public void onPause()
{
if (locman != null)
{

//  locman.removeUpdates(this);

}
super.onPause();
}

@Override
protected void onStop()
{
// TODO Auto-generated method stub
super.onStop();
// Listenerの登録解除
manager.unregisterListener(this);
}

@Override
public void onLocationChanged(final Location location)
{

set_lat = location.getLatitude();
set_lon = location.getLongitude();
set_koudo = location.getAltitude();
set_high = ((set_koudo / 4) * 0.3048);

Log.v("----------", "----------");
Log.v("Latitude", String.valueOf(location.getLatitude()));
Log.v("Longitude", String.valueOf(location.getLongitude()));
Log.v("Accuracy", String.valueOf(location.getAccuracy()));
Log.v("Altitude", String.valueOf(location.getAltitude()));
Log.v("Time", String.valueOf(location.getTime()));
/*Log.v("Speed", String.valueOf(location.getSpeed()));
 Log.v("Bearing", String.valueOf(location.getBearing()));*/
}

@Override
public void onProviderDisabled(String provider)
{

}

@Override
public void onProviderEnabled(String provider)
{

}

@Override
public void onStatusChanged(String provider, int status, Bundle extras)
{
switch (status)
{
case LocationProvider.AVAILABLE:
Log.v("Status", "AVAILABLE");
break;
case LocationProvider.OUT_OF_SERVICE:
Log.v("Status", "OUT_OF_SERVICE");
break;
case LocationProvider.TEMPORARILY_UNAVAILABLE:
Log.v("Status", "TEMPORARILY_UNAVAILABLE");
break;

}
}

@Override
public void onAccuracyChanged(Sensor sensor, int accuracy)
{
// TODO Auto-generated method stub
}

@Override
public void onSensorChanged(SensorEvent event)
{
switch (event.sensor.getType())
{
case Sensor.TYPE_ACCELEROMETER:
gravity = event.values.clone();
break;
case Sensor.TYPE_MAGNETIC_FIELD:
geomagnetic = event.values.clone();
break;
case Sensor.TYPE_ORIENTATION:
orientation = event.values.clone();
break;
}

if (gravity != null && geomagnetic != null && orientation != null)
{
time++;

SensorManager.getRotationMatrix(inR, I, gravity, geomagnetic);

SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X,
SensorManager.AXIS_Y, outR);

SensorManager.getOrientation(outR, attitude);

//t_kakudo = (-1) * orientation[1];//傾斜角 0は方位角


YangleArray[loop][0] = (float)Math.cos(orientation[0]/180.0 * Math.PI);
YangleArray[loop][1] = (float)Math.sin(orientation[0]/180.0 * Math.PI);
distance[loop] = (float) ( Mystature2 * Math.tan(t_kakudo / 180.0 * Math.PI));
tate_kakudo[loop] = (float) (-1) * orientation[1];



loop++;
//loop_houi++;

if(loop>=30)loop=0;
//if(loop_houi>=30)loop_houi=0;

sum1 = 0;
sum2 = 0;
sum3 = 0;
sum4 = 0;

for(k=0;k<30;k++)
{
sum1 += YangleArray[k][0];
sum2 += YangleArray[k][1];
sum3 += distance[k];
sum4 += tate_kakudo[k];

}


Ave1 = sum1/30;
Ave2 = sum2/30;
T_distance = sum3/30;
T_kakudo = sum4/30;
houi = (float)(Math.atan2(Ave2,Ave1)/Math.PI*180);
kyori = T_distance;
t_kakudo = T_kakudo;
y_kakudo = (float) (180 + houi);


}
}

public static  double doRad(double a){
return a/180* Math.PI;
}

public static  double radDo(double a){
return a*180/ Math.PI;
}


public static double xy(double x,double y){
return Math.pow(x,y);
}


public static void  vincenty(double latitude,double longitude,double houikaku12,double kilometer){


double lat1 = doRad(latitude);
double lng1 = doRad(longitude);
double alpha12 = doRad(houikaku12);
double length = kilometer;

double sinAlpha1 = Math.sin(alpha12);
double cosAlpha1 = Math.cos(alpha12);
double tanU1 = (1-f2) * Math.tan(lat1), cosU1 = 1 / Math.sqrt((1 + tanU1 * tanU1)), sinU1 = tanU1 * cosU1;
double sigma1 = Math.atan2(tanU1, cosAlpha1);
double sinApha = cosU1 * sinAlpha1;
double cos2alpha = 1 - sinApha*sinApha;
double u2 = cos2alpha * (1 - (1-f2)*(1-f2)) / ((1-f2)*(1-f2)); //(a*a - b*b) / (b*b);
double A = 1 + u2 / 16384 * (4096 + u2 * (-768 + u2 * (320 - 175 * u2)));
double B = u2 / 1024 * (256 + u2 * (-128 + u2 * (74 - 47 * u2)));
double sigma = length / (Radius_short * A);
double cos2sm,sinSigma,cosSigma,deltaSigma,dSigma,sigma3,count=0;

do {
cos2sm = Math.cos(2 * sigma1 + sigma);
sinSigma = Math.sin(sigma);
cosSigma = Math.cos(sigma);
deltaSigma = B * sinSigma * (cos2sm + B / 4 * (cosSigma * (-1 + 2 * cos2sm * cos2sm)
- B / 6 * cos2sm * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2sm * cos2sm)));
dSigma = sigma;
sigma3 = length / (Radius_short * A) + deltaSigma;
if (count++>10) { break; }
} while (Math.abs(sigma - dSigma) > 1e-12);

double  tmp = sinU1*sinSigma - cosU1*cosSigma*cosAlpha1;
double lat2 = Math.atan2(sinU1*cosSigma + cosU1*sinSigma*cosAlpha1, (1-f2)*Math.sqrt(sinApha*sinApha + tmp*tmp));
double lamb = Math.atan2(sinSigma*sinAlpha1, cosU1*cosSigma - sinU1*sinSigma*cosAlpha1);
double C = f2/16*cos2alpha*(4+f2*(4-3*cos2alpha));
double L = lamb - (1-C) * f2 * sinApha
* (sigma3 + C*sinSigma*(cos2sm+C*cosSigma*(-1+2*cos2sm*cos2sm)));
double lon2 = (lng1+L+3*Math.PI) % (2*Math.PI) - Math.PI;  // normalise to -180...+180
LAT2 = radDo(lat2);
LON2 = radDo(lon2);

latnow = String.valueOf(LAT2);
lngnow = String.valueOf(LON2);
}

public float gethigh() {
// TODO 自動生成されたメソッド・スタブ
return dress2;
}

public float getwidth() {

// TODO 自動生成されたメソッド・スタブ
return yokohaba;
}

public float getokuyuki() {
// TODO 自動生成されたメソッド・スタブ
return sleeve2;
}

public  float getinseam() {

return inseam;

}

public  float getwaste() {

return allwaste2;
}

public  float gethip() {

return allhip2;
}

public  float getthight() {

return allthight2;
}
public  float gethantei() {

return hantei;
}

public  float getheight() {

return  height1;
}


public static String ValueA() {
return latnow;

}

public static String high() {
return highnow;

}

public static String ValueB() {
return lngnow;

}

public static float ValueC() {
return takasa;

}


public static float ValueD() {
return yokohaba;
}

public static float ValueE() {
return O_yokohaba;
}

public static String ValueF() {
return get_id_2;

}

public static float ValueG() {
return lat1;

}

public static float ValueH() {
return long1;

}

public static float ValueI() {
return lat2;

}

public static float ValueJ() {
return long2;

}

public static float ValueK() {
return lat3;

}

public static float ValueL() {
return long3;

}

public static float ValueN() {
return lat4;

}

public static float ValueM() {
return long4;

}

public static double lng() {
return set_lon;

}

public static double lat() {
return set_lat;

}

public static int limit() {
return limit;

}

public static String Version() {
return Version;

}


public boolean onTouch(View v, MotionEvent event) {
// TODO 自動生成されたメソッド・スタブ
return false;
}

public void onConnected(Bundle arg0) {
// TODO 自動生成されたメソッド・スタブ

}

public void onDisconnected() {
// TODO 自動生成されたメソッド・スタブ

}

@Override
public boolean dispatchKeyEvent(KeyEvent event)
{
// TODO Auto-generated method stub
if (event.getAction() == KeyEvent.ACTION_DOWN)
{
if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
{
return false;
}
}
return super.dispatchKeyEvent(event);
}

public void onClick(View v)
{
// TODO 自動生成されたメソッド・スタブ
switch (v.getId())
{
case R.id.touroku1:
// sub02へボタン押下
// Intentオブジェクト生成

if (i == 5)
{
O_yokohaba = yokohaba;

}

Intent intent = new Intent(this, Result.class);
// 画面呼び出し
startActivity(intent);
limit = 0;
// 自画面終了
this.finish();

break;
case R.id.end:
// メニューボタン押下
// Intentオブジェクト生成
Intent intent1 = new Intent(this, Result.class);
// 画面呼び出し
startActivity(intent1);
limit = 1;
// 自画面終了
this.finish();
}


}

public static float check() {
return checkk;

}

@Override
protected void onDestroy()
{
// TODO 自動生成されたメソッド・スタブ
super.onDestroy();
}

}
