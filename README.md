Share File
==========

Summary
-------
An Android app with the following purpose:
when opening a file for viewing, gives the option to share it instead.
I originally created this app for my own personal use.  It was also
a way for me to learn how to program on Android.
Unfortunately it still has glitches
and due to the Android architecture, I think it always will.

Websites
--------
Android Marketplace: http://play.google.com/store/apps/details?id=com.abcodeworks.sharefileapp<br/>
Source Code: https://github.com/abcodeworks/ShareFile-Android/<br/>

Building
--------
The root folder is a workspace which contains two projects:
one which contains the main Android app, and the other
which contains the unit tester.  The projects were created using
Eclipse ADT.  They should be built and tested using the Eclipse ADT.
The root folder structure is not standard (usually the root is a project,
not a workspace) but it seemed best given that we need two projects.

Testing
-------
I wrote limited automated unit test cases in the
ShareFileAppTest project.  This testing should be improved.
These tests should be manually performed:
- Change the settings.  Make sure the activities are enabled/disabled
  as appropriate.
- Make sure the links on the settings page work.
- Make sure the activities receive files as expected (i.e. make sure
  the intent filters work as expected).
- Make sure the activities generate and send the share/view intents
  appropriately.
  
Generate APK (Deployment)
-------------------------
Use the File | Export... option and choose "Export Android Application".

Notes
-----
- Per the Android guidelines, there should be no debug logging.
  Since this is a small project, I chose to just comment them out.

License
-------
This project is released under GPLv3 (this is driven by the fact
that it uses icons released under GPLv3).
The license can be found in the root folder.
The icon was built using icons available on Wikimedia Commons.
See the icon folder for details and licenses.

Author
------
Andre Beckus
 
