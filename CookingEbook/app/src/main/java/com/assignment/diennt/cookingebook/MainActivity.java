package com.assignment.diennt.cookingebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc ;
    Integer[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (RecyclerView) findViewById(R.id.rc);
        ArrayList<Cook> cooks = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.cooks_title);
        getImages();
        for(int i=0; i<titles.length; i++){

            cooks.add(new Cook(titles[i],images[i]));
        }

        CookListAdapter adapter = new CookListAdapter(cooks, this, rc);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }
    private void getImages(){
        images = new Integer[10];
        images[0] = R.drawable.picone;
        images[1] = R.drawable.pictwo;
        images[2] = R.drawable.picthree;
        images[3] = R.drawable.picfourth;
        images[4] = R.drawable.picfifth;

        images[5] = R.drawable.picone;
        images[6] = R.drawable.pictwo;
        images[7] = R.drawable.picthree;
        images[8] = R.drawable.picfourth;
        images[9] = R.drawable.picfifth;
    }

}
