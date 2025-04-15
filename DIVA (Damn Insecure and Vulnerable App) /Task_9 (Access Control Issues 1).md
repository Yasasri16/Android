# Task 9 : Access Control Issues - Part 1

In this challenge, titled `Access Control Issues - Part 1`, the objective is to access API credentials that are displayed within the app. The app contains a button labeled `VIEW API CREDENTIALS`. When clicked, it directs the user to another screen where the credentials are shown.

![Screenshot from 2025-04-15 23-12-55](https://github.com/user-attachments/assets/580f20d4-2084-4a40-8da2-0fde62bfecb9)
![Screenshot from 2025-04-15 23-13-01](https://github.com/user-attachments/assets/7b7b5ae6-916f-4b63-a3fe-dec0bf006b0e)


However, our objective is to access these credentials from outside the application.

![Screenshot from 2025-04-15 23-12-07](https://github.com/user-attachments/assets/c0fa2375-c5eb-4d14-bf5d-5355974b8b49)

Upon reviewing the source code, we see that clicking the button sends an implicit intent with the action `jakhar.aseem.diva.action.VIEW_CREDS`.

![Screenshot from 2025-04-15 23-16-51](https://github.com/user-attachments/assets/383de74c-8cd4-462c-a7db-554cc70ed120)

From the manifest, we can confirm that the `APICredsActivity` is registered to handle this intent through the intent-filter.

![Screenshot from 2025-04-15 23-18-46](https://github.com/user-attachments/assets/8e69f37c-667d-49d8-8cf9-8c5238f1c5de)

This intent is then handled by another activity, `APICredsActivity.class`, which is responsible for displaying the sensitive API credentials.

Since the `APICredsActivity` is exposed through an implicit intent, there’s no restriction on which app or user can trigger it. This creates a significant access control vulnerability.

We can simply use ADB (Android Debug Bridge) to initiate the activity from outside the app. 

The required command is `adb shell am start jakhar.aseem.diva/.APICredsActivity`.

The core issue here is that **the API credentials are accessible via an implicit intent that can be triggered by any app or external entity, allowing unauthorized access to sensitive information**. This exposes the credentials to potential interception by malicious actors.

The possible solution is **to switch to explicit intents**, where the target activity is clearly specified, ensuring that only the app itself can trigger the activity. Additionally, **implementing proper access control mechanisms**, such as authentication or permission checks, would ensure that only authorized users or apps can access sensitive data like the API credentials.
