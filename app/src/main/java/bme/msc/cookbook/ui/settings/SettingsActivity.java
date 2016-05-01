package bme.msc.cookbook.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import bme.msc.cookbook.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
