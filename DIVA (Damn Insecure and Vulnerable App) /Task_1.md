# Task 1

After installing the APK, we can see why this application is designed and we can see 13 different options or levels.

The initial one is `Insecure Logging`.

To understand what's actually happening over there, we need to examine the source code through JADX.

![Screenshot from 2025-04-15 15-11-35](https://github.com/user-attachments/assets/00362356-99f8-41f7-859f-1f0f4d0f155a)

After seeing the source code, it becomes clear that the user entered values are being logged.

We know that any application can access logcat. The fact that our sensitive information is being logged and can be accessed by anyone is the critical issue here. The best solution is either avoid logging sensitive data or implementing proper data masking. 

Now, to display the vulnerability, we can use the following command 
`adb logcat | grep "diva-log"`.

This command filters out log messages tagged with diva-log and reveals the credit card number that was entered in the app.
