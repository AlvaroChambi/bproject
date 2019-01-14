package es.developer.achambi.bproject.add;

import es.developer.achambi.coreframework.ui.presentation.SearchListData;

public class AddItemPresentation implements SearchListData {
    public final int id;
    public final String productName;
    public final String productType;

    public AddItemPresentation(int id, String productName, String productType) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public int getId() {
        return id;
    }
}
