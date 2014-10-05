GMIMediaAndroid
===============
[![Get it on Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.gospelministries.gmimedia) [![Build Status](https://travis-ci.org/PeterHric/GMIMediaAndroid.svg?branch=modern-structure)](https://travis-ci.org/PeterHric/GMIMediaAndroid)

A video streaming app for Android, supporting virtually all GMI online media streams

## Build

After cloning the repository create a file called `local.properties` in the root of the project where you specify the location of the Android SDK on your system e.g.

```sh
sdk.dir=/opt/android-sdk
```

After that you should be able compile the project.

```sh
$ ./gradlew build
```

## Licence

Copyright (C) 2014 Gospel Ministries International

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version 2 as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
