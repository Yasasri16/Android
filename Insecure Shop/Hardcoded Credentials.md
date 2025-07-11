# Hardcoded Credentials

In the `LoginActivity`, we can see the following code snippet:

```java
boolean auth = Util.INSTANCE.verifyUserNamePassword(username, password);
if (auth) {
    Prefs prefs = Prefs.INSTANCE;
    Context applicationContext = getApplicationContext();
    Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
    prefs.getInstance(applicationContext).setUsername(username);
    Prefs prefs2 = Prefs.INSTANCE;
    Context applicationContext2 = getApplicationContext();
    Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "applicationContext");
    prefs2.getInstance(applicationContext2).setPassword(password);
    Util.saveProductList$default(Util.INSTANCE, this, null, 2, null);
    Intent intent = new Intent(this, (Class<?>) ProductListActivity.class);
    startActivity(intent);
    return;
}
```
This reveals that if **auth** is true, the app navigates to ProductListActivity using an explicit intent.

The value of auth is determined by:

`boolean auth = Util.INSTANCE.verifyUserNamePassword(username, password);`

Looking into verifyUserNamePassword method, we can see the following code.

```java
public final boolean verifyUserNamePassword(String username, String password) {
    Intrinsics.checkParameterIsNotNull(username, "username");
    Intrinsics.checkParameterIsNotNull(password, "password");
    if (!getUserCreds().containsKey(username)) {
        return false;
    }
    String passwordValue = getUserCreds().get(username);
    return StringsKt.equals$default(passwordValue, password, false, 2, null);
}
```

This method calls `getUserCreds()` and uses `.containsKey()` and `.get()`.

These two functions (containsKey() and get()) are specific to Java Map interfaces, like HashMap.

Hence moving onto getUserCreds, this is the following code 

```java
private final HashMap<String, String> getUserCreds() {
    HashMap userCreds = new HashMap();
    userCreds.put("shopuser", "!ns3csh0p");
    return userCreds;
}
```
So, the hardcoded credentials are:

**Username:** shopuser

**Password:** !ns3csh0p

**Vulnerability Explanation :**

- Hardcoded credentials can be easily extracted using static analysis, decompilation, or reverse engineering tools like JADX, apktool, etc.
- Attackers can bypass authentication, gaining unauthorized access to restricted areas such as ProductListActivity.


**Fix :**

- Avoid hardcode sensitive information like passwords.
- Implement authentication via a secure backend API over HTTPS.
- For offline scenarios, store credentials using salted hashes (e.g., bcrypt) and verify using secure local storage.
