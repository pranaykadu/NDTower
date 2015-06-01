package in.ovtech.ndtower;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class GridViewActivity extends ActionBarActivity  implements
        AdapterView.OnItemClickListener {

    TextView selection;
    String[] items = { "Person", "Flat", "Vehicle" ,"Images"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        selection = (TextView) findViewById(R.id.selection);

        GridView gv = (GridView) findViewById(R.id.grid);

        ArrayAdapter<String> aa = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items );

        gv.setAdapter(aa);
        gv.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid_view, menu);
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
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        selection.setText(items[position]);

        Intent intent= new Intent();
        switch (position) {
            case 0:
                //cls = Person.class;
                intent.setClass(getApplicationContext(),ShowAllPersons.class);

                break;
            case 1:
                //cls = Flat.class;
                intent.setClass(getApplicationContext(),ShowAllFlats.class);
                break;
            case 2:
                //cls = Vehicle.class;
                intent.setClass(getApplicationContext(),ShowAllVehicles.class);
                break;
            case 3:
                intent.setClass(getApplicationContext(),PopUpImage.class);
                break;


        }
        startActivity(intent);
    }
}
