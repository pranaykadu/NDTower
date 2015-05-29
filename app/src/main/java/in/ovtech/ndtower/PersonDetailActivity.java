package in.ovtech.ndtower;

import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import in.ovtech.ndtower.helper.SQLiteHandler;


public class PersonDetailActivity extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person_deatil, menu);
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        Log.d("onTabSelected ", Integer.toString(tab.getPosition()));
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public int get_personID() {
            return _personID;
        }
        public void set_personID(int _personID) {
            this._personID = _personID;
        }

        private int _personID;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position ==0)
                return PersonalFragment.newInstance(position + 1,_personID);
            else if (position==1)
                return FamilyFragment.newInstance(position + 1,_personID);
            else if (position==2)
                return PersonalFragment.newInstance(position + 1,_personID);

            else
                return PersonalFragment.newInstance(position + 1,_personID);

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_person_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_person_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_person_section3).toUpperCase(l);
            }
            return null;
        }

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PersonalFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String PERSONID = "personID";
        private static  int personid;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PersonalFragment newInstance(int sectionNumber,  int personID) {
            PersonalFragment fragment = new PersonalFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(PERSONID,personID);
            fragment.setArguments(args);
            personid=personID;
            return fragment;
        }
        public PersonalFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_person_deatil, container, false);
            //int personID=savedInstanceState.getInt(PERSONID);
            //Toast.makeText(container.getContext(),"length " + personID,Toast.LENGTH_LONG).show();
            SQLiteHandler dbHelper= new SQLiteHandler(getActivity());
            Person person= dbHelper.getPersonalDetails(personid);
            ((TextView)rootView.findViewById(R.id.tname_db)).setText(person.get_name());
            ((TextView)rootView.findViewById(R.id.taddress_db)).setText(person.get_address());
            ((TextView)rootView.findViewById(R.id.temail_db)).setText(person.get_email());
            ((TextView)rootView.findViewById(R.id.tmobile_db)).setText(person.get_mobile());
            ((TextView)rootView.findViewById(R.id.toccuption_db)).setText(person.get_occupation());
            ((TextView)rootView.findViewById(R.id.tocclocation_db)).setText(person.get_occulocation());

            return rootView;

        }


    }
    public static class FamilyFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String PERSONID = "personID";
        private static  int personid;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FamilyFragment newInstance(int sectionNumber,  int personID) {
            FamilyFragment fragment = new FamilyFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(PERSONID,personID);
            fragment.setArguments(args);
            personid=personID;
            return fragment;
        }
        public FamilyFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_family_detail, container, false);
            //int personID=savedInstanceState.getInt(PERSONID);
            //Toast.makeText(container.getContext(),"length " + personID,Toast.LENGTH_LONG).show();
            SQLiteHandler dbHelper= new SQLiteHandler(getActivity());
            Person person= dbHelper.getFamilyDetails(personid);
            ((TextView)rootView.findViewById(R.id.below5_db)).setText(Integer.toString(person.get_membersbelow5()));
            ((TextView)rootView.findViewById(R.id.between5218_db)).setText(Integer.toString(person.get_membersbelow5218()));
            ((TextView)rootView.findViewById(R.id.above60_db)).setText(Integer.toString(person.get_membersabove60()));
            ((TextView)rootView.findViewById(R.id.cntfemale_db)).setText(Integer.toString(person.get_femalecount()));
            ((TextView)rootView.findViewById(R.id.cntmale_db)).setText(Integer.toString(person.get_malecount()));
           // ((TextView)rootView.findViewById(R.id.tocclocation_db)).setText(person.get_occulocation());

            return rootView;

        }


    }
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_deatil);
        final ActionBar actionBar = getSupportActionBar();
        int personID= getIntent().getIntExtra("id", 1);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.set_personID(personID);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);

                // Log.d("test    adaptor", Integer.toString(position));

            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

            // actionBar.setTitle(mSectionsPagerAdapter.getPageTitle(i));
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }

}
