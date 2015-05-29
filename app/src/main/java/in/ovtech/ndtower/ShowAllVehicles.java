package in.ovtech.ndtower;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import in.ovtech.ndtower.Flat;
import in.ovtech.ndtower.NDFlatListBaseAdapter;
import in.ovtech.ndtower.NDVehiclesAdapter;
import in.ovtech.ndtower.R;
import in.ovtech.ndtower.Vehicle;
import in.ovtech.ndtower.helper.SQLiteHandler;

public class ShowAllVehicles extends ActionBarActivity {
    private ArrayList<Vehicle> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_vehicles);
        SQLiteHandler dbHelper = new SQLiteHandler(getApplicationContext());
        // displayListView(rootView);
        mylist= dbHelper.getAllVehicles();
        Vehicle newrec  =new Vehicle();
        newrec.set_vechilenumber("Create a new Vehicle entry");
        mylist.add(0,newrec);
        if(mylist.size() > 0) {
            NDVehiclesAdapter myAdapter = new NDVehiclesAdapter(mylist, getApplicationContext());
            ListView listView = (ListView) findViewById(R.id.listView1);
            listView.setAdapter(myAdapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position == 0){
                        Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                        intent.putExtra("name","Vehicle");
                        startActivity(intent);
                       // finish();
                    }else
                    {
                        Intent intent=new Intent(getApplicationContext(),VehicleDetailActivity.class);
                        intent.putExtra("id",mylist.get(position).get_id());
                        startActivity(intent);

                        //finish();
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_vehicles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
