# Task 11 : Access Control Issues - Part 3

Now, this is the tenth challenge named `Access Control - Part 3`.

The goal is to access notes stored by the app **without entering the correct PIN** and **from outside the application**.

Upon launching the Notes section in the app, it prompts the user to enter a PIN before displaying any saved notes. This suggests the presence of some access control mechanism.

Decompiling the application using `jadx` reveals the following code in the `AccessControl3Activity`:

![Screenshot from 2025-04-16 00-29-27](https://github.com/user-attachments/assets/78d34fce-5869-49c6-b2b1-d2fa646c0baa)

When you press `go to notes` button, it will lead to `AccessControl3NotesActivity`.

![Screenshot from 2025-04-16 03-19-50](https://github.com/user-attachments/assets/ece7dd15-110d-4915-ae5b-9b9481701e9f)


The `NotesProvider.CONTENT_URI` is defined as:

![Screenshot from 2025-04-16 00-30-44](https://github.com/user-attachments/assets/bb5d201c-74c9-4a0b-895a-186fe271e393)

This indicates that the app uses a **ContentProvider** to manage its notes data. If the provider is not properly secured, it may be possible to access the data externally.

We can access the notes using the following commands.

`adb shell`

Then, using the Android `content` command to query the provider:

`content query --uri content://jakhar.aseem.diva.provider.notesprovider/notes`

![Screenshot from 2025-04-16 00-27-46](https://github.com/user-attachments/assets/01453164-7da7-44c3-b5e3-d2d7dbbd03c3)

The command returns the stored notes in plaintext, successfully bypassing the PIN check.

Also, we can directly access the notes since the data is stored in the database with name "divanotes.db"

Hence, we can access the notes using commands 
```
adb shell
cd data/data/jakhar.aseem.diva/databases
sqlite3 divanotes.db
sqlite> select * from notes;
```
![Screenshot from 2025-04-16 00-35-55](https://github.com/user-attachments/assets/0cf60430-708e-49c4-b249-6a7303454071)


The key issue here is **the unrestricted access to sensitive data via an exposed ContentProvider**. 

The possible solution is **to set android:exported="false"**, **enforce proper permissions**.
