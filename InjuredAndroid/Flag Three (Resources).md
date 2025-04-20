# Task 3: Resources

When launching this challenge, the app presents a simple input field asking for a flag. 

To determine what value it was expecting, I opened the app in JADX and inspected the source code.

Within the submitFlag() function, I found the following logic:

```java
public final void submitFlag(View view) {
    EditText editText = (EditText) findViewById(R.id.editText2);
    d.s.d.g.d(editText, "editText2");
    if (d.s.d.g.a(editText.getText().toString(), getString(R.string.cmVzb3VyY2VzX3lv))) {
        Intent intent = new Intent(this, (Class<?>) FlagOneSuccess.class);
        new FlagsOverview().L(true);
        new j().b(this, "flagThreeButtonColor", true);
        startActivity(intent);
    }
}
```

Here, the input is being compared with a value retrieved from the app's resources using `getString(R.string.cmVzb3VyY2VzX3lv)`.

To find the actual string being referenced, I navigated to `res/values/strings.xml` and located the corresponding entry:

```xml
<string name="cmVzb3VyY2VzX3lv">F1ag_thr33</string>
```

The flag was clearly defined as F1ag_thr33, so I entered it into the app’s input field and successfully completed the challenge.
