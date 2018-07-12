package com.paitoo;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNReactNativeSnapkitModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNReactNativeSnapkitModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNReactNativeSnapkit";
  }

  @ReactMethod
  public void onClick() {
  		SnapLogin.getAuthTokenManager(this).startTokenGrant();
  }

  @ReactMethod
  public boolean isUserLoggedIn () {
   		SnapLogin.isUserLoggedIn(getContext());
  }

  @ReactMethod
  public void revokeToken() {
  	SnapLogin.getAuthTokenManager(this).revokeToken();
  }

  @ReactMethod
  public void boolean isUserLoggedIn () {
	SnapLogin.isUserLoggedIn(getContext());
  }

}