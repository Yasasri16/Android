# Task 2 – Hardcoding Issues - Part 1

Now, let's move on to the second challenge, which is titled `HARDCODING ISSUES - PART 1`.
 
 ![Screenshot from 2025-04-15 15-51-44](https://github.com/user-attachments/assets/72deef70-4c04-40ba-a3a9-d099369ec7a8)

By observing the source code, it's clear that the application checks whether the user input is equal to a hardcoded string called `vendorsecretkey`.

When we enter `vendorsecretkey` as input, the application grants access successfully.

The critical issue here is that **anyone who can view the source code can easily retrieve this hardcoded value and bypass the check**. 

Storing sensitive information like this directly in the source code as plain text is a serious security risk. Sensitive data such as secret keys should never be hardcoded. Instead, it should be either encrypted or obfuscated. Ideally, moved to a secure server and verified via an API call. 

This ensures better protection against reverse engineering and unauthorized access.
