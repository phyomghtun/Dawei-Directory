package pmt.daweiguide.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AdsActivity extends AppCompatActivity {
ImageView imgView;
Button btn;
TextView cancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        imgView=findViewById(R.id.imgview);
        cancle=findViewById(R.id.cancle);

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        Picasso.get().load(link).into(imgView);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,R.anim.fade_out );
            }
        });

    }

}
