# Baby Android 1 

**Description** : If you've never reverse engineered an Android application, now is the time!! Get to it, already!! Learn more about how they work!!


After installing and launching the app, the initial screen displayed a simple message:

`Too slow!!`

I decided to see the source code by decompiling the APK with JADX.

Here's the code in `MainActivity`.

![image](https://github.com/user-attachments/assets/2ac488d9-892c-4ce4-acae-307953b70b98)

We can see that an instance of the Utilities class is created and assigned to the variable util.

Then a method named cleanUp() on the Utilities object is called.

Navigating to the `Utilities` class, gave a crucial clue.

![image](https://github.com/user-attachments/assets/1efe6a5e-f76c-4c09-996d-05f30af06e89)

We can see that there are 28 TextViews present in this method.

Each TextView is named flagPart1, flagPart2, ..., flagPart28. This function sets all of them to an empty string (""), effectively erasing the flag shown on the screen.

That's the reason you only see "Too slow !!" on the screen.

Then inside the resources, I navigated to the layout file activity_main.xml containing 28 TextView elements. 

Each TextView held a single letter, but the letters appeared scrambled and out of order. 

Because the layout used ConstraintLayout, we can't manually sit and figure out the positions.

To better understand the arrangement, I copied the entire layout XML and created a new project in Android Studio. 

After pasting the code into the project’s activity_main.xml, I opened the layout preview.

This allowed the letters to be displayed in their actual positions according to the constraints, which revealed the hidden flag clearly.

![image](https://github.com/user-attachments/assets/bdc65b3f-f70f-474f-b538-35d9b3c32e42)

(b was at the top of the screen. I couldn't include it since I zoomed in)

# Baby Android 2

**Description** : If you've never reverse engineered an Android application, now is the time!! Get to it, already!! Learn more about how they work!!


Upon installing and launching the app, it asked me to enter a flag for sanity check.

I then opened jadx to view the source code.

In the `MainActivity`, we can see the follwoing snippet.

![image](https://github.com/user-attachments/assets/f23c5e3a-7af1-4237-93ab-0b1f1958f24a)

Our input is being stored in a variable named `flagAttempt` and sent to the method `check` of `FlagChecker` class.

Navigating to FlagChecker class gave this crucial detail.

```java
 public static native boolean check(String str);

    static {
        System.loadLibrary("babyandroid");
    }
```
I decompiled `libbabyandroid.so` file using ghidra.

After seeing the code and what's happening to the input , I wrote a corresponding python script to find the valid input.

```python
secret = "bycnu)_aacGly~}tt+?=<_ML?f^i_vETkG+b{nDJrVp6=)="
flag = ""

for i in range(23):  
    index = (i * i) % 47
    flag += secret[index]

print(flag)
```

![image](https://github.com/user-attachments/assets/0faead2b-6b59-4dbe-8f9f-620038d8fe1a)

This gave the output as `byuctf{c++_in_an_apk??}` which passed the sanity check and is the flag.

![image](https://github.com/user-attachments/assets/cda9072b-f85d-4177-95fb-1d2d950740e8)


# Secure Catalog

Upon installing the app and launching it, I was asked to enter the credentials (email and password).

I then viewed the source code using jadx.

```java
if (LoginActivity.this.emailID.getText().toString().equals(LoginActivity.this.getString(R.string.emailID)) && LoginActivity.this.password.getText().toString().equals(LoginActivity.this.getString(R.string.password))) {
                        try {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, (Class<?>) MainActivity.class));
                            return;
                        } catch (Exception e) {
                            Log.d(LoginActivity.TAG, "onClick: error " + e);
                            return;
                        }
                    }
```

We can clearly see that the credentials details are present in `strings.xml`.

After entering them, you will be directed to the MainActivity.

I found nothing interesing there and came back to jadx.

Then in the strings.xml file, something caught my attention.

There were many references about sqlcipher.

I then googled about it and found out that the database is encrypted with a key and can be accessed with "DB Browser for SQLite".

I then explored the app for the pass and found a base64 encoded string `cGFzczEyMw==` in `DBUtil` class.

The key turned out to be `pass123` and I installed DB Browser for SQLite, opened both the databases usimg the key and found the flag in the second one.

![image](https://github.com/user-attachments/assets/93212f1e-23cb-45f4-8b10-a9db571d4cbf)

# SPECIALIST BETA

Upon finding nothing after installing and launching the app, I used jadx to see the source code.

Inspecting `AndroidManifest.xml` confirmed a deep link being present with the scheme "webview" and host "url".

I tried using this adb command to trigger a deep link.

`adb shell am start -a android.intent.action.VIEW -n beta.smartcorp.specialist.ui.map/.MapFragment -d "webview://url" `

![image](https://github.com/user-attachments/assets/7fcc1041-9c1b-408f-ab2f-23b83d5dc981)

Since something is missing, I went to the `MapFragment` class.

```java
Uri data = intent.getData();
        if (data != null && String.valueOf(data).length() > 14 && String.valueOf(data).length() != 27) {
            String substring = String.valueOf(data).substring(14);
            try {
                str = new String(Base64.decode(getString(R.string.d1), 0), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                str = "";
            }
            if (substring.equals(str)) {
                textView.setText(Secrets());
                return;
            }
            return;
        }
        if (String.valueOf(data).length() == 27) {
            textView.setText("You are close to the secret!");
        } else {
            textView.setText(R.string.betaMessage);
        }
```

From here, we can now finally construct our url by decoding the base64 encided string and using that as a path.

Our new adb command is 

`adb shell am start -a android.intent.action.VIEW -n beta.smartcorp.specialist.ui.map/.MapFragment -d "webview://url?deeplink-secret-intent-data"` 

![image](https://github.com/user-attachments/assets/8fa5a2d5-e6a6-4ead-845e-e2321c2abe65)

# SUPER SECURE CATALOG

Upon installing the app and launching it, I was asked to enter the credentials.

As MainActivity is exported as true, I just used adb commands to launch it.

But I found nthe interface similar to that of `secure catalog`.

I used jadx to see the source code.

In the `Crypto`, we can see half part of the flag.

`cyberchaze{th3_encRYpt3d_STR1ng_in_3nc6yPtE6_`.

Then I saw that a native library named `in` is loaded and decompiled using ghidra.

`a` method returned `length_is_6teen!` and the corresponding script is 

```python
str = "154145156147164150137151163137066164145145156041"
sub_str = []
int_list = []

for i in range(0,48,3):
    sub_str.append(str[i:i+3])
for i in sub_str:
    int_list.append(int(i,8))
char_list = [chr(i) for i in int_list]

result = ''.join(char_list)

print(result)
```

`b` method returned `i hate sixteen><` and the corresponding script is 

```python
str = "151040150141164145040163151170164145145156076074"
sub_str = []
int_list = []

for i in range(0,48,3):
    sub_str.append(str[i:i+3])
for i in sub_str:
    int_list.append(int(i,8))
char_list = [chr(i) for i in int_list]

result = ''.join(char_list)

print(result)
```

`verify` method returned `fi!E}` and the corresponding script is 

```python
str = "666921457d"
sub_str = []
int_list = []

for i in range(0,10,2):
    sub_str.append(str[i:i+2])
for i in sub_str:
    int_list.append(int(i,16))
char_list = [chr(i) for i in int_list]

result = ''.join(char_list)

print(result)
```

The string which `verify` returned makes sense with the first part of the flag. Hence I concatenated them.

Finally, it became `cyberchaze{th3_encRYpt3d_STR1ng_in_3nc6yPtE6_fi!E}`
