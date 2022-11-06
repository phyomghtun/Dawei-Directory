package pmt.daweiguide.apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsActivity extends AppCompatActivity {
    String phone,name,des,image,lat,log;
    NetworkImageView  networkImageView;
    ImageLoader imageLoader1;
    TextView tv;
    static SharedPreferences sp;
    public static String myprefer = null;
    SharedPreferences.Editor editor;
    Typeface face;
    String ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        TextView textCustomTitle = (TextView) findViewById(R.id.custom_title);

        sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
        editor = sp.edit();

        if(sp.contains("Font")) {
           // get();
             ff=sp.getString("Font",null);
            face = Typeface.createFromAsset(getAssets(), ff);
            textCustomTitle.setTypeface(face);
        }

        networkImageView=findViewById(R.id.img);
        tv=findViewById(R.id.tv);

        Intent intent = getIntent();
        phone = intent.getStringExtra("Phone");
        name=intent.getStringExtra("Name");
        image=intent.getStringExtra("Image");
        lat=intent.getStringExtra("Lat");
        log=intent.getStringExtra("Log");
        des=intent.getStringExtra("Des");
        tv.setText(des);

        textCustomTitle.setText(name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setLogo(R.drawable.tb_ic);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setTitle("");
      //  toolbar.setTitleTextAppearance(this, R.style.BitterTextAppearance);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        networkImageView = (NetworkImageView) findViewById(R.id.img);

        imageLoader1 = ServerImageParseAdapter.getInstance(this).getImageLoader();
        imageLoader1.get(image,
                ImageLoader.getImageListener(
                        networkImageView,//Server Image
                        R.drawable.icon,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        networkImageView.startAnimation(animation);
        tv.startAnimation(animation);
        networkImageView.setImageUrl(image, imageLoader1);


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.startAnimation(animation);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int REQUEST_PHONE_CALL = 1;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }
                    else
                    {
                        startActivity(intent);
                    }
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
        if(phone.equals("0")){
            fab1.hide();
        }
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.startAnimation(animation);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(DetailsActivity.this,"Maps",Toast.LENGTH_LONG).show();
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+log);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void get(){
        if(sp.contains("Font")){
            String ff=sp.getString("Font",null);
            TypefaceUtils.overrideFont(getApplicationContext(), "SERIF", ff);
        }
    }
}
