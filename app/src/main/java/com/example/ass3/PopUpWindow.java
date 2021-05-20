package com.example.ass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.fragment.app.Fragment;

public class PopUpWindow extends Activity {
    private static final String TAG = "PopUpWindow";
    RatingBar ratingBar;
    Button btnCancel, btnOK;
    float myRating;



    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.popup_window);
        ratingBar = findViewById(R.id.ratingBar);
        btnCancel = findViewById(R.id.btnCancel);
        btnOK = findViewById(R.id.btnOK);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        ratingBar.setNumStars(5);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRating = ratingBar.getRating();
                Log.d(TAG, "onClick: " + Float.toString(myRating));
                Intent resultIntent = new Intent();
                resultIntent.putExtra("rating",myRating);
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });




    }

}
