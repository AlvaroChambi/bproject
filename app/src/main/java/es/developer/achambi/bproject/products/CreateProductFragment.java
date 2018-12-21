package es.developer.achambi.bproject.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.navigation.NavigationFragment;

public class CreateProductFragment extends NavigationFragment {
    public static CreateProductFragment newInstance() {
        return new CreateProductFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.base_request_fragment_layout;
    }

    @Override
    public void onViewSetup(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getTitleResource() {
        return R.string.create_product_title;
    }
}
