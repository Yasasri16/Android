In `com/insecureshop/WebView2Activity`, we can see the following code snippet.
```java
Intent intent3 = getIntent();
Intrinsics.checkExpressionValueIsNotNull(intent3, "intent");
Uri data = intent3.getData();
String queryParameter = data != null ? data.getQueryParameter("url") : null;
if (!(queryParameter == null || StringsKt.isBlank(queryParameter))) {
  Intent intent4 = getIntent();
  Intrinsics.checkExpressionValueIsNotNull(intent4, "intent");
  Uri data2 = intent4.getData();
  webview.loadUrl(data2 != null ? data2.getQueryParameter("url") : null);
  return;
        }
```

It is obvious that unsanitized data are being passed to the webview. Issuing a command similar to the one below can exploit this scenario:
`adb shell am start -n com.insecureshop/.WebView2Activity --es "url" "https://yasuu.free.nf"`
