# Insecure Logging

`LoginActivity` contains the following code.

```java
Log.d("userName", username);
Log.d("password", password);
```

**Vulnerability Explanation :**

In the given code, the app logs the username and password using Log.d(). This is dangerous because:

- Logs are not private – Other apps (especially on rooted devices) can read log data.

- Passwords should never be stored or displayed – Logging them increases the risk of credential leaks.

- If a hacker gets access to log files, they can easily steal usernames and passwords.

