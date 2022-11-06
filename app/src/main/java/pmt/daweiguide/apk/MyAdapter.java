package pmt.daweiguide.apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter< BookViewHolder >{
    private Context mContext;
    private List< Categories > mCateList;

    MyAdapter(Context mContext, List< Categories > mCateList) {
        this.mContext = mContext;
        this.mCateList = mCateList;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new BookViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {

      holder.mImage.setImageResource(mCateList.get(position).getCateImage());
      //  Animation animation= AnimationUtils.loadAnimation(mContext,mCateList.get(position).getCateImage());
    //    holder.mImage.startAnimation(animation);

        holder.tv.setText(mCateList.get(position).getCName());
         holder.mCardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                   Intent mIntent = new Intent(mContext, ViewActivity.class);
               if(mCateList.get(holder.getAdapterPosition()).getCateName().equals("pagoda"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/pagoda.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }else if(mCateList.get(holder.getAdapterPosition()).getCateName().equals("waterfall"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/waterfall.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }else if(mCateList.get(holder.getAdapterPosition()).getCateName().equals("beach"))
                {
                    mIntent.putExtra("link","https://androlover.com/dawei_guide/beach.php");
                    mIntent.putExtra("title",mCateList.get(position).getCName());

                }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("mobile"))
                {
                    mIntent.putExtra("link","https://androlover.com/dawei_guide/mobile.php");
                    mIntent.putExtra("title",mCateList.get(position).getCName());

                }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("hotel"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/hotel.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("emergency"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/police.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("education"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/education.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("fashion"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/fashion.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("restaurant"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/restaurant.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("bus"))
             {
                 mIntent.putExtra("link","https://androlover.com/dawei_guide/bus.php");
                 mIntent.putExtra("title",mCateList.get(position).getCName());

             }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("plane"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/plane.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("hospital"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/hospital.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());
               } else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("shop"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/shop.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               } else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("university"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/university.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("bank"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/bank.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }else if (mCateList.get(holder.getAdapterPosition()).getCateName().equals("present"))
               {
                   mIntent.putExtra("link","https://androlover.com/dawei_guide/present.php");
                   mIntent.putExtra("title",mCateList.get(position).getCName());

               }
               // mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getFlowerName());
              //  mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getFlowerDescription());
               // mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getFlowerImage());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCateList.size();
    }
}
class  BookViewHolder extends RecyclerView.ViewHolder {

    CardView mCardView;
    ImageView mImage;
    TextView tv;
    BookViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mCardView = itemView.findViewById(R.id.cardview);
        tv=itemView.findViewById(R.id.tv);

    }
}
