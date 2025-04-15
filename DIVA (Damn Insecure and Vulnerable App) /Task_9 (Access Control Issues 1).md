# Task 9 : Access Control Issues - Part 1

This challenge is named `Access Control Issues : Part 1`.

When we open this activity in the app, we can see the button called `VIEW API CREDENTIALS` and when we click it, we will be directed to somewhere and the Credentials are displayed there.

![Screenshot from 2025-04-15 23-12-55](https://github.com/user-attachments/assets/580f20d4-2084-4a40-8da2-0fde62bfecb9)
![Screenshot from 2025-04-15 23-13-01](https://github.com/user-attachments/assets/7b7b5ae6-916f-4b63-a3fe-dec0bf006b0e)


However, our objective is to access these credentials from outside the application.

![Screenshot from 2025-04-15 23-12-07](https://github.com/user-attachments/assets/c0fa2375-c5eb-4d14-bf5d-5355974b8b49)

From the source code, we can see that this activity leads to `jakhar.aseem.diva.action.VIEW_CREDS` when you click the button.

![Screenshot from 2025-04-15 23-16-51](https://github.com/user-attachments/assets/383de74c-8cd4-462c-a7db-554cc70ed120)

From the manifest file, we can see that this leads to another activity `APICredsActivity.class`. 

![Screenshot from 2025-04-15 23-18-46](https://github.com/user-attachments/assets/8e69f37c-667d-49d8-8cf9-8c5238f1c5de)

In this activity, we can see the details we saw earlier.

Since our objective is to access these credentials and since this activity has an intent filler, we can just simply start this activity using adb commands.

