# Intent Redirection Vulnerability

**Code Snippet** which causes this vulnerability is shown below :

```java
Intent extraIntent = getIntent().getParcelableExtra("extra_intent");
if (extraIntent != null) {
    startActivity(extraIntent);
    finish();
}
```

**Vulnerability Explanation :**

This code takes an Intent (extraIntent) passed by another app or activity, and immediately launches it without validation. If this activity is exported or accessible externally , a malicious app can send a harmful extra_intent.

Hence, the attacker controls what gets launched and your app becomes a bridge to start malicious or spoofed activities.

**Fix :**

To fix this vulnerability, you should always validate that the intent you're about to launch points to a component within your own app, ensuring it’s not controlled by an external or malicious source. Additionally, if the activity handling this logic doesn’t need to be launched from outside the app, it should be marked as exported="false" in the manifest to prevent external apps from accessing it. 
