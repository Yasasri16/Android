# Task 3: Resources

When launching this challenge, the app presents a simple input field asking for a flag. 

![Screenshot from 2025-04-21 00-12-48](https://github.com/user-attachments/assets/33e6006d-fc53-4652-b0b1ab29ff448366)

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

![Screenshot from 2025-04-21 00-13-03](https://github.com/user-attachments/assets/c38dd8e8-015d-40df-a4ef-410dc12db5f2)
![Screenshot from 2025-04-21 00-13-08](https://github.com/user-attachments/assets/be37c323-fae4-41b0-a9e2-8fb63405ac18)



