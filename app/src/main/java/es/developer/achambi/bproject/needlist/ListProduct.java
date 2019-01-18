package es.developer.achambi.bproject.needlist;

import android.os.Parcel;
import android.os.Parcelable;

public class ListProduct implements Parcelable {
    private long id;
    private String name;
    private String type;
    private int count;

    public ListProduct() {

    }

    public ListProduct( long id ) {
        this.id = id;
    }

    protected ListProduct(Parcel in) {
        id = in.readLong();
        name = in.readString();
        type = in.readString();
        count = in.readInt();
    }

    public static final Creator<ListProduct> CREATOR = new Creator<ListProduct>() {
        @Override
        public ListProduct createFromParcel(Parcel in) {
            return new ListProduct(in);
        }

        @Override
        public ListProduct[] newArray(int size) {
            return new ListProduct[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeInt(count);
    }
}
