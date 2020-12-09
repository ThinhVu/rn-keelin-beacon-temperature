import { NativeModules, Platform } from 'react-native';
const { GigasourceKeelinTemperatureBeacon: NI } = NativeModules;

export default {
  getInfo: async (macAddress) => {
    // see Android GigasourceKeelinTemperatureBeaconModule
    if (Platform.os !== 'android') {
      console.log(`Gathering ble info from ${Platform.os} is not supported.`);
      return null;
    }

    const bleJson = await NI.getBleInfo(macAddress);
    return JSON.parse(bleJson);
  }
}
