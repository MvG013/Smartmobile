package com.example.csi;

import java.util.List;

import com.example.csi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//Some example code below on how to use CriminalProvider:
		final CriminalProvider criminalProvider = new CriminalProvider(getApplicationContext() );
		List<Criminal> criminals = criminalProvider.GetCriminals();


		String boxText = "";
		for(Criminal criminal : criminals) {
			List<Crime> crimes = criminal.crimes;
			boxText += criminal.name + " has " + crimes.size() + " crimes\n";
		}

		ListView listview = (ListView)findViewById(R.id.listView);
		CriminalListAdapter adapter = new CriminalListAdapter(getApplicationContext(),criminals);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(),CrimiActivity.class);
				intent.putExtra("pos",position);
				startActivity(intent);
			}
		});

	}

}
