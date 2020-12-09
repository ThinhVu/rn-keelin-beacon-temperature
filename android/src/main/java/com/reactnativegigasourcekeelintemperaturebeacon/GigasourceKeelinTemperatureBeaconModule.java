package com.reactnativegigasourcekeelintemperaturebeacon;

import android.Manifest;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class GigasourceKeelinTemperatureBeaconModule extends ReactContextBaseJavaModule {
    private final String TAG = "GKTB";

    public GigasourceKeelinTemperatureBeaconModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "GigasourceKeelinTemperatureBeacon";
    }

    @ReactMethod
    public void getBleInfo(String mac, Promise promise) {
        if (mac == null || mac.equals("")) {
            promise.reject("1", "Device mac address is was not provided!");
            return;
        }

        if (ensurePermissionsGranted()) {
            BluetoothManager bluetoothManager = (BluetoothManager) this.getCurrentActivity().getSystemService(Context.BLUETOOTH_SERVICE);
            BleScanner.scan(
                bluetoothManager.getAdapter(),
                mac.toLowerCase(),
                device -> promise.resolve(device.toString()));
        } else {
            promise.resolve("null");
        }
    }

    private boolean ensurePermissionsGranted() {
        if (ContextCompat.checkSelfPermission(this.getCurrentActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "init: Request location permission...");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.getCurrentActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Log.d(TAG, "...");
            } else {
                ActivityCompat.requestPermissions(this.getCurrentActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            return false;
        } else {
            Log.d(TAG, "init: Location permission has been granted!");
            return true;
        }
    }
}