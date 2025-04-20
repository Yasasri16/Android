# Flag One: Login

After installing the app and launching the first challenge titled `Flag One - Login`, the app presents a simple interface with an input field labeled "Enter Flag" and a Submit button.
![Screenshot from 2025-04-20 23-42-43](https://github.com/user-attachments/assets/526c0e7c-8f87-46f1-9ca7-b996359f0695)

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

![Screenshot from 2025-04-20 23-43-03](https://github.com/user-attachments/assets/51bbf911-baf4-4131-9c77-7cccf6ac9e8b)

![Screenshot from 2025-04-20 23-43-07](https://github.com/user-attachments/assets/71373482-0abf-4887-b3ce-4fce48cd2f72)
