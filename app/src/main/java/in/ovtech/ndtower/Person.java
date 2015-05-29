package in.ovtech.ndtower;

/**
 * Created by pranay on 24/4/15.
 */
public class Person {
    private String _name;
    private String _address;
    private String _email;
    private String _mobile;
    private String _occupation;
    private String _occulocation;

    private String _flatname;
    private int    _ownerid;
    private int _membersbelow5;
    private int _membersbelow5218;
    private int _membersabove60;

    public int get_malecount() {
        return _malecount;
    }

    public void set_malecount(int _malecount) {
        this._malecount = _malecount;
    }

    private int _malecount;

    public int get_femalecount() {
        return _femalecount;
    }

    public void set_femalecount(int _femalecount) {
        this._femalecount = _femalecount;
    }

    private int  _femalecount;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private int  _id;

    public Person() {
    }


    public String get_flatname() {
        return _flatname;
    }

    public void set_flatname(String _flatname) {
        this._flatname = _flatname;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_mobile() {
        return _mobile;
    }

    public void set_mobile(String _mobile) {
        this._mobile = _mobile;
    }

    public String get_occupation() {
        return _occupation;
    }

    public void set_occupation(String _occupation) {
        this._occupation = _occupation;
    }

    public String get_occulocation() {
        return _occulocation;
    }

    public void set_occulocation(String _occulocation) {
        this._occulocation = _occulocation;
    }

    public int get_ownerid() {
        return _ownerid;
    }

    public void set_ownerid(int _ownerid) {
        this._ownerid = _ownerid;
    }

    public int get_membersbelow5() {
        return _membersbelow5;
    }

    public void set_membersbelow5(int _membersbelow5) {
        this._membersbelow5 = _membersbelow5;
    }

    public int get_membersbelow5218() {
        return _membersbelow5218;
    }

    public void set_membersbelow5218(int _membersbelow5218) {
        this._membersbelow5218 = _membersbelow5218;
    }

    public int get_membersabove60() {
        return _membersabove60;
    }

    public void set_membersabove60(int _membersabove60) {
        this._membersabove60 = _membersabove60;
    }
}
