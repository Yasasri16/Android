# hard1.apk

When we install the app, we can see that we need to enter the username and password.

From the code, we can see that username1 is retrieved from the shared preferences.

username2 is a concatenated string of username1 and "111111111111".

AES Encryption supports either 16 bytes, 24 bytes or 32 bytes.

As we can see that there are already 12 bytes in username2, the username1 should contain either 4 bytes or 12 bytes or 20 bytes.

Then by taking a look at the if else block in Main Activity, we can clearly see that username 2 should contain "1337".

Since the username2 is a concatenated string of username1 and "1111111111111", the username1 should be 1337 which is stored in shared preferences.

I then entered "1337" as username and some random password and got the flag. 
