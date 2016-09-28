package com.example.csi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class CrimiReportActivity extends Activity{


    public Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crimireport);
        Back = (Button)findViewById(R.id.button4);
        Intent intent = getIntent();
        CriminalProvider criminalProvider = new CriminalProvider(getApplicationContext());
        List<Criminal> criminals = criminalProvider.GetCriminals();
        Criminal criminal = criminals.get(intent.getIntExtra("pos",0));
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageDrawable(criminal.mugshot);

        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimiReportActivity.this , CrimiActivity.class);
                startActivity(intent);
            }
        });

    }
}

