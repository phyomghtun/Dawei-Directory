package pmt.daweiguide.apk;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.io.File;
import java.util.List;


import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ProgressDialog  progressDialog;
    public static final int progress_bar_type = 0;
    private long enqueue;
    private DownloadManager dm;

    public String file;

    Context context;

    List<GetDataAdapter> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.fade);
        v.startAnimation(animation);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getImage(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        R.drawable.icon,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getImage(), imageLoader1);

       Viewholder.name.setText(getDataAdapter1.getName());



        Viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Name",getDataAdapter1.getName());
                intent.putExtra("Image",getDataAdapter1.getImage());
                intent.putExtra("Phone",getDataAdapter1.getPhone());
                intent.putExtra("Lat",getDataAdapter1.getLat());
                intent.putExtra("Log",getDataAdapter1.getLog());
                intent.putExtra("Des",getDataAdapter1.getDes());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public NetworkImageView networkImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name) ;


            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;

        }
    }


}
