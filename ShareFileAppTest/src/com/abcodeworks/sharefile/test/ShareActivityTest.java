package com.abcodeworks.sharefile.test;

import android.net.Uri;

import com.abcodeworks.sharefileapp.ShareActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

public class ShareActivityTest extends ActivityUnitTestCase<ShareActivity> {

	public ShareActivityTest() {
		super(ShareActivity.class);
	}
	
	ShareActivity shareActivity;

    /*protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(getInstrumentation().getTargetContext(), ShareActivity.class), null, null);
        shareActivity = (ShareActivity)getActivity();
    }*/
    
    @SmallTest
    public void testSendViewIntent() {
    	
    	String uri = "file://dummypath/dummyfile.txt";
    	String type = "text/plain";
    	
    	Intent viewIntent = new Intent();
    	viewIntent.setAction(Intent.ACTION_VIEW);

    	viewIntent.setDataAndType(Uri.parse(uri), type);

    	startActivity(viewIntent, null, null);
    	
    	Intent shareIntent = getStartedActivityIntent();
    	assertEquals(Intent.ACTION_SEND, shareIntent.getAction());
    	String test = shareIntent.getExtras().get(Intent.EXTRA_STREAM).toString();
    	assertEquals(uri, shareIntent.getExtras().get(Intent.EXTRA_STREAM).toString());
    	assertNull(shareIntent.getData());
        assertEquals(type, shareIntent.getType());
    }
}
