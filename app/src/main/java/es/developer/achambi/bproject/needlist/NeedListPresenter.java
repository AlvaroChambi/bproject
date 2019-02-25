package es.developer.achambi.bproject.needlist;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.developer.achambi.coreframework.ui.Presenter;

public class NeedListPresenter extends Presenter implements  ValueEventListener {
    public interface OnDataRetrievedListener {
        void onSuccess(ArrayList<ListProduct> products);
        void onError(Error error);
    }
    private static final String LISTS_ROOT_PATH = "groups/";
    private static final String GROUP_KEY = "group0";

    private DatabaseReference dbReference;
    private OnDataRetrievedListener listener;
    private DataSnapshot dataSnapshot;

    public NeedListPresenter(OnDataRetrievedListener listener) {
        this.listener = listener;
        dbReference = FirebaseDatabase.getInstance()
                .getReference( LISTS_ROOT_PATH + GROUP_KEY);
    }

    public void fetchProductsList() {
        dbReference.addValueEventListener( this );
    }

    public void deleteEntry(long id) {
        for( DataSnapshot item : dataSnapshot.getChildren() ) {
            ListProduct product = item.getValue(ListProduct.class);
            if( id == product.getId() ) {
                item.getRef().removeValue();
            }
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
        ArrayList<ListProduct> products = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            products.add( item.getValue(ListProduct.class) );
        }
        listener.onSuccess(products);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        listener.onError(new Error(databaseError.getMessage()));
    }
}
