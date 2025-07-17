# Frida 0x1

 After decompiling the apk using jadx, lets get into the source code.

In the MainActivity, we can see the following code.

```java
public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        final int i = get_random();
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ad2001.frida0x1.MainActivity.1
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (TextUtils.isDigitsOnly(obj)) {
                    MainActivity.this.check(i, Integer.parseInt(obj));
                } else {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Enter a valid number !!", 1).show();
                }
            }
        });
    }
    int get_random() {
        return new Random().nextInt(100);
    }
    void check(int i, int i2) {
        if ((i * 2) + 4 == i2) {
            Toast.makeText(getApplicationContext(), "Yey you guessed it right", 1).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Try again", 1).show();
    }
}
```
We can clearly see the logic that the method 'get_random' randomnly return a number from 1-100 and the method 'check' check if its parameters (generated number and the input) satisfy the realation (num1 *2)+4 == num2

So we can either hook get_random method or check method to modify thier behaviour and control their return value

### Hooking `get_random()` :

By hooking get_random method, we can manipulate the value it generates. The JS script is as follows 

```javascript
Java.perform(function() {
  var activity = Java.use("com.ad2001.frida0x1.MainActivity");
  activity.get_random.implementation = function(){
    console.log("This method is hooked");
    var num = this.get_random();
    console.log("The return value is " + num);
    console.log("The value to bypass the check " + (num * 2 + 4 )) 
    return num; 
  }
})
```
 


