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

import android.content.Intent;

public class ShareFileHelper {
	static public Intent createShareIntent(Intent viewIntent)
	{
		Intent sendIntent = new Intent();
    	sendIntent.setAction(Intent.ACTION_SEND);
    	sendIntent.putExtra(Intent.EXTRA_STREAM, viewIntent.getData());
    	
    	String type = viewIntent.getType();
    	if(type == null) {
    		sendIntent.setType("application/octet-stream");
    	} else {
    		sendIntent.setType(type);
    	}
    	
    	/* Should we set additional flags, e.g. Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP or
    	 * Intent.FLAG_RECEIVER_FOREGROUND?  Until I know otherwise I will not add any
    	 * additional flags.
    	 */
    	sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    	
    	return sendIntent;
	}
}
