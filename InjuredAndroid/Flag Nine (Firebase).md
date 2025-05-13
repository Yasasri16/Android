# Flag Nine : Firebase

Upon launching the activity, I was prompted to enter a flag. To investigate further, I decompiled the APK using JADX, similar to the approach used in the AWS challenge.

![Screenshot from 2025-05-12 20-25-23](https://github.com/user-attachments/assets/c701c103-3121-41f9-a9d2-900e7b945c7d)

While examining the source code, I found that the flag was stored in a Firebase Realtime Database. The endpoint appeared to be encoded as:

`ZmxhZ3Mv`

This string is Base64-encoded. Decoding it yielded `flags/`.

Also, one of the hints is to use .json trick.

Using this information, and the known Firebase base URL `https://injuredandroid.firebaseio.com`, I constructed the full URL:

`https://injuredandroid.firebaseio.com/flags.json`.

Accessing this URL gave me `[nine!_flag]`.

![Screenshot from 2025-05-13 12-39-00](https://github.com/user-attachments/assets/aebaf172-d720-4836-8c5b-63bb5152128e)

This string after base 64 coded is the flag `W25pbmUhX2ZsYWdd`.

![Screenshot from 2025-05-12 20-25-38](https://github.com/user-attachments/assets/5bee3543-070c-4e1c-a731-90c33764ff87)
![Screenshot from 2025-05-12 20-25-44](https://github.com/user-attachments/assets/e0548b74-e9c1-4728-8b2e-a2d9f1fe6498)





