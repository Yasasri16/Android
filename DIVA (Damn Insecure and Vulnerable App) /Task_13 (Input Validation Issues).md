# Task 13 : Input Validation Issues - Part 3

Now, let's proceed with the final challenge named as `Input Validation Issues : Part 3`.

![Screenshot from 2025-04-16 02-02-25](https://github.com/user-attachments/assets/754293e6-aba2-4345-bde1-1ee47c1616cd)

By observing the source code, out input has something to do with the file from jni.

We can see that the method `initiateLaunchSequence` is from the same file as before too.

Now, let's look at the decompiled code and find the method `initiateLaunchSequence`.

![Screenshot from 2025-04-16 02-05-07](https://github.com/user-attachments/assets/1cda113b-deb0-4fce-a3d0-7e44bf972e6d)

This method is returning a string `.dotdot`.

Let's try entering it as input.

![Screenshot from 2025-04-16 02-06-28](https://github.com/user-attachments/assets/ad2a6418-c21c-4b65-89c2-c84080b75be4)

It worked. But this is not our objective. Our objective is to crash the application.

To crash the app, we use a software testing technique called `fuzzing`.

Fuzzing is a software testing technique used to find security vulnerabilities and bugs by automatically feeding unexpected, malformed, or random data (inputs) into an app and monitoring how it behaves.

![Screenshot from 2025-04-16 02-20-13](https://github.com/user-attachments/assets/50c629ad-cf22-496b-bb2c-e8810b7b2028)
![Screenshot from 2025-04-16 02-20-35](https://github.com/user-attachments/assets/7f6f1346-43a1-40d6-9fc6-7cf580f2feb1)



By entering a long string as input, the app crashes.
