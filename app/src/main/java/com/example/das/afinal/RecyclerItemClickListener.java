package com.example.das.afinal;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

/**
 * Created by das on 8/21/2017.
 */

public class RecyclerItemClickListener  implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    GestureDetector mGestureDetector;


    public interface OnItemClickListener{

        public void onItemClick(View view, int position);

    }






    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }


    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
