package es.developer.achambi.bproject.products;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import es.developer.achambi.bproject.add.Product;
import es.developer.achambi.coreframework.threading.Error;
import es.developer.achambi.coreframework.ui.Presenter;

public class CreateProductPresenter extends Presenter implements ChildEventListener {
    public interface CreateProductListener {
        void onCreateProductSuccess();
        void onCreateProductError( Error error );
    }
    private static final String PRODUCTS_PATH = "/products";
    private CreateProductListener listener;
    private DatabaseReference addProductReference;

    public CreateProductPresenter( CreateProductListener listener ) {
        this.listener = listener;
    }

    public void saveProduct(String productName, String productType) {
        addProductReference = FirebaseDatabase.getInstance()
                .getReference( PRODUCTS_PATH );
        addProductReference.addChildEventListener(this);
        Date timestamp = new Date();
        Product product = new Product(timestamp.getTime());
        product.setProductType(productType.toLowerCase());
        product.setProductName(productName.toLowerCase());
        addProductReference.push().setValue(product);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        addProductReference.removeEventListener(this);
        listener.onCreateProductSuccess();
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        listener.onCreateProductError(new Error(databaseError.getMessage()));
    }
}
