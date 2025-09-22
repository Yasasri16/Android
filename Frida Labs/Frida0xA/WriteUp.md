In the MainActivity, we can see the following code.

```java
if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding2;
        }
        activityMainBinding.sampleText.setText(stringFromJNI());
```
The string from jni is just getting displayed which is "Hello Hackers".

After decompiling using ida, we can see that there is another function named get_flag() which takes 2 integer arguments and if their sum is equal to 3, it logs the flag.

Hence, we just need to call the method get_flag with the arguments such as 1,2 (whose sum is 3).

It can be done using the following script.

```javascript
var get_flag_adr =  Module.enumerateExports("libfrida0xa.so")[148]["address"]
console.log(get_flag_adr);
var native_ptr = new NativePointer(get_flag_adr);
const get_flag = new NativeFunction(native_ptr, "void", ['int', 'int']);
get_flag(1,2);
```

This script successfully calls the method `get_flag()`.
