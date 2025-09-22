In the MainActivity, we can see the following code snippet
```java
btn.setOnClickListener(new View.OnClickListener() { // from class: com.ad2001.frida0x3.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Checker.code == 512) {
                    ...
                Toast.makeText(MainActivity.this.getApplicationContext(), "TRY AGAIN", 1).show();
            }
        });
```

From the code, we can see that the flag will be displayed as a text view if the value of the variable `code` in the class `Checker` is equal to 512.

```java
public class Checker {
    static int code = 0;

    public static void increase() {
        code += 2;
    }
}
```

This is how the function increase() is defined.

To make the value of code 512, we need to either call this method 256 times or just change the value of code to 512.
## Hooking increase()
```javascript
Java.perform(function () {
    var Checker = Java.use("com.ad2001.frida0x3.Checker");
    for (var i = 0; i < 256; i++) {
        Checker.increase();
    }
});
```

## Changing the value directly
```javascript
Java.perform(function () {
    var Checker = Java.use("com.ad2001.frida0x3.Checker");
    Checker.code.value = 512;
});
```

Both scripts can change the value of code to 512 and hence, we will get the flag.
