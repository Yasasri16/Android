# Exercise 5 : Finding the Native Function in an Android App

The goal of this exercise is to reverse engineer an Android app (`Mediacode.apk`) to find native method declarations in the Java code back to their corresponding implementations in the native `.so` libraries. This includes:

1. Identifying declared native methods in the DEX bytecode.
2. Finding which native library is loaded.
3. Extracting the native library from the APK.
4. Loading the native library into a disassembler (Ghidra).
5. Finding the corresponding function inside the `.so` file.


## Step 1: Analyze APK with JADX
1. Launch `jadx` and open the APK file `Mediacode.apk`.
2. In the **Resources** tab, navigate to the `/lib` folder to verify the presence of native libraries. You should see folders like:
   
`lib/armeabi-v7a/libnative-lib.so lib/x86/libnative-lib.so`

4. Use the search bar in JADX to search for `native` to find Java-declared native methods. You should find **two** native methods declared.

Example:

```java
public native String stringFromJNI();
public native int getSecretNumber();
```
Now search for System.loadLibrary or System.load in the Java code to determine which .so file is being loaded. You’ll likely find something like:

```java
static {
    System.loadLibrary("native-lib");
}
```
## Step 2: Extract the APK and Native Libraries
```bash
mkdir mediacode_extracted
cp ~/samples/Mediacode.apk mediacode_extracted/
cd mediacode_extracted
unzip Mediacode.apk
```
This will give you access to:

```bash
lib/armeabi-v7a/libnative-lib.so
lib/x86/libnative-lib.so
```
Choose the architecture you’re comfortable analyzing. Let’s go with lib/x86/libnative-lib.so.

### Step 3: Load Native Library in Ghidra
Open Ghidra:
`ghidraRun`

### Step 4: Identify the Native Function

You now want to find which function in the .so file maps to the declared Java native method.

Case 1: Dynamic Linking
If the function is named according to the JNI naming convention, you can search for:

`Java_<package>_<class>_<method>`

These should appear directly in the Functions list in Ghidra.

Case 2: Static Linking (RegisterNatives)

If JNI function names are not found, check JNI_OnLoad():

Search for JNI_OnLoad function.

Inside JNI_OnLoad, look for calls to RegisterNatives.

Search these function names in Ghidra.

