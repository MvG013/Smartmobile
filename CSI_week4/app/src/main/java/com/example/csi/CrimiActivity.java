package com.example.csi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CrimiActivity extends Activity {



    public Button Report;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crimi);
        Report = (Button)findViewById(R.id.button);
        Report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimiActivity.this, CrimiReportActivity.class);
                startActivity(intent);
            }
        });
        TextView TX1 =  (TextView) findViewById(R.id.textView1);
        TextView TX2 = (TextView) findViewById(R.id.textView7);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        CriminalProvider criminalProvider = new CriminalProvider(getApplicationContext());
        List<Criminal> criminals = criminalProvider.GetCriminals();
        Criminal criminal = criminals.get(intent.getIntExtra("pos",0));
        TX2.setText("€" + String.valueOf(criminal.getBountyInDollars())+ ",-");
        TX1.setText(criminal.name);
        image.setImageDrawable(criminal.mugshot);

    }
}
