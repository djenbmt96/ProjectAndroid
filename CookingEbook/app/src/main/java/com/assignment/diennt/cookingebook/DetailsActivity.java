package com.assignment.diennt.cookingebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView mTextView = (TextView) findViewById(R.id.ttest);
        ImageView mImageView = (ImageView) findViewById(R.id.imgBig);

        Integer[] imgs = new Integer[10];
        imgs[0] = R.drawable.picone;
        imgs[1] = R.drawable.pictwo;
        imgs[2] = R.drawable.picthree;
        imgs[3] = R.drawable.picfourth;
        imgs[4] = R.drawable.picfifth;

        imgs[5] = R.drawable.picone;
        imgs[6] = R.drawable.pictwo;
        imgs[7] = R.drawable.picthree;
        imgs[8] = R.drawable.picfourth;
        imgs[9] = R.drawable.picfifth;



        Bundle bd = getIntent().getExtras();
        int position = bd.getInt("text",-1);
        if (position != -1) {
//            Toast.makeText(this, "details activity" + position, Toast.LENGTH_SHORT).show();

            mTextView.setText(Html.fromHtml(getResources().getStringArray(R.array.cooks_recipe)[position]));
        }
        else{
            position = bd.getInt("image",-1);
            mImageView.setImageDrawable(getResources().getDrawable(imgs[position]));
        }



    }
}
