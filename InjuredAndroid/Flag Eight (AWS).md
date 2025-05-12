# Flag Eight : AWS

Upon launching the activity, I was prompted to enter a flag. To investigate further, I decompiled the APK using JADX to analyze the source code.

![Screenshot from 2025-05-12 20-09-52](https://github.com/user-attachments/assets/32e83dfd-360f-4348-bcb5-ed3bd6c2fbcd)

While reviewing the code, I noticed that the flag is stored in a Firebase Realtime Database under the endpoint `/aws`.

However, the base Firebase URL was not immediately visible in the code.

To locate it, I explored the **strings.xml** file and discovered the Firebase database URL:

`https://injuredandroid.firebaseio.com`.

By appending the known endpoint /aws to this URL, I constructed the full Firebase path:

`https://injuredandroid.firebaseio.com/aws`.

Visiting this URL returned the flag:

`C10ud_S3cur1ty_lol`

I then entered this as the input and successfully completed the challenge.

![Screenshot from 2025-05-12 20-10-52](https://github.com/user-attachments/assets/f80f9134-f3cf-432f-aafd-85abd6f00dd7)
![Screenshot from 2025-05-12 20-10-59](https://github.com/user-attachments/assets/655dd720-b232-48f5-b7c0-5b8127914717)
