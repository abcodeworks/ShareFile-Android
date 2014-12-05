package com.abcodeworks.sharefileapp.test;

import com.abcodeworks.sharefileapp.R;
import com.abcodeworks.sharefileapp.SettingsActivity;
import com.abcodeworks.sharefileapp.ShareActivity;
import com.abcodeworks.sharefileapp.ShareAndViewActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

public class SettingsActivityTest extends ActivityInstrumentationTestCase2<SettingsActivity> {

	public SettingsActivityTest() {
		super(SettingsActivity.class);
	}

	SettingsActivity settingsActivity = null;
	CheckBoxPreference enableSharePref = null,
			           enableShareAndViewPref = null;
	
	@SuppressWarnings("deprecation")
	Preference getPref(int key) {
		String prefkey = settingsActivity.getResources().getString(key);
    	return settingsActivity.findPreference(prefkey);
	}
	
	protected void setUp() throws Exception {
        super.setUp();

        this.getInstrumentation().getTargetContext();
        setActivityInitialTouchMode(true);
        settingsActivity = getActivity();
        
        enableSharePref = (CheckBoxPreference)getPref(R.string.pref_enable_share);
        enableShareAndViewPref = (CheckBoxPreference)getPref(R.string.pref_enable_share_and_view);
    }
	
	boolean isActivityEnabled(Class<? extends Activity> activityClass)
	{
		ComponentName component = new ComponentName(settingsActivity, activityClass);
		int flag = settingsActivity.getPackageManager().getComponentEnabledSetting(component);
    	boolean activityEnabled = flag == PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    	return activityEnabled;
	}
	
	void changeCheckboxAndVerify(CheckBoxPreference pref, Class<? extends Activity> activityClass, boolean checked) {
		pref.setChecked(checked);
		
		// Setting the checkbox does not call the change listener.
		// This is ugly but works.  The better alternative would be to simulate
		// an actual click, but I do not know how to get the widget for the preference...
		settingsActivity.onPreferenceChange(pref, Boolean.valueOf(checked));
    	
		assertEquals(checked, pref.isChecked());
    	assertEquals(checked, isActivityEnabled(activityClass));
	}
    
    @UiThreadTest
    public void testEnableSharePref() {
    	CheckBoxPreference pref = enableSharePref;
    	changeCheckboxAndVerify(pref, ShareActivity.class, true);
    	changeCheckboxAndVerify(pref, ShareActivity.class, false);
    	changeCheckboxAndVerify(pref, ShareActivity.class, true);
    }
    
    @UiThreadTest
    public void testEnableShareAndViewPref() {
    	CheckBoxPreference pref = enableShareAndViewPref;
    	changeCheckboxAndVerify(pref, ShareAndViewActivity.class, true);
    	changeCheckboxAndVerify(pref, ShareAndViewActivity.class, false);
    	changeCheckboxAndVerify(pref, ShareAndViewActivity.class, true);
    }
}
