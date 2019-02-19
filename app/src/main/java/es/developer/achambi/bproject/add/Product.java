package es.developer.achambi.bproject.add;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private long id;
    private String productName;
    private String productType;

    public Product() {

    }

    public Product(long id) {
        this.id = id;
    }

    protected Product(Parcel in) {
        id = in.readLong();
        productName = in.readString();
        productType = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(productName);
        parcel.writeString(productType);
    }
}
