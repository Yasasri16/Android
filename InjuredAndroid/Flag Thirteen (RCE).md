# Flag Thirteen : RCE

Upon launching the RCE Activity, I was greeted with a blank screen, though the title bar displayed "RCE Activity", which suggested something deeper might be going on behind the scenes.

![Screenshot from 2025-05-13 13-51-35](https://github.com/user-attachments/assets/f071b73c-9cc0-429a-b858-8bacf197e23a)

Given the hint to "find the binary", I began inspecting the app’s internal resources.

Navigating to the assets directory, I discovered several binary files. After testing all of them, one file stood out:

`narnia.x86_64` — this binary hadn't been explored yet.

Attempting to run narnia.x86_64 yielded:

![Screenshot from 2025-05-13 13-46-23](https://github.com/user-attachments/assets/c55ed036-7a84-4475-b97e-af9f5c60c37d)

This prompted me to try:

![Screenshot from 2025-05-13 13-47-11](https://github.com/user-attachments/assets/d14b1eb0-bfc0-4267-9e1b-a0ad40f43087)

I then used all the parameters one by one.

![Screenshot from 2025-05-13 13-49-03](https://github.com/user-attachments/assets/7b900361-e754-44cd-8079-2b160c977d51)

Based on the hint, I combined the outputs of these into a phrase:

`Treasure_Planet`

From the AndroidManifest.xml, I found the intent filter for RCEActivity:

```xml
<activity
    android:theme="@style/AppTheme.NoActionBar"
    android:label="@string/title_activity_rce"
    android:name="b3nac.injuredandroid.RCEActivity">
    <intent-filter android:label="filter_view_flag11">
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <data
            android:scheme="flag13"
            android:host="rce"/>
    </intent-filter>
</activity>
```

This confirmed that I could trigger the activity via a custom URI scheme:
flag13://rce?...

To make testing easier, I created an HTML file with clickable deep links:

```html
<html>
  <body>
    <p><a href="flag13://rce?binary=narnia.x86_64&param=testOne">Test One</a></p>
    <p><a href="flag13://rce?binary=narnia.x86_64&param=testTwo">Test Two</a></p>
    <p><a href="flag13://rce?binary=narnia.x86_64&param=testThree">Test Three</a></p>
    <p><a href="flag13://rce?combined=Treasure_Planet">Submit Combined Result</a></p>
  </body>
</html>
```

I pushed the HTML file to the emulator using:

`adb push links.html /sdcard/Download/`

Then opened the file in the emulator’s browser and tested each link successfully.
