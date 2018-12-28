package es.developer.achambi.bproject.needlist;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.presentation.SearchListData;

public class ListItemPresentation implements SearchListData {
    public final int id;
    public final String productName;
    public final String productType;
    public final String productQuantity;
    public final String recommendedPlace;
    public final String estimatedPrice;

    public ListItemPresentation(int id,
                                String productName, String productType, String productQuantity,
                                String recommendedPlace, String estimatedPrice) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.recommendedPlace = recommendedPlace;
        this.estimatedPrice = estimatedPrice;
    }

    @Override
    public int getViewType() {
        return R.id.list_item_view_id;
    }

    @Override
    public int getId() {
        return id;
    }
}
