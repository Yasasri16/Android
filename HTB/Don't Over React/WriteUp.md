<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/7365ec60-595f-40f6-8552-42d32f36b558" />The description of the challenge is as follows :

**Some web developers wrote this fancy new app! It’s really cool, isn’t it?**

When we install the app on our emulator, we can see the following screen.

<img width="362" height="580" alt="image" src="https://github.com/user-attachments/assets/028d2e31-70fe-4847-9e11-79f7c9903b4c" />

We can find anything here. So lets decompile the apk using jadx.

In the `Main Activity`, we can find the following code snippet.

```java
public class MainActivity extends ReactActivity {
    @Override // com.facebook.react.ReactActivity
    protected String getMainComponentName() {
        return "AwesomeProject";
    }
}
```

We can see that the MainActivity is extending `ReactActivity` and this along with the challenge name and the chall description confirms that this is an application made with ReactNative. 

This suggests that we need to check something related to ReactNative.

We know that the compiled JS code is stored in the assets under the name `index.android.bundle`.

While lookin for the urls or api end points, we found an interesting url following by its debug message.

`apiUrl:'https://www.hackthebox.eu/',debug:'SFRCezIzbTQxbl9jNDFtXzRuZF9kMG43XzB2MzIyMzRjN30='`

The debug paramter includes a base64 encoded string.

Decoding that, successfully gave the flag.

<img width="794" height="42" alt="image" src="https://github.com/user-attachments/assets/44ed0010-6a20-40ce-bf0e-8af1c798548b" />





