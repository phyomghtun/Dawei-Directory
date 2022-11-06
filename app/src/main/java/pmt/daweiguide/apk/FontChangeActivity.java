package pmt.daweiguide.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class FontChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_change);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                finish();
                Intent i = new Intent(FontChangeActivity.this, FirstActivity.class);
                startActivity(i);
                // overridePendingTransition(R.anim.fade,R.anim.fade_out);

            }
        }, 500);

    }
}
