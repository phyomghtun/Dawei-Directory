package pmt.daweiguide.apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

public class viewPageAdapter extends PagerAdapter {
    private Context context;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.ad1,R.drawable.ad3,R.drawable.ad2,R.drawable.ad1};


    public viewPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);

        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

   /*     view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.mmguider.apk" )));
                } else if(position==1){
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.baydinmm.apk" )));
                }else if(position==2){
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=mt.ghoststory.apk" )));
                }else if(position==3){
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.mmcookinguide.apk" )));
                }
            }
        });  */

        ViewPager vp=(ViewPager)container;
        vp.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View) object;
        vp.removeView(view);
    }


}
