# Baby Android 2

**Description** : If you've never reverse engineered an Android application, now is the time!! Get to it, already!! Learn more about how they work!!


Upon installing and launching the app, it asked me to enter a flag for sanity check.

I then opened jadx to view the source code.

In the `MainActivity`, we can see the follwoing snippet.

![image](https://github.com/user-attachments/assets/f23c5e3a-7af1-4237-93ab-0b1f1958f24a)

Our input is being stored in a variable named `flagAttempt` and sent to the method `check` of `FlagChecker` class.

Navigating to FlagChecker class gave this crucial detail.

![image](https://github.com/user-attachments/assets/13545025-379e-47fc-9bb1-75ae3db027c8)

I decompiled the native `libbabyandroid.so` file to analyze the underlying C/C++ logic.

The `check` method contains the logic responsible for verifying if the input satisfies the sanity check criteria.

I reversed the logic and developed a Python script that generates an input satisfying the criteria.

```python
secret = "bycnu)_aacGly~}tt+?=<_ML?f^i_vETkG+b{nDJrVp6=)="
flag = ""

for i in range(23):  
    index = (i * i) % 47
    flag += secret[index]

print(flag)
```

![image](https://github.com/user-attachments/assets/c9a8b58b-ad04-4dbc-9174-689aaa1e145e)

This gave the output as `byuctf{c++_in_an_apk??}` which passed the sanity check and is the flag.

![image](https://github.com/user-attachments/assets/cda9072b-f85d-4177-95fb-1d2d950740e8)
