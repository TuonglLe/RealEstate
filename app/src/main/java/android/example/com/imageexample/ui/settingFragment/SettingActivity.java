package android.example.com.imageexample.ui.settingFragment;


import android.content.SharedPreferences;
import android.example.com.imageexample.Utils.ContextUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.imageexample.R;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Setting");
        ContextUtils.setupToolbar(this, toolbar);
    }

    public static class PrefFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener{
        private SharedPreferences sharedPreferences;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preference);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            sharedPreferences = preferenceScreen.getSharedPreferences();

            for(int i = 0; i < preferenceScreen.getPreferenceCount(); i++){
                Preference preference = preferenceScreen.getPreference(i);
                setSumary(sharedPreferences, preference);
            }

            findPreference(getString(R.string.pref_sign_out_key)).setOnPreferenceClickListener(this);
        }

        private void setSumary(SharedPreferences sharedPreferences, Preference preference){
            if(preference instanceof CheckBoxPreference){
                return;
            }
            String newValue = sharedPreferences.getString(preference.getKey(), "");
            if(preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(newValue);
                preference.setSummary(
                        listPreference.getEntries()[index]
                );
            }else{
                preference.setSummary(newValue);
            }

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            setSumary(sharedPreferences, findPreference(key));
        }

        @Override
        public void onResume() {
            super.onResume();
            sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String prefKey = preference.getKey();
            if(prefKey.equals(getString(R.string.pref_sign_out_key))){
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Signed out", Toast.LENGTH_SHORT).show();
            }
            return true;
        }


    }

}
