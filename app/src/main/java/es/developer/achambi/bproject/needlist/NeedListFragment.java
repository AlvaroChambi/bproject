package es.developer.achambi.bproject.needlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.developer.achambi.bproject.R;
import es.developer.achambi.bproject.add.AddToListActivity;
import es.developer.achambi.coreframework.ui.BaseSearchAdapter;
import es.developer.achambi.coreframework.ui.BaseSearchListFragment;
import es.developer.achambi.coreframework.ui.Presenter;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;
import es.developer.achambi.coreframework.utils.SwipeTouchHelperCallback;

public class NeedListFragment extends BaseSearchListFragment
        implements NeedListPresenter.OnDataRetrievedListener {
    public static String ADD_PRODUCT_DATA_KEY = "ADD_PRODUCT_DATA_KEY";
    private static int ADD_PRODUCT_RESULT_CODE = 101;
    private NeedListAdapter adapter;
    private ArrayList<ListItemPresentation> items;
    private NeedListPresenter presenter;

    public static NeedListFragment newInstance() {
        return new NeedListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.need_list_title);
    }

    @Override
    public Presenter setupPresenter() {
        if( presenter == null ) {
            presenter = new NeedListPresenter(this);
        }
        return presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    @Override
    public void onViewSetup(View view, @Nullable Bundle savedInstanceState) {
        super.onViewSetup(view, savedInstanceState);
        view.findViewById(R.id.base_search_floating_button).setVisibility(View.VISIBLE);
        view.findViewById(R.id.base_search_floating_button).setOnClickListener( this );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this.getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration( dividerItemDecoration );
        presenter.fetchProductsList();
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
    protected SwipeTouchHelperCallback provideItemTouchHelper(final BaseSearchAdapter adapter ) {
        return new SwipeTouchHelperCallback( adapter ) {
            @Override
            protected int getViewHolderForegroundResource() {
                return R.id.list_item_foreground;
            }

            @Override
            protected void onItemSwiped(RecyclerView.ViewHolder viewHolder) {
                presenter.deleteEntry(items.get(viewHolder.getAdapterPosition()));
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

    @Override
    public void onSuccess(ArrayList<ListProduct> products) {
        items = ListItemPresentation.Builder.build(products);
        adapter.setData( items );
        presentAdapterData();
    }

    @Override
    public void onError(Error error) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == ADD_PRODUCT_RESULT_CODE && resultCode == Activity.RESULT_OK  ) {
            ListProduct product = data.getParcelableExtra(ADD_PRODUCT_DATA_KEY);
            items.add( ListItemPresentation.Builder.build( product ) );
            adapter.setData( items );
            presentAdapterData();
        }
    }
}
