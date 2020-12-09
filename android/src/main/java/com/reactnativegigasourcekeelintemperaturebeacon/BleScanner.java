package com.reactnativegigasourcekeelintemperaturebeacon;

import android.bluetooth.BluetoothAdapter;
import android.os.Looper;

import static com.reactnativegigasourcekeelintemperaturebeacon.KeelinBeaconUtil.getTemperature;
import static com.reactnativegigasourcekeelintemperaturebeacon.KeelinBeaconUtil.isKeelinBeaconDevice;

public class BleScanner {
  public interface Callback {
    void onFinish(KeelinTemperatureBeacon device);
  }

  private static KeelinTemperatureBeacon targetDevice;
  private static android.os.Handler handler = new android.os.Handler(Looper.myLooper());
  public static void scan(BluetoothAdapter adapter, String macAddress, Callback cb) {
    BluetoothAdapter.LeScanCallback scanCallback = (bluetoothDevice, rssi, scanRecord) -> {
      String scanRecordStr = HexUtils.encodeHexStr(scanRecord);
      if (!isKeelinBeaconDevice(scanRecordStr))
        return;

      String deviceMac = bluetoothDevice.getAddress().replace(":", "").toLowerCase();
      if (!deviceMac.equals(macAddress))
        return;

      targetDevice = new KeelinTemperatureBeacon();
      targetDevice.name = bluetoothDevice.getName();
      targetDevice.macAddress = deviceMac;
      targetDevice.signalQuality = KeelinBeaconUtil.getRssiPercent(rssi);
      targetDevice.rssi = rssi + "dbm";
      targetDevice.temperature = getTemperature(scanRecordStr);
    };

    targetDevice = null;

    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (targetDevice != null) {
          cb.onFinish(targetDevice);
          adapter.stopLeScan(scanCallback);
        } else {
          handler.postDelayed(this, 5000);
        }
      }
    }, 5000);

    adapter.startLeScan(scanCallback);
  }
}


