In the `MainActivity`, we can see the following code.
```java
button.setOnClickListener(new View.OnClickListener() { // from class: com.ad2001.frida0x8.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String ip = MainActivity.this.edt.getText().toString();
                int res = MainActivity.this.cmpstr(ip);
                if (res == 1) {
                    Toast.makeText(MainActivity.this, "YEY YOU GOT THE FLAG " + ip, 1).show();
                } else {
                    Toast.makeText(MainActivity.this, "TRY AGAIN", 1).show();
                }
            }
        });
```
If the input which we enter and the a string from the jni is equal, the value of res will be 1 and hence we will get the flag as a toast.
  
  ```c
__s1 = (char *)_JNIEnv::GetStringUTFChars(param_1,param_3,(uchar *)0x0);
  local_c4 = 0;
  while( true ) {
    uVar2 = __strlen_chk("GSJEB|OBUJWF`MBOE~",0xffffffffffffffff);
    if (uVar2 <= (ulong)(long)local_c4) break;
    local_78[local_c4] = "GSJEB|OBUJWF`MBOE~"[local_c4] + -1;
    local_c4 = local_c4 + 1;
  }
  lVar3 = __strlen_chk("GSJEB|OBUJWF`MBOE~",0xffffffffffffffff);
  local_78[lVar3] = '\0';
  iVar1 = strcmp(__s1,local_78);
```

From this code in the function cmpstr, we can understand that our input is being compared to a string after getting performed by an operation (subtracting by 1).

```javascript
var strcmp_adr =  Module.enumerateExports("libfrida0x8.so")[0]["address"];
Interceptor.attach(strcmp_adr, {
    onEnter: function (args) {
        var arg0 = Memory.readUtf8String(args[0]);
        var arg1 = Memory.readUtf8String(args[1]);
        if (arg0 === "Huii") {
            send("agr[0]: (User Input)");
            send("arg[1]: "+ arg1);
        }
    },
    onLeave: function (retval) {
    }
});
```

The above script prints the input which we entered and also the other string (which is the flag).
Hence we will get the flag.
