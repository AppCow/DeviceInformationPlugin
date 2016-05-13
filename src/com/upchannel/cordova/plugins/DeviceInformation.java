package com.upchannel.cordova.plugins;

import java.io.IOException;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Build;

public class DeviceInformation extends CordovaPlugin {
      private String getAdId(Void... params) {
          AdvertisingIdClient.Info idInfo = null;
          try {
              Context ctx = this.cordova.getActivity().getApplicationContext();
              idInfo = AdvertisingIdClient.getAdvertisingIdInfo(ctx);
          } catch (GooglePlayServicesNotAvailableException e) {
              e.printStackTrace();
          } catch (GooglePlayServicesRepairableException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
          String advertId = null;
          try{
              advertId = idInfo.getId();
          } catch (NullPointerException e){
              e.printStackTrace();
          }

          return advertId;
      }


    private String checkValue(String str) {
        if ((str == null) || (str.length() == 0)) {
            return "\"TM.ERROR\"";
        }

        return "\"" + str + "\"";
    }

    private String getAccount(AccountManager am) {
        String str = "";

        if (am != null) {
            Account[] accounts = am.getAccounts();

            if (str.length() > 0) {
                str += ",";
            }
            str += "\"accounts\":[";
            for (int i = 0; i < accounts.length; i++) {
                if (i > 0) {
                    str += ",";
                }
                str += "{\"email\": " + checkValue(accounts[i].name) + ","
                        + "\"type\": " + checkValue(accounts[i].type) + "}";
            }
            str += "]";
        }

        return str;
    }

    private String getTelephone(TelephonyManager tm) {
        String str = "";

        if (tm != null) {
            str = "\"deviceID\": " + checkValue(tm.getDeviceId()) + ","
                    + "\"phoneNo\": " + checkValue(tm.getLine1Number()) + ","
                    + "\"netCountry\": " + checkValue(tm.getNetworkCountryIso()) + ","
                    + "\"netName\": " + checkValue(tm.getNetworkOperatorName()) + ","
                    + "\"simNo\": " + checkValue(tm.getSimSerialNumber()) + ","
                    + "\"simCountry\": " + checkValue(tm.getSimCountryIso()) + ","
                    + "\"simName\": " + checkValue(tm.getSimOperatorName()) + ","
                    + "\"userAgent\": " + checkValue(System.getProperty("http.agent")) + ","
                    + "\"systemBoardID\": " + checkValue(Build.BOARD) + ","
                    + "\"systemManufacturer\": " + checkValue(Build.MANUFACTURER) + ","
                    + "\"systemVersion\": " + checkValue(Build.VERSION.RELEASE) + ","
                    + "\"systemName\": " + checkValue("Android") + ","
                    + "\"adId\": " + checkValue(getAdId());
        }

        return str;
    }

    private String getDetails(TelephonyManager tm, AccountManager am) {
        String acc = getAccount(am);
        String tel = getTelephone(tm);

        String str = "";
        if ((acc.length() != 0) || (tel.length() != 0)) {
            str += "{" + acc;

            if (str.length() > 1) {
                str += ",";
            }

            str += tel + "}";
        }

        return str;
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            if (action.equals("get")) {
                TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                AccountManager am = AccountManager.get(this.cordova.getActivity());

                String result = getDetails(tm,am);
                if (result != null) {
                    callbackContext.success(result);
                    return true;
                }
            }
            callbackContext.error("Invalid action");
            return false;
        } catch (Exception e) {
            String s = "Exception: " + e.getMessage();

            System.err.println(s);
            callbackContext.error(s);

            return false;
        }
    }
}
