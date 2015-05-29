package in.ovtech.ndtower;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.ovtech.ndtower.helper.PersonNames;
import in.ovtech.ndtower.helper.SQLiteHandler;

public class AddActivity extends ActionBarActivity {
    private void showAsPopup(Activity activity) {
        //To show activity as dialog and dim the background, you need to declare android:theme="@style/PopupTheme" on for the chosen activity on the manifest
        activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 1.0f;
        params.dimAmount = 0f;
        activity.getWindow().setAttributes(params);

        // This sets the window size, while working around the IllegalStateException thrown by ActionBarView
        activity.getWindow().setLayout(700, 850);

    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            showAsPopup(this);
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_add);
        String calingclass=getIntent().getStringExtra("name");
        Log.d("mydata", calingclass);
        if(calingclass.equals("Person")) {
            //setContentView(R.layout.activity_add);

            Button btnAddPerson = (Button) findViewById(R.id.btnAddPerson);
            final EditText pName = (EditText) findViewById(R.id.pname);
            final EditText pEmail = (EditText) findViewById(R.id.pemail);
            final EditText pMobile = (EditText) findViewById(R.id.pmobile);
            final EditText pWing = (EditText) findViewById(R.id.pwing);
            final EditText pFlatNo = (EditText) findViewById(R.id.pflatno);
            final EditText pOccupation = (EditText) findViewById(R.id.poccupation);
            final EditText pOccLocation = (EditText) findViewById(R.id.pocclocation);

            Log.d("inside", pWing.getText().toString());
            // Login button Click Event

            btnAddPerson.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Person person = new Person();
                    person.set_name(pName.getText().toString());
                    person.set_email(pEmail.getText().toString());
                    person.set_mobile(pMobile.getText().toString());
                    person.set_occupation(pOccupation.getText().toString());
                    person.set_occulocation(pOccLocation.getText().toString());
                    //person.set
                    String name = pName.getText().toString();
                    String email = pEmail.getText().toString();
                    String mobile = pMobile.getText().toString();
                    String wing = pWing.getText().toString();
                    //String flatno = pFlatNo.getText().toString();
                    String occupation = pOccupation.getText().toString();
                    String occlocation =pOccLocation.getText().toString();

                    // Check for empty data in the form
                    if (name.trim().length() > 0 && email.trim().length() > 0 && mobile.trim().length() > 0
                            && occlocation.trim().length() > 0
                            && occupation.trim().length() > 0) {
                        // login user

                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                        db.addPerson(person);
                        Intent intent = new Intent(getApplicationContext(), ShowAllPersons.class);
                        //intent.putExtra("pos",1);
                        startActivity(intent);
                        finish();
                    } else {
                        // Prompt user to enter credentials
                        Toast.makeText((getApplicationContext()),
                                "Please enter the text!", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
            else if(calingclass.equals( "Flat")){

                setContentView(R.layout.addflat);


                    Button btnAddFlat = (Button) findViewById(R.id.btnAddFlat);



                btnAddFlat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Flat flat=new Flat();
                        flat.set_flatno(((EditText) findViewById(R.id.fFlatNo)).getText().toString());
                        flat.set_wing(((EditText) findViewById(R.id.fWing)).getText().toString());
                        flat.set_type(((EditText) findViewById(R.id.fType)).getText().toString());
                        if(  flat.get_flatno().trim().length() >0
                                && flat.get_wing().trim().length() >0
                                && flat.get_type().trim().length() >0

                                ){
                            SQLiteHandler db = new SQLiteHandler(getApplicationContext());

                            db.addFlat(flat);
                            Toast.makeText(getApplicationContext(),"Flat  Added",Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(getApplicationContext(),ShowAllFlats.class);

                            startActivity(intent);
                            finish();

                        } else{Toast.makeText((getApplicationContext()),
                                "Please enter the text!", Toast.LENGTH_LONG)
                                .show();}


                    }
                });
        }
        else if(calingclass.equals( "Vehicle")){

            //setContentView(R.layout.addvehicle);
            final Vehicle vehicle=new Vehicle();

            Button btnVehicle = (Button) findViewById(R.id.btnAddVehicle);



            btnVehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vehicle.set_vechilenumber(((EditText) findViewById(R.id.vNumber)).getText().toString());
                    vehicle.set_type(((EditText) findViewById(R.id.vType)).getText().toString());

                    if(  vehicle.get_vechilenumber().trim().length() >0
                            && vehicle.get_type().trim().length() >0


                            ){
                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());

                        db.addVehicle(vehicle);
                        Toast.makeText(getApplicationContext(),"Vehicle  Added",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(),ShowAllVehicles.class);

                        startActivity(intent);
                        finish();

                    } else{Toast.makeText((getApplicationContext()),
                            "Please enter the text!", Toast.LENGTH_LONG)
                            .show();}


                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
