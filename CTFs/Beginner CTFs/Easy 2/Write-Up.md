# easy2.apk

Hint: **You can google for vulnerable places to see harcoded info in android**

After googling, I found many places where we can store the data within the app.

I started checking each of them for the flag.

While I was exploring strings.xml, I found "VGhlIGZsYWcgaXM6IGZsYWd7NDZhZmQ0ZjhkMmNhNTk1YzA5ZTRhYTI5N2I4NGFjYzF9Lg==" with the name secret_string, which is clearly base64 encoded string.

After decoding, I found the flag.

![Screenshot from 2025-03-02 15-11-12](https://github.com/user-attachments/assets/07a80c63-c6ad-4c4e-8198-ba500464f28c)
