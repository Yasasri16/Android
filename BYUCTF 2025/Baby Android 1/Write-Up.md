# Baby Android 1 

**Description** : If you've never reverse engineered an Android application, now is the time!! Get to it, already!! Learn more about how they work!!


After installing and launching the app, the initial screen displayed a simple message:

`Too slow!!`

I decided to see the source code by decompiling the APK with JADX.

Here's the code in `MainActivity`.

![image](https://github.com/user-attachments/assets/2ac488d9-892c-4ce4-acae-307953b70b98)

We can see that an instance of the Utilities class is created and assigned to the variable util.

Then a method named cleanUp() on the Utilities object is called.

Navigating to the `Utilities` class, gave a crucial clue.

![image](https://github.com/user-attachments/assets/1efe6a5e-f76c-4c09-996d-05f30af06e89)

We can see that there are 28 TextViews present in this method.

Each TextView is named flagPart1, flagPart2, ..., flagPart28. This function sets all of them to an empty string (""), effectively erasing the flag shown on the screen.

That's the reason you only see "Too slow !!" on the screen.

Then inside the resources, I navigated to the layout file activity_main.xml containing 28 TextView elements. 

Each TextView held a single letter, but the letters appeared scrambled and out of order. 

Because the layout used ConstraintLayout, we can't manually sit and figure out the positions.

To better understand the arrangement, I copied the entire layout XML and created a new project in Android Studio. 

After pasting the code into the project’s activity_main.xml, I opened the layout preview.

This allowed the letters to be displayed in their actual positions according to the constraints, which revealed the hidden flag clearly.

![image](https://github.com/user-attachments/assets/bdc65b3f-f70f-474f-b538-35d9b3c32e42)

(b was at the top of the screen. I couldn't include it since I zoomed in)
