# Task 3 : Insecure Data Storage - Part 1

Now, let's start our third challenge which is named as `Insecure Data Storage : Part 1`.

![Screenshot from 2025-04-15 16-20-16](https://github.com/user-attachments/assets/46463639-bbeb-45c9-a317-c3b457378efe)

From the code, it is evident that the user entered data is being stored insecurely in Shared Preferences. Whenever the user enter the details in the app and click on save button, the details are automatically saved into the Shared Preferences.

The core issue here is that **the data is saved in plain text, making it easily accessible to anyone with access to the device — especially on rooted devices**. 

This presents a serious security risk, as attackers can read or manipulate the stored data, potentially leading to data leaks or unauthorized access to app features.

The best possible solution is to encrypt the data before storing it in SharedPreferences. Encryption ensures that sensitive data remains protected and unreadable without the appropriate decryption key, thereby safeguarding it from tampering and unauthorized access.


