In the `MainActivity`, we can see the following code.

```java
 button.setOnClickListener(new View.OnClickListener() { // from class: com.ad2001.a0x9.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MainActivity.this.check_flag() == 1337) {
                    ...
                Toast.makeText(MainActivity.this.getApplicationContext(), "Try again", 1).show();
            }
        });
```

Its clear that if the method `check_flag` returns 1337, we can get the flag.

Using ida,
```c
__int64 Java_com_ad2001_a0x9_MainActivity_check_1flag()
{
  return 1;
}
```
we can see that the method `check_flag` always returns 1.

But if it return 1337, we will get the flag.

## Hooking check_flag() :

We will simply find its address and make it return the value 1337 forcefully.

```javascript
var huhu =  Module.enumerateExports("liba0x9.so")[0]['address']
Interceptor.attach(huhu, {
    onEnter: function (args) {
    },
    onLeave: function (retval) {
        retval.replace(1337)
    }
});
```

Doing this, successfully gave the flag.
