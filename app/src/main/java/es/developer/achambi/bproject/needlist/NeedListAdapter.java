package es.developer.achambi.bproject.needlist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.developer.achambi.bproject.R;
import es.developer.achambi.bproject.databinding.NeedListItemLayoutBinding;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;

public class NeedListAdapter extends SearchAdapterDecorator<ListItemPresentation,
        NeedListAdapter.ViewHolder> {

    @Override
    public int getLayoutResource() {
        return R.layout.need_list_item_layout;
    }

    @Override
    public NeedListAdapter.ViewHolder createViewHolder(View rootView) {
        NeedListItemLayoutBinding binding = DataBindingUtil.bind(rootView);
        return new ViewHolder( binding );
    }

    @Override
    public void bindViewHolder(NeedListAdapter.ViewHolder holder, ListItemPresentation item) {
        holder.binding.setItem( item );
    }

    @Override
    public int getAdapterViewType() {
        return R.id.list_item_view_id;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        NeedListItemLayoutBinding binding;
        ViewHolder( NeedListItemLayoutBinding binding ) {
            super( binding.getRoot() );
            this.binding = binding;
        }
    }
}
