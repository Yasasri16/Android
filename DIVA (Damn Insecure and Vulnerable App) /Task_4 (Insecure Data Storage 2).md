# Task 4 : Insecure Data Storage - Part 2

Let's proceed with our fourth challenged named `Insecure Data Storage : Part 2`.

![Screenshot from 2025-04-15 17-02-46](https://github.com/user-attachments/assets/298996f3-d4a2-41d8-8f79-cb89441c7f48)

From the code, it is clear that the user entered data is being stored insecurely in SQL database named `myuser`. Whenever the user enter the details in the app and click on save button, the details are automatically saved in the database.

The core issue here is that **the data is saved in plain text, making it easily accessible to anyone with access to the device — especially on rooted devices**.

This presents a serious security risk, as attackers can read or manipulate the stored data, potentially leading to data leaks or unauthorized access to app features.

The best possible solution is to encrypt the data before storing it in the database. Encryption ensures that sensitive data remains protected and unreadable without the appropriate decryption key, thereby safeguarding it from tampering and unauthorized access. Hence we can ensure the protection of user data against potential threats.

To display the vulnerability, we first enter our details in the app and click save button.

![Screenshot from 2025-04-15 17-08-18](https://github.com/user-attachments/assets/525eaa25-f334-49cd-babb-d0f5b2f2cc2e)

Now, we use the following commands to see the details we have given earlier.

```
adb shell
cd data/data/jakhar.aseem.diva/databases 
sqlite3 ids2
sqlite> .tables
sqlite> SELECT * FROM myuser;
sqlite> .exit
```
![Screenshot from 2025-04-15 17-18-22](https://github.com/user-attachments/assets/cf3a3538-0dc2-4d5e-a5a5-28cfc0ec60d9)

Here are the details which I entered till now.
