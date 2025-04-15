# Task 1 : INSECURE LOGGING

Upon installing and launching the APK, we observe that the application provides 13 different challenges, each corresponding to a specific vulnerability. The first level is titled `Insecure Logging`.

To understand what's actually happening over there, we need to examine the source code through JADX.

![Screenshot from 2025-04-15 15-11-35](https://github.com/user-attachments/assets/00362356-99f8-41f7-859f-1f0f4d0f155a)

After seeing the source code, it becomes clear that the user entered values are being logged whenever there is an error. 

In this care, it always throws an error and hence the data is logged every single time.

Here is the part of code which is responsible for the logging.

`Log.e("diva-log", "Error while processing transaction with credit card: " + cctxt.getText().toString());`

We know that any application or any user can access logcat. The fact that our sensitive information is being logged and can be accessed by anyone is the critical issue here. The best solution is either avoid logging sensitive data or implementing proper data masking. 

Now, to display the vulnerability, we can use the following command.

`adb logcat | grep "diva-log"`

This command filters out log messages tagged with diva-log and reveals the credit card number that was entered by the user.
