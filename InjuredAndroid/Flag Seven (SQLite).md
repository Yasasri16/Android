# Flag Seven : SQLite 

While solving the InjuredAndroid challenge, I encountered a screen that prompted me to enter a flag followed by a password.

To understand the underlying logic, I decompiled the APK using JADX. Upon reviewing the source code, I noticed that some sensitive data was being stored in a SQLite database.

To inspect the contents of the database, I used the following ADB commands:

```bash
adb shell
cd /data/data/b3nac.injuredandroid/databases
sqlite3 Thisisatest.db
.tables
SELECT * FROM Thisisatest;
```
This revealed the following entries:

![Screenshot from 2025-05-12 19-03-16](https://github.com/user-attachments/assets/5f9b06db-8b63-431e-9433-fd4d57f8e52d)

The string `2ab96390c7dbe3439de74d0c9b0b1767` is an MD5 hash. Using an online MD5 decoder, I successfully retrieved the original plaintext value as `hunter2`.

Another string appeared to be encrypted using the ROT47 cipher. After decrypting it, I obtained the following URL:

`https://injuredandroid.firebaseio.com/sqlite.json`

Visiting this URL revealed the value: `S3V3N_11`.


By entering both values `hunter2` and `S3V3N_11` , I was able to obtain the flag.

