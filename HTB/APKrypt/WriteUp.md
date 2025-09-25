In the `MainActivity`, we can see the following code snippet.

```java
public void onClick(View view) {
                try {
                    if (MainActivity.md5(MainActivity.this.ed1.getText().toString()).equals("735c3628699822c4c1c09219f317a8e9")) {
                        Toast.makeText(MainActivity.this.getApplicationContext(), MainActivity.decrypt("k+RLD5J86JRYnluaZLF3Zs/yJrVdVfGo1CQy5k0+tCZDJZTozBWPn2lExQYDHH1l"), 1).show();
                    } else {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "Wrong VIP code!", 0).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
```
Its clear that we need to enter the word whose hash is equal to `735c3628699822c4c1c09219f317a8e9`.

However we are unable to find the correct word for this.

Hence, we can just try hooking the `equals()` method and make it return true for everything.

## Hooking equals() :

```javascript
Java.perform(function() {
    var String = Java.use("java.lang.String");
    String.equals.implementation = function(other) {
        return true;
    }
});
```

This succesfully toasted the flag.
