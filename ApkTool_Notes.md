# ApkTool
Apktool is a command-line tool designed for reverse engineering Android APK files.
It allows you to
- Decode resources within an APK to their near-original form.
- Rebuild modified APK files.
- It facilitates the analysis and modification of Android applications.
  
### Key File Locations after Decoding :

`AndroidManifest.xml`: Application manifest.

`res/`: Resource files (layouts, images, strings, etc.).

`smali/`: Disassembled Dalvik bytecode (if using smali/baksmali).

`assets/`: Raw asset files.

`original/`: Contains the original manifest.


### Dalvik Bytecode:
When you develop an Android app in Java or Kotlin, the code is compiled. However, it's not compiled into native machine code. Instead, it's compiled into Dalvik bytecode. This bytecode is stored in .dex (Dalvik Executable) files within the Android APK.

### Smali :
Smali is a human-readable assembly language representation of Dalvik bytecode. Smali allows for detailed analysis and modification of Android app code.   

- Smali and Dalvik bytecode are two sides of the same coin. Dalvik bytecode is the machine-executable form, while Smali is the human-readable representation.
- Tools like baksmali convert .dex files to smali files, and tools like smali convert smali files back into .dex files.  

  

## Commands 
`apktool d <apk_file>` - To disassemble apk files.

`apktool b <decompiled_folder>` - To reassemble apk files.
