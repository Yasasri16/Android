In the MainActivity, we can see the following code.
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.t1 = (TextView) findViewById(R.id.textview);
        Checker ch = new Checker(123, 321);
        
    public void flag(Checker A) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (A.num1 > 512 && 512 < A.num2) {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec("MySecureKey12345".getBytes(), "AES");
            cipher.init(2, secretKeySpec);
            byte[] decryptedBytes = Base64.getDecoder().decode("cL/bBqDmfO0IXXJCVFwYLeHp1k3mQr+SP6rlQGUPZTY=");
            String decrypted = new String(cipher.doFinal(decryptedBytes));
            this.t1.setText(decrypted);
        }
    }
```
From the code, we can see that, the values of num1 and num2 are set to 123 and 321 by the constructor.
But if the values are greater than 512, we can get the flag.

The follwoing script does that.

```javascript
Java.perform(function () {
    const MainActivity = Java.use('com.ad2001.frida0x7.MainActivity');
    const Checker = Java.use('com.ad2001.frida0x7.Checker'); 

    MainActivity.flag.overload('com.ad2001.frida0x7.Checker').implementation = function(ch) {
        ch.num1.value = 513;
        ch.num2.value = 513;
        return this.flag(ch);
    };
});
```

The above script does that and we can see the flag getting displayed.
