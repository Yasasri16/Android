In the MainActivity, we can see the following code.
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.t1 = (TextView) findViewById(R.id.textview);
    }

    public void get_flag(Checker A) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (1234 == A.num1 && 4321 == A.num2) {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec("MySecureKey12345".getBytes(), "AES");
            cipher.init(2, secretKeySpec);
            byte[] decryptedBytes = Base64.getDecoder().decode("QQzMj/JNaTblEHnIzgJAQkvWJV2oK9G2/UmrCs85fog=");
            String decrypted = new String(cipher.doFinal(decryptedBytes));
            this.t1.setText(decrypted);
        }
    }
```

We can see that the method `get_flag` is not getting called in `onCreate()`.
If this method is called in the method `onCreate` and if the values of num1 and num2 present in the `Checker` class are equal to 1234 and 4321 respectively, we can get the flag.

The following script does that.

```javascript
Java.performNow(function() {
  Java.choose('com.ad2001.frida0x6.MainActivity', {
    onMatch: function(instance) {
      var checker = Java.use("com.ad2001.frida0x6.Checker");
      var checker_obj  = checker.$new();  
      checker_obj.num1.value = 1234; 
      checker_obj.num2.value = 4321; 
      instance.get_flag(checker_obj);
    },
    onComplete: function() {}
  });
});
```
Since we have initialized the values of num1 and num2, now, we can see the flag getting displayed.
