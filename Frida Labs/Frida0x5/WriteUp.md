We can see the following code snippet in the MainActivity.
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.t1 = (TextView) findViewById(R.id.textview);
    }

    public void flag(int code) {
        if (code == 1337) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec("WILLIWOMNKESAWEL".getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec(new byte[16]);
                cipher.init(2, secretKeySpec, iv);
                byte[] decodedEnc = Base64.getDecoder().decode("2Y2YINP9PtJCS/7oq189VzFynmpG8swQDmH4IC9wKAY=");
                byte[] decryptedBytes = cipher.doFinal(decodedEnc);
                String decryptedText = new String(decryptedBytes);
                this.t1.setText(decryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
```

We can clearly see that we just need to call the method `flag` with the argument 1337. But that method isn't called anywhere in the MainActivity.
Hence, we need to change the implementation of `onCreate`.

```javascript
Java.perform(function() {
        var MainActivity = Java.use("com.ad2001.frida0x5.MainActivity");
        MainActivity.onCreate.implementation = function(savedInstanceState) {
            this.onCreate(savedInstanceState);
                this.flag(1337);
        };
   
});
```
The above script clearly does that.

After we run this script, we can see the flag.
