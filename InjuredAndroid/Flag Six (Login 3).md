# Flag Six: Login 3

When I opened this challenge, the app prompted for a flag input. To determine the correct value, I examined the source code using JADX.

In the submitFlag() method, I found the following logic:

```java
public final void submitFlag(View view) {
    EditText editText = (EditText) findViewById(R.id.editText3);
    d.s.d.g.d(editText, "editText3");
    if (d.s.d.g.a(editText.getText().toString(), k.a("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ=="))) {
        Intent intent = new Intent(this, (Class<?>) FlagOneSuccess.class);
        FlagsOverview.G = true;
        new j().b(this, "flagSixButtonColor", true);
        startActivity(intent);
    }
}
```

Here, the input is being compared to the decrypted result of a Base64 string:

`k.a("k3FElEG9lnoWbOateGhj5pX6QsXRNJKh///8Jxi8KXW7iDpk2xRxhQ==")`

Diving into the k.a() function:

```java
public static String a(String str) {
    if (c(str)) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES")
                .generateSecret(new DESKeySpec(f1472a));
            byte[] decode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, generateSecret);
            return new String(cipher.doFinal(decode));
        } catch (...) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Not a string!");
    }
    return str;
}
```

This function is clearly performing DES decryption. The key used for decryption is defined as:

```java
private static final byte[] f1472a = h.b();
```

So I checked the h class and found:

```java
private static byte[] f1469a = Base64.decode("Q2FwdHVyM1RoMXM=", 0);

static byte[] b() {
    return f1469a;
}
```

Decoding "Q2FwdHVyM1RoMXM=" gives us the DES key as `Captur3Th1s`.

Using this key, I decrypted the original Base64-encoded string.

The result of the decryption was `{This_Isn't_Where_I_Parked_My_Car}`.
