package es.developer.achambi.bproject.login;

import android.os.Bundle;

import es.developer.achambi.bproject.R;
import es.developer.achambi.coreframework.ui.BaseActivity;
import es.developer.achambi.coreframework.ui.BaseFragment;

public class LoginActivity extends BaseActivity {
    @Override
    public int getScreenTitle() {
        return R.string.login_activity_title;
    }

    @Override
    public BaseFragment getFragment(Bundle args) {
        return new LoginFragment();
    }
}
