package es.developer.achambi.bproject.add;

import java.util.ArrayList;

import es.developer.achambi.coreframework.ui.presentation.SearchListData;

public class ItemPresentation implements SearchListData {
    public final long id;
    public final String productName;
    public final String productType;

    public ItemPresentation(long id, String productName, String productType) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
    }

    public static class Builder {
        public static ItemPresentation build(long id, String name, String type) {
            return new ItemPresentation(
                    id,
                    formatText( name ),
                    formatText( type ) );
        }
        public static ArrayList<ItemPresentation> build(ArrayList<Product> products) {
            ArrayList<ItemPresentation> presentations = new ArrayList<>();
            for (Product product : products) {
                presentations.add( build( product.getId(),
                        product.getProductName(),
                        product.getProductType() ) );
            }
            return presentations;
        }
    }

    private static String formatText( String text ) {
        String formatted = "";
        if( text != null && !text.isEmpty() ) {
            formatted = text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
        }
        return formatted;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public long getId() {
        return id;
    }
}
