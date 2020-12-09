package com.reactnativegigasourcekeelintemperaturebeacon;

import java.text.DecimalFormat;

public class KeelinBeaconUtil {
    public static boolean isKeelinBeaconDevice(String scanRecord) {
        return scanRecord.contains("e0e20a18");
    }

    public static String getTemperature(String scanRecord) {
        return UnitConversion((Integer.valueOf(Integer.parseInt(scanRecord.substring(34, 38), 16))).shortValue(), false);
    }

    public static String getMillivoltage(String scanRecord) {
        return Integer.parseInt(scanRecord.substring(30, 34)) + "mV";
    }

    /**
     * 5 == 100% (good)
     * 4 = 80%
     * 4 = 60%
     * ...
     * @param rssi
     * @return
     */
    public static String getRssiPercent(int rssi) {
        return (rssi < -90) ? "Very bad" : ((rssi < -75) ? "Bad" : ((rssi < -50) ? "Normal": ((rssi < -35) ? "Good": ((rssi < -20) ? "Very good": "Very good"))));
    }

    private static String UnitConversion(double paramDouble, boolean paramBoolean) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        if (paramBoolean) {
            paramDouble = paramDouble / 256.0D * 1.8D + 32.0D;
            return decimalFormat.format(paramDouble) + "";
        }
        paramDouble /= 256.0D;
        return decimalFormat.format(paramDouble) + "";
    }
}
