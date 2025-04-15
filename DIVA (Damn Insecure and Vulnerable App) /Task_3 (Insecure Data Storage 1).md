# Task 3 : Insecure Data Storage - Part 1

Now, let's start our third challenge which is named as `Insecure Data Storage : Part 1`.

![Screenshot from 2025-04-15 16-20-16](https://github.com/user-attachments/assets/46463639-bbeb-45c9-a317-c3b457378efe)

From the code, it is evident that whenever the user enters details and clicks the Save button, the data is directly stored in SharedPreferences insecurely.

The core issue here is that **the data is saved in plain text, making it easily accessible to anyone with access to the device — especially on rooted devices**. 

This presents a serious security risk, as attackers can read or manipulate the stored data, potentially leading to data leaks or unauthorized access to app features.

The best possible solution is to encrypt the data before storing it in SharedPreferences. Encryption ensures that sensitive data remains protected and unreadable without the appropriate decryption key, thereby safeguarding it from tampering and unauthorized access.

To display the vulnerability, we first enter our details in the app and click save button.

![Screenshot from 2025-04-15 16-46-32](https://github.com/user-attachments/assets/b6ca7a27-9680-4af6-bd84-26938ecaaabd)

Now, we use the following commands to see the details we have given earlier.

`adb shell`

`cd data/data/jakhar.aseem.diva/shared_prefs `

`cat jakhar.aseem.diva_preferences.xml`

![Screenshot from 2025-04-15 16-59-12](https://github.com/user-attachments/assets/4edb5481-509f-4658-8389-211e7834b4a5)

Here are the details which I entered earlier.


