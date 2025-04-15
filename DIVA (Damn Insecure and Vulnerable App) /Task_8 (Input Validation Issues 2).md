# Task 8 : Input Validation Issues : Part 2 

Now, the eighth challenge is `Input Validation Issues : Part 2`.

![Screenshot from 2025-04-15 22-41-33](https://github.com/user-attachments/assets/b840a352-6185-43c6-ab30-f32daa0e0562)

The main objective here is to access the sensitive information apart from Web URLs.

The critical issue here is **the lack of proper input validation, which allows untrusted data to be processed directly**. This can lead to serious vulnerabilities such as XSS, open redirects, or even code execution. 

The possible solution is to implement strict input validation and sanitization, ensure server-side checks, and avoid loading or executing user-provided input without verification.

Now, let's try to demonstrate the vulnerability. 

Let's try to access any sensitive information from the files from the previous challenges or from Shared Preferences.





![Screenshot from 2025-04-15 22-58-57](https://github.com/user-attachments/assets/ec7a8145-3de0-4c79-b26a-a1c22342c0e9)  
      ![Screenshot from 2025-04-15 22-59-56](https://github.com/user-attachments/assets/7c9a5a86-8a8b-4f05-93b7-3ba6c391c2ed)
