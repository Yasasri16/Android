# Task 7 : Input Validation Issues - Part 1

Seventh challange is named as `Input Validation Issues : Part 1`.

![Screenshot from 2025-04-15 18-54-35](https://github.com/user-attachments/assets/850be42e-ab95-498c-aed5-45f958c4a7ca)

The objective of this challenge is to access all the user data without knowing any username with a single search.

From the source code, we can see that **the input is getting concatenated with the query** which is vulnerable to SQL Injection. If the user enters `' or TRUE;`, the whole data will be returned.

When we enter `' or TRUE;`, the final query becomes `SELECT * FROM sqliuser WHERE user = '' or TRUE;'"`

This retrives the data either if the username is ' ' (NULL or Empty) or the condition is TRUE. Here, we gave the input as TRUE itself instead of any true condition, which is always true and hence all the user data from the database can be retreived.

![Screenshot from 2025-04-15 19-51-16](https://github.com/user-attachments/assets/b051c279-93a7-4e46-9de7-8170e3445dbe)

Hence, we got all the user data without even knowing any username.

To prevent SQL injection and protect user privacy, user input should never be directly added into SQL queries. Instead, parameterized queries must be used, which separate SQL logic from user data. In Android, methods like rawQuery() or query() with placeholders (?) ensure inputs are safely handled. This prevents attackers from injecting malicious SQL to access or manipulate private data. Input validation can add extra security by restricting allowed characters. Using these practices is essential to keep user data safe and maintain the integrity of the application.
