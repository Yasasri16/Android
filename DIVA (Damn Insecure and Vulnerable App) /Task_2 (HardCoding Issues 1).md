# Task 2

Now, let's start with the second challenge which is named as `HARDCODING ISSUES - PART 1`

![Screenshot from 2025-04-15 15-51-44](https://github.com/user-attachments/assets/9472f5e0-4d25-42ed-90a0-42fa90fbea24)

Here, it is very clear that the input should be equal to `vendorsecretkey` to get access.

Here is the part of code which validates the input to be `vendorsecretkey`. 

`if (hckey.getText().toString().equals("vendorsecretkey")) {
            Toast.makeText(this, "Access granted! See you on the other side :)", 0).show();
        } else {
            Toast.makeText(this, "Access denied! See you in hell :D", 0).show();
        }
`
When we enter `vendorsecretkey` as input, it successfully gives us access.

In this challenge, the critical issue is that, anyone who can view this source code can get the access. Therefore, storing the sensitive data in the form of plain text in the source code is a problem too. The best solution is to encrypt sensitive information like this. This is for security reasons.


