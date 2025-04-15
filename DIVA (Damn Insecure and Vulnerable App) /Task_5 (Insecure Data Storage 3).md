#Task 5 : Insecure Data Storage - Part 3

Let's move on to the fifth challenge which is named as `Insecure Data Storage : Part 3`.

![Screenshot from 2025-04-15 17-25-05](https://github.com/user-attachments/assets/0c57c64b-f86e-42ef-885e-cd30a33a4ad1)

From the code, it is clear that the entered details are being stored in a temporary file that is created when we enter the save button. That temporary file is named as `uinfo`.

The critical issue here is that the user entered data is getting stored in the file without any exncyption or obfuscation. It is getting stored in plain text.

This presents a serious security risk regarding to user privacy. Storing sensitive data like this in files without encyption leads to data leaks. 

The best solution is to encrypt the data before storing the data in a file to prevent unauthorized access. Hence we can ensure the protection of user data against potential threats.

To display the vulnerability, we first enter our details in the app and click save button.

![Screenshot from 2025-04-15 17-33-03](https://github.com/user-attachments/assets/8f186dab-4a96-43c5-9514-7e0bbfddc090)

Now, we will use the following commands to see the details which we have entered earlier.

```
adb shell
cd data/data/jakhar.aseem.diva/

cat uinfo*
```


