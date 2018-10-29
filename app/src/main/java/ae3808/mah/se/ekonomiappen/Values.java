package ae3808.mah.se.ekonomiappen;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by Joakim on 10/24/2015.
 */
public class Values implements Parcelable {
    private String id;
    private String type;
    private String amount;
    private String date;
    private String title;

    public Values(String id, String type, String date, String title, String amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {return title; }

    public void setTitle(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeString(amount);
    }

    public static final Creator<Values> CREATOR = new Creator<Values>() {
        public Values createFromParcel(Parcel source) {
            return new Values(source.readString(), source.readString(), source.readString(),
                    source.readString(), source.readString());
        }

        public Values[] newArray(int size) {
            return new Values[size];
        }
    };
}