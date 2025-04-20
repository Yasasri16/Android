# Flag Two : Exported Activity

When launching this challenge, the app displays a hint message :

**There is a way to bypass the main activity and invoke other activities that are exported.**

![Screenshot from 2025-04-20 23-59-55](https://github.com/user-attachments/assets/8dc4dcde-07c9-4f4f-a0e8-84768f1a0e2f)

This suggests that we need to skip the main activity and directly launch an exported activity within the app.

I then checked the AndroidManifest.xml using JADX, and found the following entry :

```xml
<activity
    android:name="b3nac.injuredandroid.b25lActivity"
    android:exported="true" />
```

This indicates that the activity `b3nac.injuredandroid.b25lActivity` is exported and can be launched externally.

To launch it manually, I used the following ADB command:

`adb shell am start -n b3nac.injuredandroid/b3nac.injuredandroid.b25lActivity`

Running this command successfully opened the target activity, which revealed the flag.

![Screenshot from 2025-04-21 00-02-22](https://github.com/user-attachments/assets/2c5f2e64-74bd-415d-9894-71104e174d49)
