In the `com.insecureshop.LoginActivity`, we can see the following code snippet.

```java
Prefs prefs = Prefs.INSTANCE;
Context applicationContext = getApplicationContext();
Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
prefs.getInstance(applicationContext).setUsername(username);
Prefs prefs2 = Prefs.INSTANCE;
Context applicationContext2 = getApplicationContext();
Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "applicationContext");
prefs2.getInstance(applicationContext2).setPassword(password);
Util.saveProductList$default(Util.INSTANCE, this, null, 2, null);
startActivity(new Intent(this, ProductListActivity.class));
return;
```
After we successfully login, we can see that this code saves the username and password values into the app's shared preferences.

Since the app is debuggable, we can run-as the application in order to view the contents of its internal storage:

