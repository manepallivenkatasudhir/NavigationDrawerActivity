package com.android.navigationdraweractivity.interfaces;

import android.os.Bundle;

public interface HomePageInterface {
    void reloadFragment();
    void loadNonDrawerFragment(final String currentTag, final Bundle bundle, String title);
}
