In the MainActivity, we can see the following code.

```java
public static final void onCreate$lambda$0(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getFlag();
    }
```

We can see that the `getFlag()` method is being called in the `MainActivity`. 

After using ida to view the implementation of getFlag, we can only find the following.

```
void Java_com_ad2001_frida0xb_MainActivity_getFlag()
{
  ;
}
```

We can clearly see that this method is not doing anything. Thats the reason nothing is happening.
