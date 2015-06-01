package in.ovtech.ndtower;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.ovtech.ndtower.helper.SQLiteHandler;


public class ShowAllFlats extends ActionBarActivity {
    private NDFlatListBaseAdapter myAdapter;
    private ArrayList<Flat> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_flats);
        SQLiteHandler dbHelper = new SQLiteHandler(getApplicationContext());
        // displayListView(rootView);
        mylist= dbHelper.getAllFlatNames();
        Flat newrec  =new Flat();
        newrec.set_wing("Create a new Flat entry");
        newrec.set_flatno("");
        newrec.set_id(777);
        mylist.add(0,newrec);
        if(mylist.size() > 0) {
            myAdapter = new NDFlatListBaseAdapter(mylist, this);
            ListView listView = (ListView) findViewById(R.id.listView1);
            listView.setAdapter(myAdapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int selectid =Integer.parseInt(((TextView)view.findViewById(R.id.cid)).getText().toString());
                    if(selectid == 777){
                        Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                        intent.putExtra("name","Flat");
                        startActivity(intent);
                        //finish();
                    }
                    else
                    {
                        //Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.cid)).getText().toString(), Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(getApplicationContext(),FlatDetailActivity.class);
                        intent.putExtra("id",selectid);
                        startActivity(intent);

                        //finish();
                    }
                }
            });
        }
        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                myAdapter.getFilter().filter(s.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_flats, menu);

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
