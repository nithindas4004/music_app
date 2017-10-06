package com.example.das.afinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.List;

import static android.R.attr.y;

/**
 * Created by Belal on 2/23/2017.
 */

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    private DatabaseReference mDatabase;


    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private static MediaPlayer mediaPlayer;
    private Context context;
    private List<Upload> uploads;
    int a;



    public mAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Upload upload = uploads.get(position);
a=position;

      //  holder.textViewName.setText(upload.getName());


        // holder.imageView.setImageResource(Integer.parseInt(upload.getUrl())).into(holder.imageView);
        Glide.with(context).load((upload.getUrl())).into(holder.imageView);
        holder.card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
        if(position==0) {
    Constant.DATABASE_PATH_UPLOADS="hindi";
    Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
    v.getContext().startActivity(intent);
            }
        else if(position==1) {
                    Constant.DATABASE_PATH_UPLOADS="1992";
                    Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
                    v.getContext().startActivity(intent);
                }
    else if(position==2) {
        Constant.DATABASE_PATH_UPLOADS="1993";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==3) {
        Constant.DATABASE_PATH_UPLOADS="1994";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==4) {
        Constant.DATABASE_PATH_UPLOADS="1995";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==5) {
        Constant.DATABASE_PATH_UPLOADS="1996";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==6) {
        Constant.DATABASE_PATH_UPLOADS="1997";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==7) {
        Constant.DATABASE_PATH_UPLOADS="1998";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==8) {
        Constant.DATABASE_PATH_UPLOADS="1999";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }
    else if(position==9) {
        Constant.DATABASE_PATH_UPLOADS="2000";
        Intent intent = new Intent(context.getApplicationContext(), ShowImagesActivity.class);
        v.getContext().startActivity(intent);
    }

            }
        });
    }


    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public ImageView imageView;
        public CardView card;


        ViewHolder(View itemView) {
            super(itemView);


          //  textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            //textViewName.setOnClickListener(this);
            card = (CardView) itemView.findViewById(R.id.card_view);
            card.setOnClickListener(this);
            a=getAdapterPosition();


        }
        public void onDataChange(DataSnapshot snapshot) {
            //dismissing the progress dialog
            progressDialog.dismiss();

            //iterating through all the values in database
            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                Upload upload = postSnapshot.getValue(Upload.class);
                uploads.add(upload);
            }
        }


        @Override
        public void onClick(View v) {

        }
    }
}