package com.dimaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.onesignal.OneSignal;

import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID = "b6d68acb-4d69-4520-b212-829fe7684728";
    String LOG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //OneSignal method
        oneSignal();
        appsFlyer();
    }

    private void appsFlyer() {
        AppsFlyerLib.getInstance().init("zky33TNFo27BGW44UCiFof", null, this);
        AppsFlyerLib.getInstance().start(this);
    }

    private void oneSignal() {
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

    AppsFlyerConversionListener conversionListener =  new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionDataMap) {

            for (String attrName : conversionDataMap.keySet())
                Log.d(LOG_TAG, "Conversion attribute: " + attrName + " = " + conversionDataMap.get(attrName));
            String status = Objects.requireNonNull(conversionDataMap.get("af_status")).toString();
            if(status.equals("Organic")){
                // Business logic for Organic conversion goes here.
            }
            else {
                // Business logic for Non-organic conversion goes here.
            }
        }

        @Override
        public void onConversionDataFail(String errorMessage) {
            Log.d(LOG_TAG, "error getting conversion data: " + errorMessage);
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> attributionData) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
        }

        @Override
        public void onAttributionFailure(String errorMessage) {
            // Must be overriden to satisfy the AppsFlyerConversionListener interface.
            // Business logic goes here when UDL is not implemented.
            Log.d(LOG_TAG, "error onAttributionFailure : " + errorMessage);
        }

    };

}