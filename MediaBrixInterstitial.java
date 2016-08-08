package com.mediabrix.admobtestapp;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.mediabrix.android.api.IAdEventsListener;
import com.mediabrix.android.api.MediabrixAPI;
/**
 * Created by muhammad on 7/7/16.
 */
public class MediaBrixInterstitial implements CustomEventInterstitial, IAdEventsListener {
    private CustomEventInterstitialListener customEventInterstitialListener;
    private Context context;
    private final String baseURL = "http://mobile.mediabrix.com/v2/manifest/";
    private String appID = "";
    private String zone = "";

    @Override
    public void requestInterstitialAd(Context context, CustomEventInterstitialListener customEventInterstitialListener, String s, MediationAdRequest mediationAdRequest, Bundle bundle) {
        this.context = context;
        this.customEventInterstitialListener = customEventInterstitialListener;
        MediabrixAPI.getInstance().initialize(this.context,baseURL,appID,this);
    }

    @Override
    public void showInterstitial() {
        MediabrixAPI.getInstance().show(this.context, zone);
    }

    @Override
    public void onDestroy() {
        MediabrixAPI.getInstance().onDestroy(this.context);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        MediabrixAPI.getInstance().onResume(context);
    }

    @Override
    public void onStarted(String s) {
        MediabrixAPI.getInstance().load(context,zone,null);
    }

    @Override
    public void onAdReady(String s) {
        customEventInterstitialListener.onAdLoaded();
    }

    @Override
    public void onAdUnavailable(String s) {
        customEventInterstitialListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
    }

    @Override
    public void onAdShown(String s) {
        customEventInterstitialListener.onAdOpened();
    }

    @Override
    public void onAdRewardConfirmation(String s) {

    }

    @Override
    public void onAdClosed(String s) {
        customEventInterstitialListener.onAdClosed();
    }

    @Override
    public void onAdClicked(String s) {
        customEventInterstitialListener.onAdClicked();
    }
}
