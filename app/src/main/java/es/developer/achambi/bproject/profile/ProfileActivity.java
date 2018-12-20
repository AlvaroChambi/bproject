package es.developer.achambi.bproject.profile;

import android.os.Bundle;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseActivity;
import es.developer.achambi.coreframework.ui.BaseFragment;

public class ProfileActivity extends BaseActivity {
    @Override
    public int getScreenTitle() {
        return R.string.profile_title;
    }

    @Override
    public BaseFragment getFragment(Bundle args) {
        return ProfileFragment.newInstance();
    }
}
