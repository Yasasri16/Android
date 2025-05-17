# Baby Android 1 

After installing and launching the app, the initial screen displayed a simple message:

`Too slow!!`

I decided to see the source code by decompiling the APK with JADX.

Inside the resources, I found the layout file activity_main.xml containing 28 TextView elements. 

Each TextView held a single letter, but the letters appeared scrambled and out of order. 

Because the layout used ConstraintLayout, we can't manually sit and figure out the positions.

To better understand the arrangement, I copied the entire layout XML and created a new project in Android Studio. 

After pasting the code into the project’s activity_main.xml, I opened the layout preview.

This allowed the letters to be displayed in their actual positions according to the constraints, which revealed the hidden flag clearly.

![image](https://github.com/user-attachments/assets/bdc65b3f-f70f-474f-b538-35d9b3c32e42)

(b was at the top of the screen. I couldn't include it since I zoomed in)

# Baby Android 2

Upon installing and launching the app, it asked me to enter a flag for sanity check.

I then opened jadx to view the source code.

In the `MainActivity`, we can see the follwoing snippet.

```java
if (FlagChecker.check(flagAttempt)) {
                    banner.setText("That's the right flag!!!");
                } else {
                    banner.setText("Nope! Try again if you'd like");
                }
```

Our input is being sent to the method `check` of `FlagChecker` class.

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

This gave the output as `byuctf{c++_in_an_apk??}` which passed the sanity check and is the flag.
