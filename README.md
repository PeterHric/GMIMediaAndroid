GMIMediaAndroid
===============

A video streaming app for Android, supporting virtually all GMI online media streams

## Build

After cloning the repository create a file called `local.properties` in the root of the project where you specify the location of the Android SDK on your system.

```sh
sdk.dir=/usr/local/opt/android-sdk
```

After that you should be able compile the project.

```sh
$ ./gradlew build
```
