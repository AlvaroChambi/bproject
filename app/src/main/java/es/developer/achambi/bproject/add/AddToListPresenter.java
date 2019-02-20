package es.developer.achambi.bproject.add;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.developer.achambi.bproject.needlist.ListProduct;
import es.developer.achambi.coreframework.ui.Presenter;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;

public class AddToListPresenter extends Presenter
        implements SearchAdapterDecorator.OnViewClickedListener<ItemPresentation,
        AddToListFragment.ViewHolder>,
        ChildEventListener,
        ValueEventListener{
    private static final String PRODUCTS_PATH = "/products";
    private static final String LISTS_ROOT_PATH = "/groups/";
    private static final String GROUP_KEY = "group0";
    private ArrayList<Product> products;
    private AddToListFragment screen;

    private DatabaseReference getProductsReference;
    private DatabaseReference addProductReference;

    public AddToListPresenter( AddToListFragment screen ) {
        products = new ArrayList<>();
        this.screen = screen;
    }

    private void addItemToList( ListProduct product ) {
        addProductReference = FirebaseDatabase.getInstance()
                .getReference( LISTS_ROOT_PATH + GROUP_KEY);
        addProductReference.addChildEventListener(this);
        addProductReference.push().setValue(product);
    }

    public void queryProducts( String query ) {
        DatabaseReference queryReference = FirebaseDatabase.getInstance()
                .getReference( PRODUCTS_PATH);
        queryReference.orderByChild("productName")
                .startAt(query.toLowerCase()).endAt(query.toLowerCase()+"\uf8ff")
                .addListenerForSingleValueEvent( this );
    }

    public void getProducts() {
        getProductsReference = FirebaseDatabase.getInstance()
                .getReference(PRODUCTS_PATH);
        getProductsReference.addValueEventListener(this);
    }

    private Product findProduct(ItemPresentation item) {
        for( Product product: products ) {
            if( item.getId() == product.getId() ) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void onViewClicked(ItemPresentation item, AddToListFragment.ViewHolder viewHolder) {
        ItemCountView countView = viewHolder.binding.itemCountView;
        Product product = findProduct(item);
        ListProduct result = new ListProduct();
        result.setId( product.getId() );
        result.setType(product.getProductType());
        result.setName(product.getProductName());
        result.setCount( countView.getCount() );
        addItemToList( result );
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        getProductsReference.removeEventListener((ValueEventListener) this);
        products = new ArrayList<>();
        for(DataSnapshot data : dataSnapshot.getChildren()) {
            products.add( data.getValue(Product.class) );
        }
        screen.onDataReceived(products);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        addProductReference.removeEventListener((ChildEventListener) this);
        screen.returnSelectedProduct( dataSnapshot.getValue(ListProduct.class) );
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

    }
}
