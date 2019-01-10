package es.developer.achambi.bproject.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseActivity;
import es.developer.achambi.coreframework.ui.BaseFragment;

public class AddToListActivity extends BaseActivity {
    public static Intent getStartIntent( Context context ) {
        return new Intent( context, AddToListActivity.class );
    }
    @Override
    public int getScreenTitle() {
        return R.string.add_to_list_activity_title;
    }

    @Override
    public BaseFragment getFragment(Bundle args) {
        return AddToListFragment.newInstance();
    }
}
