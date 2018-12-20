package es.developer.achambi.bproject.needlist;

import android.os.Bundle;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseActivity;
import es.developer.achambi.coreframework.ui.BaseFragment;

public class NeedListActivity extends BaseActivity {

    @Override
    public int getScreenTitle() {
        return R.string.app_name;
    }

    @Override
    public BaseFragment getFragment(Bundle args) {
        return new NeedListFragment();
    }
}
