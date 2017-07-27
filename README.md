# BlockButton [![Platform](https://img.shields.io/badge/Platform-Android-green.svg) ]() [![Download](https://api.bintray.com/packages/brouding/maven/android-block-button/images/download.svg) ](https://bintray.com/brouding/maven/android-block-button/_latestVersion)[![GitHub license](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/brouding/simpledialog/blob/master/LICENSE.txt)

Android Block style Button by BROUDING
- BlockButton is an extension of LinearLayout (thinking of extending RelativeLayout too..) hence, `orientation` should be set.
- `layout_height` should be fixed.


![Sample Video](https://github.com/BROUDING/BlockButton/blob/master/sample/sample_video.gif?raw=true)

# Sample .apk

You can download the latest sample APK from this repo here: https://github.com/brouding/BlockButton/blob/master/sample/BlockButtonSample.apk

---
# Gradle Dependency
### Repository
The Gradle dependency is available via [jCenter](https://bintray.com/brouding/maven/android-block-button).
jCenter is the default Maven repository used by Android Studio.

The minimum API level supported by this library is API 14, Android 4.0 (ICE_CREAM_SANDWICH)


### Import to your project
add below code in `build.gradle (Module: app)`
```gradle
dependencies {
	// ... other dependencies here
    compile 'com.brouding:android-block-button:0.1.2.1'
}
```
---
# How to use

![Sample Image_enabled](https://github.com/BROUDING/BlockButton/blob/master/sample/BlockButton_enabled.png?raw=true) ![Sample Image_disabled](https://github.com/BROUDING/BlockButton/blob/master/sample/BlockButton_disabled.png?raw=true)
```java
<com.brouding.blockbutton.BlockButton
    app:pushDepthDp="2"
    app:buttonColor="#58ad26"
    app:buttonGapColor="@color/blockButtonGapColor"
    app:buttonDisabledColor="#999999"
    app:buttonDisabledGapColor="@color/blockButtonDisabledGapColor"
    android:id="@+id/btn_reset_guide"
    android:layout_width="wrap_content"
    android:layout_height="50dp"
    android:gravity="center"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:paddingTop="10dp"
        android:paddingBottom="10dp"
        android:paddingLeft="12dp"
        android:paddingRight="12dp"
        android:textStyle="bold"
        android:textColor="#ffffff"
        android:textSize="17dp"
        android:text="RESET" />

</com.brouding.blockbutton.BlockButton>
```

### Parameters
- `pushDepthDp` : Integer value in `dp`, default is 4 (I don't recommend integer over 6..)
- `buttonColor`, `buttonGapColor`, `buttonDisabledColor`, `buttonDisabledGapColor`
: Color values, it can be `#------` or `@color/---`

### In class
```java
private BlockButton btnResetGuidePref = (BlockButton) layoutMain.findViewById(R.id.btn_reset_guide);
btnResetGuidePref.setEnabled(false);
```

License
-------

    Copyright 2017 BlockButton authors.

    - Jeongwon Lee (ssyjk2@gmail.com)

    All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
