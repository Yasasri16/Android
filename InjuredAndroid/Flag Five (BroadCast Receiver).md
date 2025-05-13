# Flag Five : BroadCast Receiver

When you just click on the button thrice, you will get the flag.

![Screenshot from 2025-05-13 17-18-22](https://github.com/user-attachments/assets/ff9a71be-e549-48b5-87b5-770a229663bc)
![Screenshot from 2025-05-13 17-18-32](https://github.com/user-attachments/assets/caf363fb-0466-4541-a053-cc85b1c6600c)
![Screenshot from 2025-05-13 17-19-20](https://github.com/user-attachments/assets/9f19e814-3740-4c8b-8224-5f3966b4501c)

Being curious about what's happening, I used jadx to see the code.

I see two declarations in manifest related to Flag Five.

`FlagFiveActivity` and `FlagFiveReceiver`

I have reviewed the code of FlagFiveActivity and we can see a custom intent there.

Then I opened the code of FlagFIveReceiver and we can see the toast messages that we get when we click the button.

The last toast message is `"You are a winner " + k.a("Zkdlt0WwtLQ="`.

And the string is a des encrypted string.

After decrypting it using the correct secret key, we get the flag `{F1v3!}`.

Also, there is a class named `TestBroadcastReceiver` and reviewing that code gave some details.

```java
Intent intent = new Intent(getApplicationContext(), (Class<?>) FlagFiveReceiver.class);
        intent.setAction("com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT");
        intent.putExtra("url", "Hi");
        sendBroadcast(intent);
```

We need to pass a string extra along with the action.

To do that, we use the adb command `adb shell am start -n b3nac.injuredandroid/.TestBroadcastReceiver -a com.b3nac.injuredandroid.intent.action.CUSTOM_INTENT --es url Hi`

Executing this command thrice will give you the flag.
