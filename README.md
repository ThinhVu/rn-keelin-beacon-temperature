# react-native-gigasource-keelin-temperature-beacon

Reading Keelin Temperature Beacon information

## Installation

```sh
npm install react-native-gigasource-keelin-temperature-beacon
```

## Usage

Android manifest:
```
<uses-feature android:name="android.hardware.bluetooth_le" android:required="true"/>
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```


```js
import KTB from "react-native-gigasource-keelin-temperature-beacon";

// ...

const result = await KTB.getInfo("<mac address>");
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
