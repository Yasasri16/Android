# Task 3 : Insecure Data Storage - Part 1

Now, let's start our third challenge which is named as `Insecure Data Storage : Part 1`.

![Screenshot from 2025-04-15 16-20-16](https://github.com/user-attachments/assets/46463639-bbeb-45c9-a317-c3b457378efe)

From the code, it is clear that the user entered data is being stored insecurely in Shared Preferences. Whenever the user enter the details in the app and click on save button, the details are automatically saved into the Shared Preferences.

The issue here is that **the data is stored in plain text in the Shared Preferences and anyone can view them**.

Storing data in plain text within the SharedPreferences is a security risk because attackers can easily read or modify stored data on rooted devices, leading to data leaks. Sensitive info like tokens or login states in plaintext can be exploited. 

The best possible solution is encrypting the data in Shared Preferences. Encrypting ensures data is securely stored and unreadable without proper keys, protecting against tampering and unauthorized access.

