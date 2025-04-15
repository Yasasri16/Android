# Task 6 : Insecure Data Storage - Part 4 

Let's dive into the sixth challenge which is named as `Insecure Data Storage : Part 4`

![Screenshot from 2025-04-15 18-05-41](https://github.com/user-attachments/assets/648bb133-bd24-4e51-b35b-f587c2f8aab3)

During this level, people might face issues because the app lacks required required permissions. Once you give the requires permissions for your emulator, everything will be fine.

It is clear from the code that the data is being stored in a file called ".uinfo.txt" in the external storage.

The critical issue here is that **the user entered data is getting stored in the file without any exncyption or obfuscation. It is getting stored in plain text. Any one who has access to the device can view your information**.

This presents a serious security risk regarding to user privacy. Storing sensitive data like this in files without encyption leads to data leaks.

The best solution is **to encrypt the data before storing the data in a file** to prevent unauthorized access. Hence we can ensure the protection of user data against potential threats.

To display the vulnerability, we first enter our details in the app and click save button.

![Screenshot from 2025-04-15 18-15-19](https://github.com/user-attachments/assets/2d3edc0c-a76e-4efd-bbfa-5bc19b4b4c38)

Here are the commands needed to view the details which we have entered earlier.


```
adb shell
cd sdcard
cat ".uinfo.txt"
```
![Screenshot from 2025-04-15 18-19-33](https://github.com/user-attachments/assets/e752f968-dbd0-44f0-ab91-bf1b63491637)

Here are the details which we have entered earlier.
