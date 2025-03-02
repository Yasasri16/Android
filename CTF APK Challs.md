# easy1.apk
When I installed the app and opened it, It asked me to enter a password for the flag.

There are two inputs. Username and password. Since it is just asking me for the password, the username can be anything.

I then used "adb shell dumpsys window | grep mCurrentFocus" to understand which activity is currently running. 

After knowing that it is MainActivity, I used jadx to view the source code.

![Screenshot from 2025-03-02 14-52-51](https://github.com/user-attachments/assets/7020f7a3-8af8-4b83-a512-3c7813547a82)

The code clearly says that we will get the password when we decrypt the "b74dec4f39d35b6a2e6c48e637c8aedb" using md5 decryption.

After decryption, we will get "Spring2019" which is our password. 

When we enter the password in the app (we need not enter the username) , the flag will display.

![Screenshot from 2025-03-02 15-01-18](https://github.com/user-attachments/assets/c93215b4-6177-4c2a-8d7b-042808679ae8)
