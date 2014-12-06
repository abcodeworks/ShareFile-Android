/**
 * Copyright 2014 by Andre Beckus
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.abcodeworks.sharefileapp;

import com.abcodeworks.sharefileapp.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

// Used this file as a guide:
// https://github.com/intrications/intent-intercept/blob/76b79537ede9cd5b476eb267f66c12035a319058/app/src/main/java/uk/co/ashtonbrsc/intentexplode/Settings.java

public class SettingsActivity extends PreferenceActivity
                implements Preference.OnPreferenceChangeListener
{
	protected Preference
	            enableSharePref = null, 
			    enableShareAndViewPref = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Is this really necessary?  I don't see this being done in examples.
		PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
	}
	
	protected void setActivityEnabled(Class<? extends Activity> activityClass, boolean enabled) {
        int flag = (enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
        ComponentName component = new ComponentName(this, activityClass);

        getPackageManager().setComponentEnabledSetting(component, flag,
                PackageManager.DONT_KILL_APP);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.pref_general);
		
		// Set the version on the settings page
		// Some of this code was copied from:
		// http://stackoverflow.com/questions/3637665/user-versionname-value-of-androidmanifest-xml-in-code/3637686#3637686
		try {
			Preference appVersionPref = findPreference(getString(R.string.pref_app_version));
		    String version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		    appVersionPref.setSummary(version);
		} catch (NameNotFoundException e) {
		    Log.e("ShareFileApp", e.getMessage());
		}
		
		enableSharePref = findPreference(getString(R.string.pref_enable_share));
		enableSharePref.setOnPreferenceChangeListener(this);
		
		enableShareAndViewPref = findPreference(getString(R.string.pref_enable_share_and_view));
		enableShareAndViewPref.setOnPreferenceChangeListener(this);
	}

	public boolean onPreferenceChange(Preference preference, Object newValue)
	{
		if(preference == enableSharePref){
			Boolean enable = (Boolean) newValue;
			setActivityEnabled(ShareActivity.class, enable);
		} else if(preference == enableShareAndViewPref){
			Boolean enable = (Boolean) newValue;
			setActivityEnabled(ShareAndViewActivity.class, enable);
		}

	    return true;
	}
}
