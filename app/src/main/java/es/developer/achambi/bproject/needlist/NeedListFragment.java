package es.developer.achambi.bproject.needlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseSearchAdapter;
import es.developer.achambi.coreframework.ui.BaseSearchListFragment;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;
import es.developer.achambi.coreframework.utils.TouchHelper;

public class NeedListFragment extends BaseSearchListFragment {
    private NeedListAdapter adapter;
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
        ArrayList<ListItemPresentation> items = new ArrayList<>();
        for( int i = 0; i < 2; i++ ) {
            items.add( new ListItemPresentation(
                    i,"Leche","Vive Soy soja", "x2",
                    "Mercadona", "2.50$"
            ) );
        }
        for( int i = 4; i < 40; i++ ) {
            items.add( new ListItemPresentation(
                    i,"Leche","Vive Soy soja", "x2",
                    null, null
            ) );
        }
        adapter.setData( items );
        presentAdapterData();
    }

    @Override
    protected TouchHelper provideItemTouchHelper( BaseSearchAdapter adapter ) {
        return new ItemListTouchHelper( adapter );
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
}
