package es.developer.achambi.bproject.needlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.developer.achambi.coreframework.ui.BaseSearchListFragment;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;
import es.developer.achambi.coreframework.ui.presentation.SearchListData;

public class NeedListFragment extends BaseSearchListFragment {

    @Override
    public SearchAdapterDecorator provideAdapter() {
        return new SearchAdapterDecorator() {
            @Override
            public int getLayoutResource() {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder createViewHolder(View rootView) {
                return null;
            }

            @Override
            public void bindViewHolder(RecyclerView.ViewHolder holder, SearchListData item) {

            }

            @Override
            public int getAdapterViewType() {
                return 0;
            }
        };
    }
}
