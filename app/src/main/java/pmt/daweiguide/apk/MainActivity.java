package pmt.daweiguide.apk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    static SharedPreferences sp;
    public static String myprefer = null;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(myprefer, Context.MODE_PRIVATE);
        editor = sp.edit();

        if (!NetTest()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Unstable Connection Please Check Your Network")
                    .setCancelable(false)
                    .setTitle("Network Fail")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            change();
        }
    }
    public void change(){

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                finish();
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(i);
               // overridePendingTransition(R.anim.fade,R.anim.fade_out);

            }
        }, 3000);

    }
    private boolean NetTest() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
