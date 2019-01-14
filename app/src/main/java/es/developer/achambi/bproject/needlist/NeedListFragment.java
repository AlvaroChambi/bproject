package es.developer.achambi.bproject.needlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.developer.achambi.bproject.R;
import es.developer.achambi.bproject.add.AddToListActivity;
import es.developer.achambi.bproject.add.Product;
import es.developer.achambi.coreframework.ui.BaseSearchAdapter;
import es.developer.achambi.coreframework.ui.BaseSearchListFragment;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;
import es.developer.achambi.coreframework.utils.SwipeTouchHelperCallback;

public class NeedListFragment extends BaseSearchListFragment {
    public static String ADD_PRODUCT_DATA_KEY = "ADD_PRODUCT_DATA_KEY";
    private static int ADD_PRODUCT_RESULT_CODE = 101;
    private NeedListAdapter adapter;
    private ArrayList<ListItemPresentation> items;
    public static NeedListFragment newInstance() {
        return new NeedListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.need_list_title);
    }

    @Override
    public void onViewSetup(View view, @Nullable Bundle savedInstanceState) {
        super.onViewSetup(view, savedInstanceState);
        items = new ArrayList<>();
        for( int i = 0; i < 2; i++ ) {
            items.add( new ListItemPresentation(
                    i,"Leche","Vive Soy soja", "x2",
                    "Mercadona", "2.50"
            ) );
        }
        for( int i = 4; i < 40; i++ ) {
            items.add( new ListItemPresentation(
                    i,"Leche","Vive Soy soja", "x2",
                    null, null
            ) );
        }

        view.findViewById(R.id.base_search_floating_button).setVisibility(View.VISIBLE);
        view.findViewById(R.id.base_search_floating_button).setOnClickListener( this );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this.getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration( dividerItemDecoration );
        adapter.setData( items );
        presentAdapterData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if( v.getId() == R.id.base_search_floating_button ) {
            startActivityForResult( AddToListActivity.getStartIntent(getActivity()),
                    ADD_PRODUCT_RESULT_CODE );
        }
    }

    @Override
    protected SwipeTouchHelperCallback provideItemTouchHelper( BaseSearchAdapter adapter ) {
        return new SwipeTouchHelperCallback( adapter ) {
            @Override
            protected int getViewHolderForegroundResource() {
                return R.id.list_item_foreground;
            }
        };
    }

    @Override
    protected void overrideRecyclerViewMargins(ViewGroup.MarginLayoutParams marginParams) {
        marginParams.setMargins(0, 0, 0, 0);
    }

    @Override
    public SearchAdapterDecorator provideAdapter() {
        if( adapter == null ) {
            adapter = new NeedListAdapter();
        }
        return adapter;
    }

    static class PresentationBuilder {
        static ListItemPresentation build( Product product ) {
            return new ListItemPresentation(
                    product.getId(),
                    product.getProductName(),
                    product.getProductType(),
                    "x" + String.valueOf( product.getCount() ),
                    null, null
            );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == ADD_PRODUCT_RESULT_CODE && resultCode == Activity.RESULT_OK  ) {
            Product product = data.getParcelableExtra(ADD_PRODUCT_DATA_KEY);
            items.add( PresentationBuilder.build( product ) );
            adapter.setData( items );
            presentAdapterData();
        }
    }
}
