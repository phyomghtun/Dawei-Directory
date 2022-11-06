package pmt.daweiguide.apk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {
    static SharedPreferences sp;
    public static String myprefer = null;
    SharedPreferences.Editor editor;
    String image = "image";
    String state="state";
    String temp1="";
    String temp2="";
    Context context;
    String GET_JSON_DATA_HTTP_URL2 = "https://androlover.com/dawei_guide/ads.php";
    JsonArrayRequest jsonArrayRequest2 ;
    RequestQueue requestQueue2 ;

    Timer myTimer;
    CollapsingToolbarLayout ctl;
    ImageView ivCtl;
    ViewPager viewPager;
    Toolbar tb;
    RecyclerView mRecyclerView;
    List< Categories > mCateList;
    Categories mCateData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        int resId = R.anim.layout_animation_up_to_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(FirstActivity.this, resId);

        int REQUEST_PHONE_CALL = 1;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(FirstActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(FirstActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
            }
        }

        sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
        editor = sp.edit();

        if(sp.contains("Font")) {
            get();
        }

        JSON_DATA_WEB_CALL2();

     /*   viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPageAdapter viewPage=new viewPageAdapter(this);
        viewPager.setAdapter(viewPage);

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);  */


        tb=(Toolbar)findViewById(R.id.nnl_toolbar);
      //  tb.setLogo(R.drawable.tbic);
        setSupportActionBar(tb);
        ctl=(CollapsingToolbarLayout)findViewById(R.id.nnl_ctl);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(FirstActivity.this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setLayoutAnimation(animation);


        mCateList = new ArrayList<>();
        mCateData = new Categories("pagoda",R.drawable.pagoda,"ဘုရားေစတီမ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("hotel",R.drawable.hotel,"ဟိုတယ္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("waterfall",R.drawable.waterfall,"ေရတံခြန္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("beach",R.drawable.beach,"ကမ္းေျခမ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("restaurant",R.drawable.restaurant,"စားေသာက္ဆိုင္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("mobile",R.drawable.mobile,"ဖုန္းဆိုင္မ်ား ");
        mCateList.add(mCateData);
        mCateData = new Categories("fashion",R.drawable.fashion,"အထည္ဆိုင္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("emergency",R.drawable.emergency ,"အေရးေပၚဌာနမ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("hospital",R.drawable.hospital ,"ေဆးရံုႏွင့္ေဆးခန္းမ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("education",R.drawable.border ,"ပညာေရးဝန္ေဆာင္မႈမ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("university",R.drawable.university ,"ေကာလိပ္ ႏွင့္ တကၠသိုလ္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("bus",R.drawable.bus ,"ကားလက္မွတ္ဆိုင္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("plane",R.drawable.plane ,"ေလယာဥ္လက္မွတ္ဆိုင္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("shop",R.drawable.shop ,"ကုန္တိုက္ႏွင့္စတိုးဆိုင္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("bank",R.drawable.bank ,"ဘဏ္မ်ား");
        mCateList.add(mCateData);
        mCateData = new Categories("present",R.drawable.present ,"လက္ေဆာင္ပစၥည္းဆိုင္မ်ား");
        mCateList.add(mCateData);


        MyAdapter myAdapter = new MyAdapter(FirstActivity.this, mCateList);
        mRecyclerView.setAdapter(myAdapter);

        //////////////////////

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(this);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            FirstActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }else if(viewPager.getCurrentItem()==3){
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    public void setTitleText(String title){
        ctl.setTitle(title);
    }

    public void setTitleImage(int res){
        ivCtl.setImageResource(res);
    }

    public void JSON_DATA_WEB_CALL2() {

        jsonArrayRequest2 = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL2,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL2(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue2 = Volley.newRequestQueue(this);

        requestQueue2.add(jsonArrayRequest2);




    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL2(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                temp1=json.getString(image);
                temp2=json.getString(state);

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        if(temp2.length()!=0) {
            Intent i = new Intent(FirstActivity.this, AdsActivity.class);
            i.putExtra("link",temp1);
            startActivity(i);
            overridePendingTransition(R.anim.fade,R.anim.fade_out);
        //    Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
          //  i.startAnimation(animation);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_src, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.font) {
          //  Toast.makeText(FirstActivity.this,"font Change",Toast.LENGTH_LONG).show();
            String f= sp.getString("Font",null);
            if(sp.contains("Font")) {
                if (f.equals("zawgyi.ttf")) {
                    change("unicode.ttf");
                } else {
                    change("zawgyi.ttf");
                }
            }else{
                change("unicode.ttf");
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void get(){
        if(sp.contains("Font")){
            String ff=sp.getString("Font",null);
            TypefaceUtils.overrideFont(getApplicationContext(), "SERIF", ff);
        }
    }
    public void change(String ff) {
        sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Font", ff);
        editor.commit();
        TypefaceUtils.overrideFont(getApplicationContext(), "SERIF", ff);
        Intent i=new Intent(FirstActivity.this,FontChangeActivity.class);
        startActivity(i);
        finish();
    }
}
