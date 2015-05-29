package in.ovtech.ndtower;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


public class PopUpImage extends ActionBarActivity {

    private void showAsPopup(Activity activity) {
        //To show activity as dialog and dim the background, you need to declare android:theme="@style/PopupTheme" on for the chosen activity on the manifest
        activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        //activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
           //     WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
       // params.alpha = 1.0f;
       // params.dimAmount = 0f;
        activity.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        // This sets the window size, while working around the IllegalStateException thrown by ActionBarView
        activity.getWindow().setLayout(450, 450);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showAsPopup(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up_image);
       // getWindowManager().updateViewLayout();
       Intent intent= getIntent();
       int imgId = intent.getIntExtra("f",0);
        ImageView img1 = (ImageView) findViewById(R.id.image1);
        img1.setImageResource(imgId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pop_up_image, menu);
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
