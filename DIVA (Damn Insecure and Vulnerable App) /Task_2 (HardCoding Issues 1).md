## Task 2 – Hardcoding Issues - Part 1

Now, let's move on to the second challenge, which is titled `**"HARDCODING ISSUES - PART 1"**`


From the source code (see: *Screenshot from 2025-04-15 15-51-44*), it's clear that the application checks whether the user input is equal to a hardcoded string called `vendorsecretkey`.

When we enter `vendorsecretkey` as input, the application grants access successfully.

The critical issue here is that **anyone who can view the source code can easily retrieve this hardcoded value and bypass the check**. Storing sensitive information like this directly in the source code as plain text is a serious security risk.

### 🔐 Best Practice

Sensitive data such as secret keys should never be hardcoded. Instead, it should be:

- Encrypted or obfuscated, if it absolutely needs to reside on the client side.
- Ideally, moved to a secure server and verified via an API call.

This ensures better protection against reverse engineering and unauthorized access.
