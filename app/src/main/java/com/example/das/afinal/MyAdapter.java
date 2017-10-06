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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private DatabaseReference mDatabase;


    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private static MediaPlayer mediaPlayer;
    private Context context;
    private List<Upload> uploads;



    public MyAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Upload upload = uploads.get(position);

if(position==1)
{
    Constant.DATABASE_PATH_UPLOADS="hindi";
}
        holder.textViewName.setText(upload.getName());


      //holder.imageView.setImageResource(Integer.parseInt(upload.getUrl())).into(holder.imageView);
      // Glide.with(context).load((upload.getUrl())).into(holder.imageView);
        holder.card.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }


                mediaPlayer = MediaPlayer.create(v.getContext(), Uri.parse(uploads.get(position).getUrl()));
                mediaPlayer.start();


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

            textViewName = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
             textViewName.setOnClickListener(this);
             card = (CardView) itemView.findViewById(R.id.card_view);


         }
        public void onDataChange(DataSnapshot snapshot) {
            //dismissing the progress dialog
            progressDialog.dismiss();

            //iterating through all the values in database
            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                Upload upload = postSnapshot.getValue(Upload.class);
                uploads.add(upload);
            }
            }        @Override
        public void onClick(View v) {

        }
    }
}