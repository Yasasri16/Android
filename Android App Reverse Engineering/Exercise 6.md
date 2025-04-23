# Exercise 6 : How to Find and Reverse a Native Function in an Android App

In Android malware analysis, if suspicious activity is not visible in the Java/Kotlin code (DEX), it might be hidden in native code—typically .so files inside the APK under lib/. Here's a step-by-step approach:

**Step 1 : Decompile using jadx**

This gives you the full decompiled and the manifest. You’ll also see the native libraries under:

```bash
lib/arm64/libnative-lib.so
```
**Step 2: Check Manifest and DEX for Native Links**

In AndroidManifest.xml, look for permissions like:

```xml
<uses-permission android:name="android.permission.SEND_SMS" />
```

In decompiled Java (via JADX), look for native method declarations:

```java
public native void sendSMS();
```

Also look for:

```java
System.loadLibrary("native-lib");
```

This confirms the DEX is calling into native code.

**Step 3: Load the .so File into Ghidra or IDA**

Open libnative-lib.so in Ghidra or IDA Free.

**Step 4: Identify JNI Functions**

Look for symbols like:

```nginx
Java_com_example_app_MainActivity_sendSMS
```

These are native implementations of Java methods. You can find them by searching for Java_ prefixes.

🛠 Step 5: Reconstruct Logic in Pseudocode

Use Ghidra’s decompiler to turn assembly into C-like pseudocode. You may see:

```c
const char* number = "19761234";
const char* msg = "Subscribe";
jclass smsMgr = env->FindClass("android/telephony/SmsManager");
jmethodID send = env->GetMethodID(smsMgr, "sendTextMessage", ...);
env->CallVoidMethod(...);
```
This would confirm SMS behavior.

