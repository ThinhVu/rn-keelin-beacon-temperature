package com.reactnativegigasourcekeelintemperaturebeacon;
import com.google.gson.JsonObject;

class KeelinTemperatureBeacon {
  public String name;
  public String macAddress;
  public String signalQuality;
  public String rssi;
  public String temperature;

  @Override
  public String toString() {
    JsonObject o = new JsonObject();
    o.addProperty("name", name);
    o.addProperty("mac", macAddress);
    o.addProperty("signalQuality", signalQuality);
    o.addProperty("rssi", rssi);
    o.addProperty("temperature", temperature);
    String rs = o.toString();
    return rs;
  }
}