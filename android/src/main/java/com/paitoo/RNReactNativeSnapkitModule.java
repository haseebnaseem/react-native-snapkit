
package com.paitoo;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.snapchat.kit.sdk.SnapLogin;
public class RNReactNativeSnapkitModule extends ReactContextBaseJavaModule {


  public RNReactNativeSnapkitModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNReactNativeSnapkit";
  }

  @ReactMethod
  public void onClick() {
  		SnapLogin.getAuthTokenManager(getReactApplicationContext()).startTokenGrant();
  }

  @ReactMethod
  public boolean isUserLoggedIn () {
   		boolean isTrue = SnapLogin.isUserLoggedIn(getReactApplicationContext());
        String str = Boolean.toString(isTrue);
        Log.w("Helloooooooooooooooooo",str);
        return isTrue;
  }

  @ReactMethod
  public void revokeToken() {
  	SnapLogin.getAuthTokenManager(getReactApplicationContext()).revokeToken();
  }

  @ReactMethod
  public void setToken(String accessToken){
    SnapLogin.getAuthTokenManager(getReactApplicationContext()).setAccessToken(accessToken);
  }

  @ReactMethod
  public void fetchUserData(String query, String variables, callback){
    SnapLogin.fetchUserData(getReactApplicationContext(),query,variables,callback)
  }

}
