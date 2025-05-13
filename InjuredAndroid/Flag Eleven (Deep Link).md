# Flag Eleven : Deep Link

When the activity is launched, the UI hints to **exploit the schema** and **check the manifest**.

![Screenshot from 2025-05-13 12-31-02](https://github.com/user-attachments/assets/0e9b016c-3d7e-4012-89c0-d685e2252138)
![Screenshot from 2025-05-13 12-31-09](https://github.com/user-attachments/assets/b48b5ec9-7802-4ac4-b517-3d7f2fada2e7)

This led me to inspect the **AndroidManifest.xml** using JADX.

The manifest reveals that DeepLinkActivity is configured with two intent filters:

```xml
<activity
    android:label="@string/title_activity_deep_link"

    android:name="b3nac.injuredandroid.DeepLinkActivity">
    
    <intent-filter android:label="filter_view_flag11">
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <data android:scheme="flag11"/>
    </intent-filter>

    <intent-filter android:label="filter_view_flag11">
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>
        <data android:scheme="https"/>
    </intent-filter>
</activity>
```

These filters allow the activity to be triggered using either the flag11 or https URI scheme.

However, analyzing the source code revealed the following condition:

```java
if (d.s.d.g.a("flag11", data != null ? data.getScheme() : null)) {
    startActivity(new Intent("android.intent.action.VIEW"));
}
```

This clearly shows that only the flag11 scheme is handled by the app logic.

To trigger this, I used the following ADB command to simulate the deep link:

`adb shell am start -n b3nac.injuredandroid/.DeepLinkActivity -a android.intent.action.VIEW -d "flag11://"`

This launched the DeepLinkActivity, which then prompted me to enter the flag.

![Screenshot from 2025-05-12 20-52-07](https://github.com/user-attachments/assets/9ec7ed66-47a9-4c72-b6a2-1cc297b12fcd)

The in-app hint said **Find the compiled treasure**, which led me to look further into the code and UI.

While exploring the activity, I found a reference to an endpoint named **binary**. Based on previous Firebase patterns in the challenge, I constructed the URL:

`https://injuredandroid.firebaseio.com/binary.json`

Accessing this endpoint returned the flag as `HIIMASTRING`.

![Screenshot from 2025-05-13 12-36-51](https://github.com/user-attachments/assets/3e42f545-f5b4-4724-8c4e-7fbafcd9fd37)

I successfully completed the challenge by entering the flag.

![Screenshot from 2025-05-12 20-52-26](https://github.com/user-attachments/assets/65d286ad-745e-48a6-ab2e-38b4c6ca24e8)
![Screenshot from 2025-05-12 20-52-32](https://github.com/user-attachments/assets/cf092d19-1c08-457b-b040-d42dcc9ae499)

