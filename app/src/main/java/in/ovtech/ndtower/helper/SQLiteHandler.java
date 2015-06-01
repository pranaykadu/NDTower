package in.ovtech.ndtower.helper;

/**
 * Created by pranay on 24/4/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import in.ovtech.ndtower.Flat;
import in.ovtech.ndtower.Person;
import in.ovtech.ndtower.Vehicle;


public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 16;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_LOGIN = "NDLOGIN";
    // Brands table name
    private static final String TABLE_FLAT = "NDFLAT";
    //customer table name
    private static final String TABLE_PERSON ="NDPERSON";
    //customer table name
    private static final String TABLE_VEHICLES ="NDVEHICLES";


    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_FLATNO ="flatno";
    private static final String KEY_FLATID ="flatid";
    private static final String KEY_PERSONID ="personid";
    private static final String KEY_TYPE ="type";
    private static final String KEY_NUMBER="number";
    //private static final String KEY_OWNERID="ownerid";
    //customer table columns
    private static final String KEY_FLOOR= "floor";
    private static final String KEY_WING= "wing";
    private  static  final String KEY_BUILDING ="building";
    private  static  final String KEY_ADDRESS ="address";
    private  static  final String KEY_OCCUPATION ="occupation";
    private  static  final String KEY_OCCLOCATION ="occlocation";
    private  static  final String KEY_OWNERID ="ownerid";
    private  static  final String KEY_MEMBERSBELOW5 ="membersbelow5";
    private  static  final String KEY_MEMBERS5218 ="members5218";
    private  static  final String KEY_MEMBERSABOVE60 ="membersabove60";
    private  static  final String KEY_MOBILE ="mobile";
    private  static  final String KEY_ISMEMBER ="ismember";
    private  static  final String KEY_POSSESSION ="possesion";
    private  static  final String KEY_STATUS ="status";
    private  static  final String KEY_MAPID ="mapid";
    private  static  final String KEY_FEMALE_COUNT ="female";
    private  static  final String KEY_MALE_COUNT ="male";



    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        String CREATE_VEHICLES_TABLE = "CREATE TABLE " + TABLE_VEHICLES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FLATID + " TEXT,"
                + KEY_PERSONID + " INTEGER ," + KEY_TYPE + " TEXT ," + KEY_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_VEHICLES_TABLE);

        String CREATE_FLAT_TABLE = "CREATE TABLE " + TABLE_FLAT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FLATNO  + " INTEGER,"
                + KEY_OWNERID + " INTEGER ," + KEY_TYPE + " TEXT ," + KEY_WING + " TEXT, "
                + KEY_POSSESSION + " TEXT, " + KEY_STATUS + " TEXT, "
                + KEY_FLOOR + " TEXT"  + ")";
        db.execSQL(CREATE_FLAT_TABLE);

        String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT ," + KEY_BUILDING + " TEXT ," + KEY_UID + " TEXT, "
                + KEY_MAPID + " INTEGER ,"
                + KEY_EMAIL + " TEXT ," + KEY_MOBILE + " TEXT ," + KEY_OCCLOCATION + " TEXT ,"
                + KEY_OCCUPATION + " TEXT ,"+ KEY_OWNERID + " INTEGER ,"+ KEY_MEMBERSBELOW5 + " INTEGER ,"
                + KEY_MEMBERS5218 + " INTEGER ,"+ KEY_MEMBERSABOVE60+ " INTEGER ,"+ KEY_MALE_COUNT+ " INTEGER ,"
                + KEY_FEMALE_COUNT+ " INTEGER ,"
                + KEY_ISMEMBER + " INTEGER "
                + ")";
        db.execSQL(CREATE_PERSON_TABLE);

        Log.d(TAG, "Database tables created");

        PersonNames personNames=new PersonNames();
        String[] allFats= personNames.updateAllFlatswithmapID;
        String[] allPersonDetail=personNames.allPersonsdetail;

        for(int i=0; i<allFats.length; i++)
            db.execSQL(allFats[i]);
        for(int i=0; i<allPersonDetail.length; i++)
            db.execSQL(allPersonDetail[i]);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
            Log.d("name",cursor.getString(1));
        }
        else{Log.d("name","didnt go inside");}
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }


    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
    /**
     * Storing brand details in database
     * */
    /**
     * Getting user login status return true if rows are there in table
     * */
 public ArrayList<Person> getAllPersonNames(){
     ArrayList<Person> persons = new ArrayList();
     SQLiteDatabase db = this.getReadableDatabase();

     String selectQuery = "SELECT distinct p.id,p.name,f." +KEY_WING + ",f."+KEY_FLATNO+" FROM " + TABLE_PERSON  + " as p inner join " + TABLE_FLAT
             +" as f on f." + KEY_OWNERID +" = p." +KEY_MAPID;
     Cursor cursor = db.rawQuery(selectQuery, null);

     if(cursor.getCount() > 0){

         while(cursor.moveToNext()){
             Person person=new Person();
             person.set_id(cursor.getInt(0));
             person.set_name(cursor.getString(1));
             person.set_flatname(cursor.getString(2) +" " +cursor.getString(3));
             persons.add(person);
             Log.d(TAG,cursor.getColumnName(0));

         }

     }
     else{Log.d(TAG,"Person names list didnt go inside");}
     cursor.close();
     db.close();
     // return user
     Log.d(TAG, "Fetching Person names from Sqlite: " );

     return persons;

 }


    public ArrayList<Flat> getAllFlatNames(){
        ArrayList<Flat> flats = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT f.id,f.wing,f.flatno, p.name FROM " + TABLE_FLAT + " as f inner join  "+  TABLE_PERSON +" as p on " +
                " p."+ KEY_MAPID + "= f." + KEY_OWNERID +" sorder by wing,flatno " ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() > 0){

            while(cursor.moveToNext()){
                Flat flat= new Flat();
                flat.set_id(cursor.getInt(0));
                flat.set_wing(cursor.getString(1));
                flat.set_flatno(Integer.toString(cursor.getInt(2)));
                flat.set_OwnerName(cursor.getString(3));
                flats.add(flat);
                Log.d("name",cursor.getColumnName(0));

            }

        }
        else{Log.d(TAG,"Flats list didnt go inside");}
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching all flats  from Sqlite: " );

        return flats;

    }
    public ArrayList<Vehicle> getAllVehicles(){
        ArrayList<Vehicle> vehicles = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT id, " + KEY_NUMBER + " FROM " + TABLE_VEHICLES ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() > 0){

            while(cursor.moveToNext()){
                Vehicle vehicle= new Vehicle();
                vehicle.set_id(cursor.getInt(0));
                vehicle.set_vechilenumber(cursor.getString(1));
               // vehicle.set_flatno(cursor.getString(1));
                vehicles.add(vehicle);
                Log.d("name",cursor.getColumnName(0));

            }

        }
        else{Log.d(TAG,"Flats list didnt go inside");}
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching all flats  from Sqlite: " );

        return vehicles;

    }
    public  Person getPersonalDetails(int personID)
    {
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT p.name,p.address,p.mobile,p.email,p.occupation,p.occlocation" +
              //  ",f.wing,f.flatno  FROM NDPERSON as p " +
               // "join NDFLAT as f " +
                // "where  p.id =f.ownerid and p.id='" + personID +"'";
                " FROM NDPERSON as p where p.id='" + personID + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            person.set_name(cursor.getString(0));
            person.set_address(cursor.getString(1));
            person.set_mobile(cursor.getString(2));
            person.set_email(cursor.getString(3));
            person.set_occupation(cursor.getString(4));
            person.set_occulocation(cursor.getString(5));
           // person.set_flatname(cursor.getString(6) + " " + cursor.getString(7));

            }
        cursor.close();
        return person;

    }
    public void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.get_name()); // Name
        values.put(KEY_ADDRESS, person.get_address()); // Email
        values.put(KEY_MOBILE, person.get_mobile()); // Email
        values.put(KEY_EMAIL, person.get_email());
        values.put(KEY_OCCUPATION, person.get_occupation()); // Created At
        values.put(KEY_OCCLOCATION,person.get_occulocation());


        // Inserting Row
        long id = db.insert(TABLE_PERSON, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New person added into sqlite: " + id);

    }


    public void addFlat(Flat flat){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FLATNO, flat.get_flatno());
        values.put(KEY_OWNERID, flat.get_ownerid());
        values.put(KEY_TYPE, flat.get_type());
        values.put(KEY_WING, flat.get_wing());
        values.put(KEY_FLOOR, flat.get_floor());
        long id = db.insert(TABLE_FLAT, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New flat added into sqlite: " + id);

    }
    public Flat getFlat(int flatid){

    Flat flat= new Flat();
    SQLiteDatabase db = this.getReadableDatabase();

    String selectQuery = "SELECT f.type,  " +
            " f.wing,f.flatno, f.floor,f.possesion ,f.status,  p.name  FROM NDFLAT as f " +
            " join NDPERSON as p on f.ownerid=p.mapid"
           + " where  f.id='" + flatid +"'";
    Cursor cursor = db.rawQuery(selectQuery, null);
    // Move to first row
    //cursor.moveToFirst();
    if(cursor.getCount() > 0) {
        cursor.moveToFirst();
        flat.set_type(cursor.getString(0));
        flat.set_wing(cursor.getString(1));
        flat.set_flatno(Integer.toString(cursor.getInt(2)));
        flat.set_floor(cursor.getString(3));
        flat.set_possession(cursor.getString(4));
        flat.set_status(cursor.getString(5));
        flat.set_OwnerName(cursor.getString(6));
        }
        cursor.close();
return flat;
    }
    public void addVehicle(Vehicle vehicle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER, vehicle.get_vechilenumber()); // Name
        values.put(KEY_FLATID, vehicle.get_flatid()); // Name
        values.put(KEY_PERSONID, vehicle.get_personid()); // Name
        values.put(KEY_TYPE, vehicle.get_type()); // Name

        // Inserting Row
        long id = db.insert(TABLE_VEHICLES, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New vehicle added into sqlite: " + id);

    }
    public Vehicle getVehicle(int vehicleID){
        Vehicle vehicle= new Vehicle();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT p.name,v.number,v.type, " +
                "f.wing,f.flatno  FROM NDPERSON as p join NDFLAT as f on  p.flatid =f.id and p.ownerid is null  " +
                "  and p.ismember is null  " +
                "  join NDVEHICLES as v on v.flatid=f.id and v.personid=p.id " +
                " where  v.id='" + vehicleID +"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            vehicle.set_personname(cursor.getString(0));
            vehicle.set_vechilenumber(cursor.getString(1));
            vehicle.set_type(cursor.getString(2));
            vehicle.set_flatno(cursor.getString(3) + " " + cursor.getString(4));


        } cursor.close();
   return vehicle;
    }
    public void executeQuery(String Query){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Query);
        db.close();
    }
    public Person getFlatOwnerDetails(int flatID){
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT p.name,p.address,p.mobile,p.email" +
                 ",f.wing,f.flatno  FROM NDPERSON as p " +
                "join NDFLAT as f on f.ownerid=p.mapid" +
                " and f.id='" + flatID +"'";
               // " FROM NDPERSON as p where p.id='" + personID + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            person.set_name(cursor.getString(0));
            person.set_address(cursor.getString(1));
            person.set_mobile(cursor.getString(2));
            person.set_email(cursor.getString(3));
            person.set_flatname(cursor.getString(4) + "  " +cursor.getString(5));

        }cursor.close();
        return person;

    }
    public Person getFlatTenantDetails(int flatID){
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT p.name,p.address,p.mobile,p.email" +
                ",f.wing,f.flatno  FROM NDPERSON as p " +
                "join NDFLAT as f on f.ownerid=p.ownerid" +
                " and f.id='" + flatID +"'";
        // " FROM NDPERSON as p where p.id='" + personID + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d(TAG + "cursor count",cursor.getCount() + "");
        // Move to first row
        //cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            person.set_name(cursor.getString(0));
            person.set_address(cursor.getString(1));
            person.set_mobile(cursor.getString(2));
            person.set_email(cursor.getString(3));
            person.set_flatname(cursor.getString(4) + "  " +cursor.getString(5));

        }
        cursor.close();
        return person;

    }
    public  Person getFamilyDetails(int personID)
    {
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + KEY_MEMBERSBELOW5 + "," + KEY_MEMBERS5218 + "  ,"
                + KEY_MEMBERSABOVE60+ ","  + KEY_MALE_COUNT + " ,"
            + KEY_FEMALE_COUNT +  " FROM NDPERSON as p where p.id='" + personID + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        //cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            person.set_name(cursor.getString(0));
            person.set_address(cursor.getString(1));
            person.set_mobile(cursor.getString(2));
            person.set_email(cursor.getString(3));
            person.set_occupation(cursor.getString(4));
            //person.set_occulocation(cursor.getString(5));
            // person.set_flatname(cursor.getString(6) + " " + cursor.getString(7));

        }
        cursor.close();
        return person;

    }

}