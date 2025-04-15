# Task 7 : Input Validation Issues - Part 1

Seventh challange is named as `Input Validation Issues : Part 1`.

![Screenshot from 2025-04-15 18-54-35](https://github.com/user-attachments/assets/850be42e-ab95-498c-aed5-45f958c4a7ca)

The objective of this challenge is to access all the user data without knowing any username with a single search.

From the source code, we can see that the input is getting concatenated with the query which is vulnerable to SQL Injection. If the user enters `' or TRUE;`, the whole data will be returned.

When we enter `' or TRUE;`, the final query becomes 

`SELECT * FROM sqliuser WHERE user = '' or TRUE;'"`

This retrives the data either if the username is '' (NULL or Empty) or the condition is TRUE. Here, we gave the input as TRUE itself instead of any true condition, which is always true and hence all the data from the database can be retreived.

All rows in the sqliuser table are returned, regardless of what username the attacker gives.
