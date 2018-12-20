package es.developer.achambi.bproject.profile;

import android.content.Intent;

import es.developer.achambi.bproject.login.LoginActivity;
import es.developer.achambi.coreframework.ui.profile.BaseProfileFragment;

public class ProfileFragment extends BaseProfileFragment {
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onUserLoggedOut() {
        startActivity( new Intent(getActivity(), LoginActivity.class) );
        getActivity().finish();
    }
}
