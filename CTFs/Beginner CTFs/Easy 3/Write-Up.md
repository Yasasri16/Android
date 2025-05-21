# easy3.apk

When you open the app, you will see a text saying something. There is no clue to find the flag. 

Then I used jadx to view the source code knowing there will be more activities.

In the SecondActivity, there is a text saying that some part of the flag is here.

Then I opened it's xml file. Under that text, there is a textview named part2 which is in strings.xml file, which is "0f_4ndro1d_r3v?"

This is the second part of the flag.

Then I started exploring the manifest file, layout files.

In activity_main.xml file, I found a text "3ver_h3ard_". This along with the second part makes a meaningful string.

From this app, we will get a meaningful string and not flag.
