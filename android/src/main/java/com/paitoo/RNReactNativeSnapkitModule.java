
package com.paitoo;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.snapchat.kit.sdk.SnapLogin;
import com.snapchat.kit.sdk.core.controller.LoginStateController;
import com.snapchat.kit.sdk.core.models.MeData;
import com.snapchat.kit.sdk.core.models.UserDataResponse;
import com.snapchat.kit.sdk.login.networking.FetchUserDataCallback;
import android.support.annotation.Nullable;

public class RNReactNativeSnapkitModule extends ReactContextBaseJavaModule {
  public void sendEvent(ReactApplicationContext reactContext,
                        String eventName,
                        @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }


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
  public void isUserLoggedIn(Promise promise) {
    boolean isTrue = SnapLogin.isUserLoggedIn(getReactApplicationContext());
    String str = Boolean.toString(isTrue);
    Log.w("Helloooooooooooooooooo", str);
    promise.resolve(isTrue);
  }

  @ReactMethod
  public void revokeToken() {
    SnapLogin.getAuthTokenManager(getReactApplicationContext()).revokeToken();
  }

  @ReactMethod
  public void setToken(String accessToken) {
    SnapLogin.getAuthTokenManager(getReactApplicationContext()).setAccessToken(accessToken);
  }

  @ReactMethod
  public void AddChangedLogin() {
    final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
            new LoginStateController.OnLoginStateChangedListener() {
              @Override
              public void onLoginSucceeded() {
                sendEvent(getReactApplicationContext(), "LoginSucceeded", null);
              }

              @Override
              public void onLoginFailed() {
                sendEvent(getReactApplicationContext(), "LoginFailed", null);

              }

              @Override
              public void onLogout() {
                sendEvent(getReactApplicationContext(), "LogOut", null);

              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).addOnLoginStateChangedListener(mLoginStateChangedListener);
  }

  @ReactMethod
  public void RemoveChangedLogin() {
    final LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener =
            new LoginStateController.OnLoginStateChangedListener() {
              @Override
              public void onLoginSucceeded() {
                sendEvent(getReactApplicationContext(), "LoginSucceeded", null);
              }

              @Override
              public void onLoginFailed() {
                sendEvent(getReactApplicationContext(), "LoginFailed", null);

              }

              @Override
              public void onLogout() {
                sendEvent(getReactApplicationContext(), "LogOut", null);

              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).removeOnLoginStateChangedListener(mLoginStateChangedListener);
  }

  @ReactMethod
  public void AddStartLogin() {
    final LoginStateController.OnLoginStartListener mLoginStartListener =
            new LoginStateController.OnLoginStartListener() {
              @Override
              public void onLoginStart() {
                sendEvent(getReactApplicationContext(), "Login Start", null);
              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).addOnLoginStartListener(mLoginStartListener);
  }

  @ReactMethod
  public void RemoveStartLogin() {
    final LoginStateController.OnLoginStartListener mLoginStartListener =
            new LoginStateController.OnLoginStartListener() {
              @Override
              public void onLoginStart() {
                sendEvent(getReactApplicationContext(), "Login Start", null);
              }
            };

    SnapLogin.getLoginStateController(getReactApplicationContext()).removeOnLoginStartListener(mLoginStartListener);
  }

  @ReactMethod
  public void fetchUserData(final Promise promise) {
    String query = "{me{bitmoji{avatar},displayName}}";
    String variables = null;
    final FetchUserDataCallback ourCallback =
            new FetchUserDataCallback() {
              @Override
              public void onSuccess(@android.support.annotation.Nullable UserDataResponse userDataResponse) {
                if (userDataResponse == null || userDataResponse.getData() == null) {
                  return;
                }

                MeData meData = userDataResponse.getData().getMe();
                if (meData == null) {
                  return;
                }

                promise.resolve(userDataResponse.getData().getMe().getDisplayName());
              }

              @Override
              public void onFailure(boolean b, int i) {
                String B = Boolean.toString(b);
                String I = Integer.toString(i);
                Log.w(B,I);
                promise.reject(B);
              }
            };
  }
}
