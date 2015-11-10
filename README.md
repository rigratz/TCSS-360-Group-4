# TCSS-360-Group-4


Last commit didn't work... so this is a new calendar
Added new features:
-	Now we support better rest hours for the auction crew 
	by checking every single day and hour to make sure there 
	is no overlaps.
-	Now calendar accepts a list of auctions to add to the calendar.
-	Now we can search the auction for its name by sendin a name of the 	auction (Useless).
-	Now we do not allow similar non-profit organizations to host more that 	one auction per year.




Before doing something, pull the repository.
Before submitting, make a new branch with your new staff.
Then ASK other members to approve it.

Instructions on how to use terminal.
I used Sublime Text for programming while doing this. very good text editor. download it.

git clone https://github.com/rigratz/TCSS-360-Group-4.git
git status
git add filename.exe
git commit -m “my new message”
git push


git pull

git commit? -> Esc -> :wq

clear window -> ctrl + l (lower case L)


BRANCHING
git branch
git branch BRANCHNAME <- create a copy of a master branch
git checkout BRANCHNAME <- to switch to that branch

***do some changes***
switch to master branch
check if master was not changed for the last period of time
-> git pull 
if changes were made -> go back to BRANCHNAME and merge everything to the BRANCHNAME branch by -> git merge master

git add -A <- add all
git commit
git push


to delete the whole repository -> git rm -r -- fileName

To merge the branch -> compare, review, create a pull request
Its on the website, looks likea sync button
-> Create a Pull request
-> Leave a note
!!!******* NEVER MERGE ANYTHING BY YOURSELF, SOMEONE IS HAVE TO MERGE IT FOR YOU ****!!!!!!

-> Merge Pull Request
