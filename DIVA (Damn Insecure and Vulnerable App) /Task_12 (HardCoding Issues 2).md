# Task 12 : Hard Coding Issues - Part 2

This challenge is named as `HardCoding Issues - Part 2`.

![Screenshot from 2025-04-16 01-38-32](https://github.com/user-attachments/assets/bdaf37f1-e0a8-44c2-b0b3-aca94520cd79)

From the source code, it is clear that the input string is getting compared to some string from a jni file.

![Screenshot from 2025-04-16 01-42-07](https://github.com/user-attachments/assets/0dd1bb51-2b35-4ba6-bc32-15f3360bb783)

In `DivaJni.class`, we saw that some native functions are declared.

![Screenshot from 2025-04-16 01-45-11](https://github.com/user-attachments/assets/e0275849-83ab-4e13-bb90-b93c6be0d17d)

In `Resources/lib`, we can find the many `.so files` (different arch).

We then download any one of these `.so files` and decompile them using IDA or Ghidra to see the native code.

![Screenshot from 2025-04-16 01-47-13](https://github.com/user-attachments/assets/7c70f274-a0a6-4a6f-991b-5743760247cf)

Here is the native code for the method `access` which returns a string `olsdfgad;lh`.

We can see that our input is being compared to the string that the method `access` returns. Hence our input should be `olsdfgad;lh`.

![Screenshot from 2025-04-16 01-50-38](https://github.com/user-attachments/assets/f99fd7ed-8017-4bb9-a026-ecae955a413e)
