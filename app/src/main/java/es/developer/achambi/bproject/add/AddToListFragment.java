package es.developer.achambi.bproject.add;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.developer.achambi.bproject.R;
import es.developer.achambi.bproject.databinding.ItemToAddLayoutBinding;
import es.developer.achambi.bproject.needlist.NeedListFragment;
import es.developer.achambi.coreframework.ui.BaseSearchListFragment;
import es.developer.achambi.coreframework.ui.Presenter;
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator;

public class AddToListFragment extends BaseSearchListFragment {
    private AddListAdapter adapter;
    private AddToListPresenter presenter;

    public static AddToListFragment newInstance() {
        return new AddToListFragment();
    }

    @Override
    public SearchAdapterDecorator provideAdapter() {
        if( adapter == null ) {
            adapter = new AddListAdapter();
        }
        return adapter;
    }

    @Override
    public Presenter setupPresenter() {
        if( presenter == null ) {
            presenter = new AddToListPresenter(this);
        }
        return presenter;
    }

    public void returnSelectedProduct(Product product) {
        Intent dataIntent = getActivity().getIntent();
        dataIntent.putExtra(NeedListFragment.ADD_PRODUCT_DATA_KEY, product);
        getActivity().setResult(Activity.RESULT_OK, dataIntent);
        getActivity().finish();

    }

    @Override
    public void onViewSetup(View view, @Nullable Bundle savedInstanceState) {
        super.onViewSetup(view, savedInstanceState);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this.getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration( dividerItemDecoration );
        adapter.setData( PresentationBuilder.build( presenter.getProducts() ) );
        adapter.setViewClickedListener( presenter );
        presentAdapterData();
    }

    @Override
    protected void overrideRecyclerViewMargins(ViewGroup.MarginLayoutParams marginParams) {
        marginParams.setMargins(0, 0, 0, 0);
    }

    static class PresentationBuilder {
        static ArrayList<AddItemPresentation> build(ArrayList<Product> products) {
                ArrayList<AddItemPresentation> presentations = new ArrayList<>();
                for (Product product : products) {
                    presentations.add( new AddItemPresentation(
                            product.getId(),
                            product.getProductName(),
                            product.getProductType()
                    ) );
                }
                return presentations;
        }
    }

    class AddListAdapter extends SearchAdapterDecorator<AddItemPresentation, ViewHolder> {

        @Override
        public int getLayoutResource() {
            return R.layout.item_to_add_layout;
        }

        @Override
        public ViewHolder createViewHolder(View rootView) {
            ItemToAddLayoutBinding binding = DataBindingUtil.bind(rootView);
            return new ViewHolder( binding );
        }

        @Override
        public void bindViewHolder(ViewHolder holder, AddItemPresentation item) {
            holder.binding.setItem( item );
        }

        @Override
        protected View overrideClickableView(RecyclerView.ViewHolder viewHolder) {
            return viewHolder.itemView.findViewById(R.id.add_item_send_to_list_image);
        }

        @Override
        public int getAdapterViewType() {
            return 0;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ItemToAddLayoutBinding binding;
        public ViewHolder(ItemToAddLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
