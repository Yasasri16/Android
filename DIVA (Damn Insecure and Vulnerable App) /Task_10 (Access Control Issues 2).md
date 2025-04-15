# Task 10 : Access Control Issues : Part - 2

The tenth challenge is named as `Access Control Issues - Part 2`.

![Screenshot from 2025-04-16 03-28-51](https://github.com/user-attachments/assets/9a9cb400-7e9b-4e38-ac7d-01cb2e0c0ddd)
![Screenshot from 2025-04-16 03-28-56](https://github.com/user-attachments/assets/ecb895bb-2c50-4260-ac93-86ae96dde649)

Our goal again is to acccess these credentials from outside the application.

![Screenshot from 2025-04-16 03-23-36](https://github.com/user-attachments/assets/00eda3c8-d93d-484c-88db-eee28ab2cf25)

Upon reviewing the source code, we see that clicking the button sends an implicit intent with the action `jakhar.aseem.diva.action.VIEW_CREDS2` and with the extra value as either true or false depending on whether the `Register Now` button is selected or not.

![Screenshot from 2025-04-16 03-32-12](https://github.com/user-attachments/assets/5aa3a885-420c-41e3-beea-bdaaf20666b9)

We can see that the action `jakhar.aseem.diva.action.VIEW_CREDS2` is handled by the activity `jakhar.aseem.diva.APICreds2Activity`.

![Screenshot from 2025-04-16 03-34-02](https://github.com/user-attachments/assets/e073beed-040f-4af9-a233-09fc46413801)

We can see that this activity is responsible for displaying the sensitive API credentials.

Now, Let's try launching this activity like we did before.

![Screenshot from 2025-04-16 03-36-53](https://github.com/user-attachments/assets/54665b3a-49fd-4a06-9508-a5e60fa46043)

But looks like we failed to do it.

This is because of the extra value that is being passed along with the action.

In `APICreds2Activity` , we can see this snippet of code :

`boolean bcheck = i.getBooleanExtra(getString(R.string.chk_pin), true);`

which means bcheck will store the value true or false which is retrieved from chk_pin and by default, it is set to `true`.

Also, here is another interesting snippet of code :

```
if (!bcheck) {
            apicview.setText("TVEETER API Key: secrettveeterapikey\nAPI User name: diva2\nAPI Password: p@ssword2");
            return;
}
```
`if(!bcheck)` implies `if bcheck is false`.

Hence if the value of chk_pin is false, we can retrive the data.

Now, while using adb command, we need to manually pass the boolean value "false" too.

This is the final command :

`adb shell am start -n jakhar.aseem.diva/.APICreds2Activity -a jakhar.aseem.diva.action.VIEW_CREDS2 --ez check_pin false`.

**NOTE : --ez is used to pass a boolean extra (--ez <key> <true|false>)**.

The core issue here is that **the API credentials are accessible via an implicit intent that can be triggered by any app or external entity, allowing unauthorized access to sensitive information**. This exposes the credentials to potential interception by malicious actors.

The possible solution is **to switch to explicit intents**, where the target activity is clearly specified, ensuring that only the app itself can trigger the activity. Additionally, **implementing proper access control mechanisms**, such as authentication or permission checks, would ensure that only authorized users or apps can access sensitive data like the API credentials.
