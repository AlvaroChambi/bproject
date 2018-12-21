package es.developer.achambi.bproject;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import es.developer.achambi.bproject.needlist.NeedListFragment;
import es.developer.achambi.bproject.products.CreateProductFragment;
import es.developer.achambi.bproject.profile.ProfileFragment;
import es.developer.achambi.coreframework.ui.navigation.BaseNavigationActivity;

public class NavigationActivity extends BaseNavigationActivity {
    @Override
    public int provideMenuResource() {
        return R.menu.bottom_navigation_menu;
    }

    @Override
    public Fragment provideEntryFragment() {
        return NeedListFragment.newInstance();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_menu_list:
                replaceFragment( NeedListFragment.newInstance(), null );
                break;
            case R.id.navigation_menu_create:
                replaceFragment( CreateProductFragment.newInstance(), null );
                break;
            case R.id.navigation_menu_profile:
                replaceFragment( ProfileFragment.newInstance(), null );
                break;
        }
        return true;
    }
}
