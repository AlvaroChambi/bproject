package es.developer.achambi.bproject.needlist;

import java.util.ArrayList;

import es.developer.achambi.bproject.R;
import es.developer.achambi.bproject.add.ItemPresentation;
import es.developer.achambi.coreframework.ui.presentation.SearchListData;

public class ListItemPresentation implements SearchListData {
    public final ItemPresentation itemPresentation;
    public final String productQuantity;
    public final String recommendedPlace;
    public final String estimatedPrice;

    public ListItemPresentation(ItemPresentation itemPresentation, String productQuantity,
                                String recommendedPlace, String estimatedPrice) {
        this.itemPresentation = itemPresentation;
        this.productQuantity = productQuantity;
        this.recommendedPlace = recommendedPlace;
        this.estimatedPrice = estimatedPrice;
    }

    public static class Builder {
        public static ListItemPresentation build( ListProduct product ) {
            return new ListItemPresentation(
                    ItemPresentation.Builder.build(product.getId(), product.getName(),
                            product.getType() ),
                    "x" + String.valueOf( product.getCount() ),
                    null, null
            );
        }
        public static ArrayList<ListItemPresentation> build(ArrayList<ListProduct> products ) {
            ArrayList<ListItemPresentation> list = new ArrayList<>();
            for ( ListProduct product : products ) {
                list.add( build(product) );
            }
            return list;
        }
    }



    @Override
    public int getViewType() {
        return R.id.list_item_view_id;
    }

    @Override
    public int getId() {
        return itemPresentation.getId();
    }
}
