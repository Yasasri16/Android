# Task 11 : Access Control Issues - Part 3

Now, this is the tenth challenge named `Access Control - Part 3`.

The goal is to access notes stored by the app **without entering the correct PIN** and **from outside the application**.

Upon launching the Notes section in the app, it prompts the user to enter a PIN before displaying any saved notes. This suggests the presence of some access control mechanism.

Decompiling the application using `jadx` reveals the following code in the `AccessControl3NotesActivity`:

```java
Cursor cr = getContentResolver().query(
    NotesProvider.CONTENT_URI,
    new String[]{"_id", "title", "note"},
    null,
    null,
    null
);
```

The `NotesProvider.CONTENT_URI` is defined as:

```
content://jakhar.aseem.diva.provider.notesprovider/notes
```

This indicates that the app uses a **ContentProvider** to manage its notes data. If the provider is not properly secured, it may be possible to access the data externally.

We can access the notes using the following commands.

`adb shell`

Then, using the Android `content` command to query the provider:

`content query --uri content://jakhar.aseem.diva.provider.notesprovider/notes`


The command returns the stored notes in plaintext, successfully bypassing the PIN check.

The key issue here is **the unrestricted access to sensitive data via an exposed ContentProvider**. The possible solution is **to set android:exported="false"**, **enforce proper permissions**.
