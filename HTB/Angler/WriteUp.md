When we install the Angler Application, we can see the following screen.

<img width="356" height="581" alt="image" src="https://github.com/user-attachments/assets/5ec48e57-426e-4e2a-a45d-6cf32624591d" />

Analyzing the code in the `MainActivity` revealed this :

```java
public final void onReceive(Context context, Intent intent) {
            PrintStream printStream;
            String str;
            if (intent.getStringExtra("Is_on").equals("yes")) {
                MainActivity mainActivity = MainActivity.this;
                int i3 = MainActivity.A;
                Window window = mainActivity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.clearFlags(67108864);
                window.setStatusBarColor(mainActivity.getResources().getColor(R.color.purple_200));
                d.a r3 = mainActivity.r();
                Objects.requireNonNull(r3);
                r3.b(new ColorDrawable(mainActivity.getResources().getColor(R.color.teal_700)));
                mainActivity.f1754x.setImageResource(R.drawable.please);
                mainActivity.v.setTextColor(mainActivity.getResources().getColor(R.color.purple_200));
                mainActivity.v.setText("1%");
                mainActivity.f1753w.setText(d.d(mainActivity.f1755y));
                Toast.makeText(context, "Look me inside", 1).show();
                printStream = System.out;
                str = MainActivity.this.getInfo(d.d("XDR"));
            } else {
                printStream = System.out;
                str = "I am Strong, no one can defeat me";
            }
            printStream.println(str);
        }
```
We can clearly see that a new receiver is created in `onCreate` method. This receiver works with intent `android.intent.action.BATTERY_LOW`.To be able to run this receiver, we need to use the following adb command 

`adb shell am broadcast -a android.intent.action.BATTERY_LOW --es Is_on "yes"`

After running this command, the application will be as follows :

<img width="356" height="581" alt="image" src="https://github.com/user-attachments/assets/57183723-75f0-4927-9058-664e3a50c160" />

Since it said **Look me inside**, if we examine the `onReceive` method, we can see that a native function named `getInfo` is called.

After opening the libangler.so file in Ghidra, we can see the following code :

```c

void Java_com_example_angler_MainActivity_getInfo
               (long *param_1,undefined8 param_2,undefined8 param_3)

{
  char *pcVar1;
  long in_FS_OFFSET;
  byte local_38;
  undefined1 local_37 [15];
  undefined1 *local_28;
  long local_20;
  
  local_20 = *(long *)(in_FS_OFFSET + 0x28);
  pcVar1 = (char *)(**(code **)(*param_1 + 0x548))(param_1,param_3,0);
  illusion(pcVar1);
  ne((char *)&local_38);
  if ((local_38 & 1) == 0) {
    local_28 = local_37;
  }
  else {
    operator.delete(local_28);
  }
  (**(code **)(*param_1 + 0x538))(param_1,local_28);
  if (*(long *)(in_FS_OFFSET + 0x28) == local_20) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}
```
Inside the `getInfo` method, we can see two more methods named `illusion` and `ne`.


