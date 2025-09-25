In the `MainActivity`, we can see the following code.

```java
if (MainActivity.this.f928c.getText().toString().equals("admin")) {
                    MainActivity mainActivity = MainActivity.this;
                    b bVar = mainActivity.e;
                    String obj = mainActivity.d.getText().toString();
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                        messageDigest.update(obj.getBytes());
                        byte[] digest = messageDigest.digest();
                        StringBuffer stringBuffer = new StringBuffer();
                        for (byte b2 : digest) {
                            stringBuffer.append(Integer.toHexString(b2 & 255));
                        }
                        str = stringBuffer.toString();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        str = "";
                    }
                    if (str.equals("a2a3d412e92d896134d9c9126d756f")) {
                        Context applicationContext = MainActivity.this.getApplicationContext();
                        MainActivity mainActivity2 = MainActivity.this;
                        b bVar2 = mainActivity2.e;
                        g gVar = mainActivity2.f;
                        makeText = Toast.makeText(applicationContext, b.a(g.a()), 1);
                        makeText.show();
                    }
                }
                makeText = Toast.makeText(MainActivity.this.getApplicationContext(), "Wrong Credentials!", 0);
                makeText.show();
```

From the code, we can see that if the name is "admin" and the hash of the password is `a2a3d412e92d896134d9c9126d756f`, the flag will get toasted.

However, we are unable to find the correct word whose hash is `a2a3d412e92d896134d9c9126d756f`.

So, we can try hooking the `equals` method and make it return true even if we enter some random string.

## Hooking equals() :

```javascript
Java.perform(function() {
    var String = Java.use("java.lang.String");
    String.equals.implementation = function(other) {
       return true;
    }
});
```

This successfully toasted the flag. 
