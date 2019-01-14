package es.developer.achambi.bproject.add;

import java.util.ArrayList;

import es.developer.achambi.coreframework.ui.Presenter;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;

public class AddToListPresenter extends Presenter
        implements SearchAdapterDecorator.OnViewClickedListener<AddItemPresentation,AddToListFragment.ViewHolder>  {
    private ArrayList<Product> products;
    private AddToListFragment screen;

    public AddToListPresenter( AddToListFragment screen ) {
        products = new ArrayList<>();
        this.screen = screen;
    }

    public ArrayList<Product> getProducts() {
        if( products.isEmpty() ) {
            for( int i = 0; i < 10; i++ ) {
                Product product = new Product(i);
                product.setProductName("Arroz");
                product.setProductType("Grano largo");
                products.add(product);
            }
        }
        return products;
    }

    private Product findProduct(AddItemPresentation item) {
        for( Product product: products ) {
            if( item.getId() == product.getId() ) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void onViewClicked(AddItemPresentation item, AddToListFragment.ViewHolder viewHolder) {
        ItemCountView countView = viewHolder.binding.itemCountView;
        Product product = findProduct(item);
        product.setCount( countView.getCount() );
        screen.returnSelectedProduct( product );
    }
}
