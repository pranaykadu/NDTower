package in.ovtech.ndtower;

/**
 * Created by pranay on 25/4/15.
 */
public class Flat {


    public int get_id() {
        return _id;
    }



    public String get_flatno() {
        return _flatno;
    }

    public void set_flatno(String _flatno) {
        this._flatno = _flatno;
    }

    public int get_ownerid() {
        return _ownerid;
    }

    public void set_ownerid(int _ownerid) {
        this._ownerid = _ownerid;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_wing() {
        return _wing;
    }

    public void set_wing(String _wing) {
        this._wing = _wing;
    }

    public String get_floor() {
        return _floor;
    }

    public void set_floor(String _floor) {
        this._floor = _floor;
    }

    public void set_id(int _id) {
        this._id = _id;
    }



    public String get_OwnerName() {
        return _OwnerName;
    }

    public void set_OwnerName(String _OwnerName) {
        this._OwnerName = _OwnerName;
    }

    private int _id;
    private String  _flatno;
    private int _ownerid;
    private String _OwnerName;
    private String _type;
    private String _wing;
    private String _floor;

    public String get_possession() {
        return _possession;
    }

    public void set_possession(String _possession) {
        this._possession = _possession;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    private String _possession;
    private String _status;


}
