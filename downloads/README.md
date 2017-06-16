# User Downloads
## About
A simple user downloads plugin that allows **_back-end_** users to upload files via the back-end and assign said files to specific **_front-end_** user(s).

## A quick use case
Given there are three (3) front-end users:
- A
- B
- C

Given one (1) back-end user:
- ADMIN

Given there is a file:
- test.txt

Case #1:
Back-end user 'ADMIN' uploads 'test.txt' via the User Downloads Plugin, and assigns the file to front-end user 'A' and front-end user 'B'. Front-end user 'A' logins to the dashboard and accesses the downloads component, the file 'test.txt' is visible to 'A', the same case is for front-end user 'B', however it is not visible to front-end user 'C'.