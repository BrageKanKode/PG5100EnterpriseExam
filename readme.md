##Intro
This has been a fun project to work on, and i've learned so much from making this. It's the coding language i'm
most comfertable in using, which has made it possible to complete all the features requested and more. More on this later



##About the game
The game bases on a game called Star Citizen (ships and stuff), and you can collect a card
of a ship. On the start page you can see an overview of all the ships available, and information about them.
When you sign up or log in, you can go to your collection, where you initially start with 100 in game currency
and 3 lootboxes. 

You can open these lootboxes and buy new ones with in game currency, and even sell the ships to get more currency.

I chose to start the collection table with a null item, to show the table, instead of a message that there isn't any
ships there yet.


##Requirements checked
I have done R1, R2, R3, R4 and R5

##Coverage
backend: 88%

frontend: 92%

Total: 89%

##Extra features
The extra feature consists of a search feature at the home page, with the ability to search by Value of the card, 
or a ship name. There is not currently an errormessage if the search doesn't find anything, but i thought that was ok. 
No result is just as good as some text telling you there is no result.



##How to run the application
You can run the application by running LocalApplicationRunner

You can run the selenium tests by running SeleniumLocalIt

You can run the backend tests by running java test folder in backend

##Errors/bad behavior
There is one flaky test on testMillItem in the frontend, where it sometimes adds a ship with no values, thus not adding to 
the table. I just figured out this 04:00 on friday, so i won't be able to 
examine this any more before the deadline.