# Flag Twelve : Protected Components
Upon launching the activity, the UI provided two important hints: 

**1. Use an exported Activity**

**2. A PoC app is needed**

![Screenshot from 2025-05-13 13-06-09](https://github.com/user-attachments/assets/1c7721d0-9a43-4a2e-b933-c9fe218da1a7)
![Screenshot from 2025-05-13 13-06-16](https://github.com/user-attachments/assets/f4674b2c-2734-4edc-ab4d-4ec339601602)

These hints suggest exploiting an exported component via an external intent.

Using JADX, I examined the AndroidManifest.xml file and found the following exported activity:

```xml
<activity
            android:theme="@style/AppTheme.NoActionBar"
            android:label="@string/title_activity_exported_protected_intent"
            android:name="b3nac.injuredandroid.ExportedProtectedIntent"
            android:exported="true"/>

```

This indicated that the activity can be invoked externally.

I attempted to launch the activity using ADB:

`adb shell am start -n b3nac.injuredandroid/.ExportedProtectedIntent`

However, the app immediately crashed, indicating that the activity likely expects specific data to be passed via intent extras.

I looked into the logic of FlagTwelveProtectedActivity, which revealed the following code:

```java
Uri parse = Uri.parse(getIntent().getStringExtra("totally_secure"));

if (getIntent() == null || !getIntent().hasExtra("totally_secure")) {
    finish();
    return;
}

if (!d.s.d.g.a("https", parse.getScheme())) {
    webView.loadData(getIntent().getStringExtra("totally_secure"), "text/html", "UTF-8");
    return;
}
```
This confirms that the activity expects an intent extra with the key "totally_secure", containing a URL that must use the https scheme.


To satisfy the condition and trigger the success path, I sent the required intent using:

`adb shell am start -n b3nac.injuredandroid/.FlagTwelveProtectedActivity --es totally_secure "https://"`

This successfully launched the activity and redirected me to a success page, revealing the flag.

![Screenshot from 2025-05-13 13-08-22](https://github.com/user-attachments/assets/d3debfca-2222-4350-bc3f-91ba63bc5f02)



