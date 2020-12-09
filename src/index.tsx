import { NativeModules } from 'react-native';

type GigasourceKeelinTemperatureBeaconType = {
  multiply(a: number, b: number): Promise<number>;
};

const { GigasourceKeelinTemperatureBeacon } = NativeModules;

export default GigasourceKeelinTemperatureBeacon as GigasourceKeelinTemperatureBeaconType;
