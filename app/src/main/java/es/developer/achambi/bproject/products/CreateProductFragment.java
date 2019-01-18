package es.developer.achambi.bproject.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.threading.Error;
import es.developer.achambi.coreframework.ui.BaseRequestFragment;
import es.developer.achambi.coreframework.ui.Presenter;

public class CreateProductFragment extends BaseRequestFragment implements View.OnClickListener,
        CreateProductPresenter.CreateProductListener {
    private CreateProductPresenter presenter;
    private EditText productName;
    private EditText productType;
    private View saveButton;

    public static CreateProductFragment newInstance() {
        return new CreateProductFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.create_product_fragment_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.create_product_title);
    }

    @Override
    public Presenter setupPresenter() {
        if( presenter == null ) {
            presenter = new CreateProductPresenter(this);
        }
        return presenter;
    }

    @Override
    public void onViewSetup(View view, @Nullable Bundle savedInstanceState) {
        productName = view.findViewById(R.id.create_name_edit_text);
        saveButton = view.findViewById(R.id.create_save_button);
        saveButton.setOnClickListener(this);
        productName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(productName.getText().toString().equals( "" )) {
                    saveButton.setEnabled( false );
                } else {
                    saveButton.setEnabled( true );
                }
            }
        });
        saveButton.setEnabled( false );
        productType = view.findViewById(R.id.create_type_edit_text);

    }

    @Override
    public int getLoadingFrame() {
        return R.id.base_request_loading_frame;
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.create_save_button ) {
            startLoading();
            presenter.saveProduct( productName.getText().toString(),
                    productType.getText().toString() );
        }
    }

    @Override
    public void onCreateProductSuccess() {
        hideLoading();
        Snackbar snackbar = Snackbar.make( getView(),
                R.string.create_product_success_message, Snackbar.LENGTH_LONG );
        snackbar.show();
        productName.getText().clear();
        productType.getText().clear();
    }

    @Override
    public void onCreateProductError(Error error) {
        showError(error);
    }
}
