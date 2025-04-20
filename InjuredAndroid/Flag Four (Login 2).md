# Flag Four: Login 2

Upon opening this challenge, the app prompts the user to enter a flag. 

![Screenshot from 2025-04-21 00-31-52](https://github.com/user-attachments/assets/77514f6a-321e-44c0-9db9ccb9215b9478)

To determine the correct value, I began by inspecting the app's source code using JADX.

In the submitFlag() method, I found the following code:

```java
public final void submitFlag(View view) {
    EditText editText = (EditText) findViewById(R.id.editText2);
    d.s.d.g.d(editText, "editText2");
    String obj = editText.getText().toString();
    byte[] a2 = new g().a();
    d.s.d.g.d(a2, "decoder.getData()");
    if (d.s.d.g.a(obj, new String(a2, d.w.c.f2418a))) {
        Intent intent = new Intent(this, (Class<?>) FlagOneSuccess.class);
        new FlagsOverview().I(true);
        new j().b(this, "flagFourButtonColor", true);
        startActivity(intent);
    }
}
```

In this code, the input entered by the user is compared against a string derived from a byte array `a2`. 

To understand what string `a2` contains, I examined the class `g`, where the byte array is defined as:

```java
public class g {
    private byte[] f1468a = Base64.decode("NF9vdmVyZG9uZV9vbWVsZXRz", 0);

    public byte[] a() {
        return this.f1468a;
    }
}
```

Here, I noticed that the byte array is the result of decoding a Base64 encoded string. 

I decoded the string `NF9vdmVyZG9uZV9vbWVsZXRz` and obtained the flag :

`4_overdone_omelets`

I then entered this as the input and successfully completed the challenge.

![Screenshot from 2025-04-21 00-32-10](https://github.com/user-attachments/assets/0926bd99-b193-4ee9-96b3-417dffd948c3)
![Screenshot from 2025-04-21 00-32-18](https://github.com/user-attachments/assets/76c37b13-76c0-43b8-aead-cd348fe09c2d)



