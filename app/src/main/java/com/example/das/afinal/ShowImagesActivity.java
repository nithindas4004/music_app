package com.example.das.afinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowImagesActivity extends AppCompatActivity {
    //recyclerview object
    private RecyclerView recyclerView;
    private TextView tx;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Upload> uploads;
    private List<Upload> upl;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mToggle;
   // FirebaseAuth mAuth;
    //FirebaseAuth.AuthStateListener mAuthListner;

    //@Override
    //protected void onStart() {
      //super.onStart();
   // mAuth.addAuthStateListener(mAuthListner);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
       initNavigationDrawer();
        mdrawer=(DrawerLayout)findViewById(R.id.drawerlayout1);
        mToggle=new ActionBarDrawerToggle(this,mdrawer,R.string.open,R.string.close);
        mdrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
      //mAuth= FirebaseAuth.getInstance();
        //mAuthListner=new FirebaseAuth.AuthStateListener(){
            //@Override
          //  public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
          //      if(firebaseAuth.getCurrentUser()==null)
            //    {
             //       startActivity(new Intent(ShowImagesActivity.this,login.class));
              //}
        //}
        //};






        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();
        upl = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference(Constant.DATABASE_PATH_UPLOADS);

        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploads.add(upload);

                }



                //creating adapter
                adapter = new MyAdapter(getApplicationContext(), uploads);


                //adding adapter to recyclerview
                mRecyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.new_account:
                        Intent intent = new Intent(ShowImagesActivity.this, Mainpage.class);
                        startActivity(intent);
                        break;
                    case R.id.new_account2:
                        Intent intent1 = new Intent(ShowImagesActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.new_account3:
                       //mAuth.signOut();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
