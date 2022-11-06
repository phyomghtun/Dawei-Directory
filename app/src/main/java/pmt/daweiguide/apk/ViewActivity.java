package pmt.daweiguide.apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    List<GetDataAdapter> GetDataAdapter1;
    private SwipeRefreshLayout mSwipeLayout;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL;
    String NAME = "name";
    String IMAGE_URL = "image";
    String lat = "latitude";
    String log = "longitude";
    String DES = "des";
    String PHONE = "phone";
    ProgressDialog progressDialog;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;
    static SharedPreferences sp;
    public static String myprefer = null;
    SharedPreferences.Editor editor;
    Typeface face;
    String ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        TextView textCustomTitle = (TextView) findViewById(R.id.custom_title);

        sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
        editor = sp.edit();





        Intent intent = getIntent();
        GET_JSON_DATA_HTTP_URL = intent.getStringExtra("link");
        String title=intent.getStringExtra("title");
        textCustomTitle.setText(title);

        if(sp.contains("Font")) {
            // get();
            ff=sp.getString("Font",null);
            face = Typeface.createFromAsset(getAssets(), ff);
            textCustomTitle.setTypeface(face);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   toolbar.setLogo(R.drawable.tb_ic);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
       toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isOnline2()) {
                    JSON_DATA_WEB_CALL();
                    // new SanZtoU(FirstActivity.this).ForceToUni("Font1.ttf", true);
                } else{
                    mSwipeLayout.setRefreshing(false);
                    Toast.makeText(getApplicationContext(),"No internet connection", Toast.LENGTH_SHORT).show();
                }
            } });

        mSwipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);


        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        // recyclerViewlayoutManager = new LinearLayoutManager(this);

        // recyclerView.setLayoutManager(recyclerViewlayoutManager);
        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.getProgress();
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        GetDataAdapter1.clear();

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

              //  GetDataAdapter2.setName(json.getString(NAME));
                byte[] bytes1 = json.getString(NAME).getBytes();
                String d1= new String(android.util.Base64.decode(bytes1, Base64.DEFAULT));
                GetDataAdapter2.setName(d1);


                GetDataAdapter2.setImage(json.getString(IMAGE_URL));
                GetDataAdapter2.setLat(json.getString(lat));
                GetDataAdapter2.setLog(json.getString(log));

            // GetDataAdapter2.setDes(json.getString(DES));
                byte[] bytes2 = json.getString(DES).getBytes();
               String d2= new String(android.util.Base64.decode(bytes2, Base64.DEFAULT));
               GetDataAdapter2.setDes(d2);


                GetDataAdapter2.setPhone(json.getString(PHONE));

              /*  String d;
                byte[] bytes1 = json.getString(JSON_IMAGE_TITLE_NAME4).getBytes();
                d= new String(android.util.Base64.decode(bytes1, Base64.DEFAULT));
                GetDataAdapter2.setImageTitleNamee4(d); */



                //  progressDialog.dismiss();

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }
        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this);

        int resId = R.anim.layout_animation_up_to_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(ViewActivity.this, resId);
        recyclerView.setAdapter(recyclerViewadapter);
        recyclerView.setLayoutAnimation(animation);

        progressDialog.dismiss();
        mSwipeLayout.setRefreshing(false);

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    /////////////
    protected boolean isOnline2() {
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void get(){
        if(sp.contains("Font")){
            String ff=sp.getString("Font",null);
            TypefaceUtils.overrideFont(getApplicationContext(), "SERIF", ff);
        }
    }

}
