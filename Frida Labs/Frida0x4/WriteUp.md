In the MainActivity, we can see the following code.
```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.t1 = (TextView) findViewById(R.id.txtview);
    }
```
We can clearly see that there is nothing in MainAcitvity. However there is a class named `Check` which has the following code.

```java
public class Check {
    public String get_flag(int a) {
        if (a == 1337) {
            byte[] decoded = new byte["I]FKNtW@]JKPFA\\[NALJr".getBytes().length];
            for (int i = 0; i < "I]FKNtW@]JKPFA\\[NALJr".getBytes().length; i++) {
                decoded[i] = (byte) ("I]FKNtW@]JKPFA\\[NALJr".getBytes()[i] ^ 15);
            }
            return new String(decoded);
        }
        return "";
    }
}
```

We can see that there is a method named `get_flag` and if we call that method with 1337 as an argument, the flag will get displayed.

## Hooking get_flag()
```javascript
Java.perform(function() {
  var check = Java.use("com.ad2001.frida0x4.Check");
  var check_obj = check.$new();
  var res = check_obj.get_flag(1337); 
  console.log("FLAG " + res);

})
```

This script creates an object for the Check class and then calls method using that object. Since we are able to call the method with proper argument, the flag is displayed.
