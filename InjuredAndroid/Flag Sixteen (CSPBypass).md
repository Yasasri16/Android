# Flag Sixteen : CSP Bypass

Launching the activity led me to a blank screen with the title CSP Bypass Activity.

![Screenshot from 2025-05-13 16-36-39](https://github.com/user-attachments/assets/55e3c708-421d-4c42-95b4-1670f75288ee)

Hints led me to "AndroidManifest.xml" in which I found the following code.

```xml
 <activity

            android:theme="@style/AppTheme.NoActionBar"
            android:label="@string/title_activity_c_s_p_bypass"
            android:name="b3nac.injuredandroid.CSPBypassActivity">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>
                <data
                    android:scheme="http"
                    android:host="b3nac.com"
                    android:pathPattern="/.*/"/>
                <data
                    android:scheme="https"
                    android:host="b3nac.com"
                    android:pathPattern="/.*/"/>
            </intent-filter>
        </activity>
```

We can see that we can trigger a deep link here.

The scheme is either http or https and the host is b3nac.com and the path Pattern can be anything.

Hence I tried to trigger the deep link using the adb command 

`adb shell am start -n b3nac.injuredandroid/.CSPBypassActivity -d "http://b3nac.com"`

When I did that, the url opened in my browser.

![Screenshot from 2025-05-13 16-29-13](https://github.com/user-attachments/assets/cb8e7289-7215-4300-a2f3-edd0463f1b6a)

When I pressed back, I was asked to enter the flag. After some time, we can see a textview saying "try another url".

![Screenshot from 2025-05-13 16-29-25](https://github.com/user-attachments/assets/e1fdc61a-9530-4559-a252-83d75d87e99b)

Then I took a look at source code and found a encrypted string.

`kOC6ZrdMXEnfIKWihcBNLTWIhDiINUfSQyYrFsTpEBGZy1KmfPMTwtba8CXa/WVAVoJ1ACvJMd8f/MF97/7UaeNCQvC9OD4lZ/vQN6LmpBU=`.

This string is encrypted twice using DES encryption.

I decrypted the string twice using respective secret keys and found a url.

![Screenshot from 2025-05-13 16-32-43](https://github.com/user-attachments/assets/bca7a620-e06a-44dc-a4bc-768a933994e2)

I have tried modifying my adb command as `adb shell am start -n b3nac.injuredandroid/.CSPBypassActivity -d "https://b3nac.com/contentsecuritypolicyflag.html"`

This directed me to browser and then blank page.

Then I monitered the traffic using HttpToolkit after I execute the adb command.

I found that `https://b3nac.com/contentsecuritypolicyflag.html` url gave a response body but it has nothing sus in it.
