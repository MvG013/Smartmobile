package mvg.csi_week_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CriminalslistActivity extends AppCompatActivity {

    public ListView ListviewCriminals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criminalslist);

        //Get a reference to the listview
        ListView listview = (ListView) findViewById(R.id.listViewCriminals);
        //Get a reference to the list with names
        final String[] criminals = getResources().getStringArray(R.array.names);
        //Create an adapter that feeds the data to the listview
        listview.setAdapter(
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        criminals
                )
        );

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Get the name from the array that is in the same position as the chosen listitem.
                String name = criminals[position];
                //Todo start intent and pass name using putExtra
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }