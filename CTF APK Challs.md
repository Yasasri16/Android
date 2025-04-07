# easy1.apk
When I installed the app and opened it, It asked me to enter a password for the flag.

There are two inputs. Username and password. Since it is just asking me for the password, the username can be anything.

I then used "adb shell dumpsys window | grep mCurrentFocus" to understand which activity is currently running. 

After knowing that it is MainActivity, I used jadx to view the source code.

![Screenshot from 2025-03-02 14-52-51](https://github.com/user-attachments/assets/7020f7a3-8af8-4b83-a512-3c7813547a82)

The code clearly says that we will get the password when we decrypt the "b74dec4f39d35b6a2e6c48e637c8aedb" using md5 decryption.

After decryption, we will get "Spring2019" which is our password. 

When we enter the password in the app (we need not enter the username) , the flag will display.

![Screenshot from 2025-03-02 15-02-50](https://github.com/user-attachments/assets/28ca4c08-1af8-4534-a5fa-d57bbbf2ecc3)

# easy2.apk
Hint: "You can google for vulnerable places to see harcoded info in android" 

After googling, I found many places where we can store the data within the app.

I started checking each of them for the flag.

While I was exploring strings.xml, I found "VGhlIGZsYWcgaXM6IGZsYWd7NDZhZmQ0ZjhkMmNhNTk1YzA5ZTRhYTI5N2I4NGFjYzF9Lg==" with the name secret_string, which is clearly base64 encoded string.

After decoding, I found the flag.

![Screenshot from 2025-03-02 15-11-12](https://github.com/user-attachments/assets/07a80c63-c6ad-4c4e-8198-ba500464f28c)

# easy3.apk
When you open the app, you will see a text saying something. There is no clue to find the flag. 

Then I used jadx to view the source code knowing there will be more activities.

In the SecondActivity, there is a text saying that some part of the flag is here.

Then I opened it's xml file. Under that text, there is a textview named part2 which is in strings.xml file, which is "0f_4ndro1d_r3v?"

This is the second part of the flag.

Then I started exploring the manifest file, layout files.

In activity_main.xml file, I found a text "3ver_h3ard_". This along with the second part makes a meaningful string.

From this app, we will get a meaningful string and not flag.

# easy4.apk
When I installed the app and opened it, there is a cat with a gun.

When I clicked on that image, there is an explosion.

I then started viewing the source code. 

In the MainActivity code,

![Screenshot from 2025-03-02 15-45-10](https://github.com/user-attachments/assets/d0e5c541-406e-4f62-b9db-89a0ffa8c684)

We can clearly see that the flag will be displayed when we use logcat.

I used "adb logcat | grep flag" to get the flag.

![Screenshot from 2025-03-02 15-50-21](https://github.com/user-attachments/assets/4ae2e7d6-8538-48dd-8257-26057e3275be)

# hard1.apk
When we install the app, we can see that we need to enter the username and password.

From the code, we can see that username1 is retrieved from the shared preferences.

username2 is a concatenated string of username1 and "111111111111".

AES Encryption supports either 16 bytes, 24 bytes or 32 bytes.

As we can see that there are already 12 bytes in username2, the username1 should contain either 4 bytes or 12 bytes or 20 bytes.

Then by taking a look at the if else block in Main Activity, we can clearly see that username 2 should contain "1337".

Since the username2 is a concatenated string of username1 and "1111111111111", the username1 should be 1337 which is stored in shared preferences.

I then entered "1337" as username and some random password and got the flag. 

`  





