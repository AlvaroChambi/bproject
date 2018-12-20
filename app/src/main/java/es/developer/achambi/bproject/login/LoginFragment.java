package es.developer.achambi.bproject.login;

import android.content.Intent;

import es.developer.achambi.bproject.needlist.NeedListActivity;
import es.developer.achambi.coreframework.ui.login.BaseLoginFragment;

public class LoginFragment extends BaseLoginFragment {
    @Override
    public Intent getNextScreenIntent() {
        return new Intent( getActivity(), NeedListActivity.class );
    }
}
