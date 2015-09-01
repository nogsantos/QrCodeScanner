package nogsantos.ufg.br.qrscanner.Model;

import java.io.Serializable;

/**
 * Created by nogsantos on 8/23/14.
 */
public class ScannerModel implements Serializable {

    private static final long serialVersionUid = 1L;
    private long   _id;
    private long   date;
    private String text;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    /*
     * Will be used by the ArrayAdapter in the ListView
     */
    @Override
    public String toString() {
        return text;
    }
}
