In `Android Manifest`, we can see the exported content provider.
```xml
<provider
    android:name=".contentProvider.InsecureShopProvider"
    android:authorities="com.insecureshop.provider"
    android:exported="true"
    android:readPermission="com.insecureshop.permission.READ" />
```
Besides the fact that is exported, the other two things we take from this snippet is the name and the permission it requires.

In `com.insecureshop.InsecureShopProvide0r`, we can see the following code.

```java
if (uriMatcher2 != null && uriMatcher2.match(uri) == 100) {
            MatrixCursor cursor = new MatrixCursor(new String[]{"username", "password"});
            String[] strArr = new String[2];
            String username = Prefs.INSTANCE.getUsername();
            if (username == null) {
                Intrinsics.throwNpe();
            }
            strArr[0] = username;
            String password = Prefs.INSTANCE.getPassword();
            if (password == null) {
                Intrinsics.throwNpe();
            }
            strArr[1] = password;
            cursor.addRow(strArr);
            return cursor;
        }
```
And on the onCreate we see the uriMatcher

```java
 public boolean onCreate() {
        UriMatcher uriMatcher2 = new UriMatcher(-1);
        uriMatcher = uriMatcher2;
        if (uriMatcher2 != null) {
            uriMatcher2.addURI("com.insecureshop.provider", "insecure", 100);
            return true;
        }
        return true;
    }
```
So, this means that with the proper URI we will be able to access the username and the password of the content provider!



