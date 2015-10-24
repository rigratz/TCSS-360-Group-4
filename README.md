# TCSS-360-Group-4
INstructions on how to use terminal.
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
