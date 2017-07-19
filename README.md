# MediaBrix Google DFP/AdMob Adapters - Android
## Please see "Testing / Release Settings" section for new guidelines on testing and deploying your integration.

MediaBrix has created a Google DFP/AdMob adapter that allows publishers, using Google DFP/AdMob as their central ad server, to mediate the MediaBrix network as another demand source.

## Prerequisites
* MediaBrix App ID and Zone Name
* MediaBrix SDK Integration ([download/clone here](https://github.com/mediabrix/mediabrix-android-sdk/tree/master/Android/SDK%20Files))
* Google DFP/AdMob SDK Integration
* [Google DFP](https://developers.google.com/mobile-ads-sdk/docs/dfp/android/custom-events)/[AdMob Mediation Setup](https://support.google.com/admob/answer/3083407?hl=en&ref_topic=3063091)

## Edit Custom Event
### Class Name
Enter the following for "Class Name", **com.your.package.MediaBrixInterstitial** when editing the the custom event.

### Mediation Parameters
When creating the custom event network for [Google DFP](https://developers.google.com/mobile-ads-sdk/docs/dfp/android/custom-events)/[AdMob Mediation Setup](https://support.google.com/admob/answer/3083407?hl=en&ref_topic=3063091) you will need to add the following to the "Parameter" feild in this format: 
```
{"zone": "provided_by_mediabrix", "appID": "provided_by_mediabrix"}
```

**Step 1:** Add the MediaBrix SDK to your project ([which can be found here](https://github.com/mediabrix/mediabrix-android-sdk/tree/master/Android/SDK%20Files))

**Step 2:** Download **MediaBrixInterstitial.java** from this repo.

**Step 3:** Copy mediabrix-sdk-FBless.jar and MediaBrixInterstitial.java into your project.
*  Android Studio: 
````
dependencies {
    compile 'com.android.support:support-v4:21.0.3'// This can be changed to
                                                   // whatever version you want  
    compile files('libs/mediabrix-sdk-FBless.jar') 
}
````
**Step 4:** Copy the following below into your project's AndroidManifest.xml:

``<activity
     android:name="com.mediabrix.android.service.AdViewActivity"
     android:configChanges="orientation|screenSize|keyboard"
     android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" >
</activity>``

``<service
      android:name="com.mediabrix.android.service.MediaBrixService" >
</service> ``

**Step 5:** In the activity that is requesting the interstitial ad add the following lines to your activity's ``onResume`` and ``onPause`` methods.

````@Override
    public void onPause() {
        super.onPause();
        MediabrixAPI.getInstance().onPause(getApplicationContext());
    }
````

````@Override
    public void onResume() {
        super.onResume();
        MediabrixAPI.setDebug(true); // This method prints out logs from the MediaBrix SDK.
                                    //  To disable logs, set the method to false.
        MediabrixAPI.getInstance().onResume(getApplicationContext());
    }
````

### Testing / Release Settings

To facilitate integrations and QA around the globe, MediaBrix has deployed an open Base URL for all of our world wide network partners to use while testing the MediaBrix SDK. This Test Base URL will eliminate the need for proxying your device to the US and ensure your app receives 100% fill during testing.

* **Test Base URL:** `https://test-mobile.mediabrix.com/v2/manifest/`

* **Production Base URL:** `https://mobile.mediabrix.com/v2/manifest/`

`https://test-mobile.mediabrix.com/v2/manifest/` should **ONLY** be used for testing purposes, as it will not deliver live campaigns to your app.

You can edit the Base URL for testing in "MediaBrixInterstitial.java":

```private final String baseURL = "BASE_URL";```

It is important to ensure that after testing, the Release build of your app uses the Production Base URL. **If you release your app using the Test Base URL, your app will not receive payable MediaBrix ads.**
