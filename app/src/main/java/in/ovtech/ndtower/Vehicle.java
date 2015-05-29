package in.ovtech.ndtower;

/**
 * Created by pranay on 25/4/15.
 */
public class Vehicle {

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_flatno() {
        return _flatno;
    }

    public void set_flatno(String _flatno) {
        this._flatno = _flatno;
    }

    public String get_personname() {
        return _personname;
    }

    public void set_personname(String _personname) {
        this._personname = _personname;
    }

    public int get_flatid() {
        return _flatid;
    }

    public void set_flatid(int _flatid) {
        this._flatid = _flatid;
    }

    public int get_personid() {
        return _personid;
    }

    public void set_personid(int _personid) {
        this._personid = _personid;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_vechilenumber() {
        return _vechilenumber;
    }

    public void set_vechilenumber(String _vechilenumber) {
        this._vechilenumber = _vechilenumber;
    }

    private int _id;
    private String _flatno;
    private String _personname;
    private int _flatid;
    private int _personid;
    private String _type;
    private String _vechilenumber;
}
