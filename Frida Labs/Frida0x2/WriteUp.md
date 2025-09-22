In the MainActivity, we can see the following code snippet :
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.textview);
    }

    public static void get_flag(int a) {
        if (a == 4919) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec("HILLBILLWILLBINN".getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec(new byte[16]);
                cipher.init(2, secretKeySpec, iv);
                byte[] decryptedBytes = cipher.doFinal(Base64.decode("q7mBQegjhpfIAr0OgfLvH0t/D0Xi0ieG0vd+8ZVW+b4=", 0));
                String decryptedText = new String(decryptedBytes);
                t1.setText(decryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
```
From the code, its clear that if we are able to call the function get_flag with the argument 4919, we will get the flag.

Since the method `get_flag` is a static method, its too easy for us to call the method.

## Hooking get_flag()

```javascript
Java.perform(() => {
    var Activity = Java.use('com.ad2001.frida0x2.MainActivity');
    Activity.get_flag(4919);
});
```
Hence we made a script to call the `get_flag` method with the argument 4919.
