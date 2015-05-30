/**
 * Created by pranay on 30/5/15.
 */
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

import in.ovtech.ndtower.Person;
import in.ovtech.ndtower.R;
import in.ovtech.ndtower.ShowAllPersons;
import in.ovtech.ndtower.helper.PersonNames;
import in.ovtech.ndtower.helper.SQLiteHandler;

public class AddActivity1 extends ActionBarActivity {
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
        String calingclass = getIntent().getStringExtra("name");
        Log.d("mydata", calingclass);
        if (calingclass.equals("Person")) {
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
                    String occlocation = pOccLocation.getText().toString();

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

    }
}