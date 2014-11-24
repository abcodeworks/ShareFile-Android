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

import android.content.ActivityNotFoundException;
import android.content.Intent;
//import android.util.Log;
import android.widget.Toast;

public class ShareActivity extends IntentHandlerActivity {
	@Override
	protected void handler(Intent intent) {
	    if (Intent.ACTION_VIEW.equals(intent.getAction())) {
	    	//Log.d("DownloadSharer", "Sending intent...");

	    	Intent sendIntent = ShareFileHelper.createShareIntent(intent);

	    	try {
	    		startActivity(sendIntent);
	    	} catch(ActivityNotFoundException e) {
	    		Toast.makeText(this, getString(R.string.msg_no_app_for_share), Toast.LENGTH_LONG).show();
	    	}
	    }
	    
	    finish();
	}
}
