# Flag One: Login

After installing the app and launching the first challenge titled `Flag One - Login`, the app presents a simple interface with an input field labeled "Enter Flag" and a Submit button.

To determine the correct flag, I decompiled the app using JADX to analyze its source code. Upon inspecting the logic behind the submit button, I found the following method:
```java
public final void submitFlag(View view) {
    EditText editText = (EditText) findViewById(R.id.editText2);
    d.s.d.g.d(editText, "editText2");
    if (d.s.d.g.a(editText.getText().toString(), "F1ag_0n3")) {
        Intent intent = new Intent(this, (Class<?>) FlagOneSuccess.class);
        new FlagsOverview().J(true);
        new j().b(this, "flagOneButtonColor", true);
        startActivity(intent);
    }
}
```
From the code above, it’s clear that the input is directly compared to the hardcoded string `F1ag_0n3`. 

If the input matches exactly, it triggers a new activity `FlagOneSuccess` and updates the flag overview and button color state.

Since the flag is hardcoded, I simply entered `F1ag_0n3` as the input and successfully completed the challenge.
