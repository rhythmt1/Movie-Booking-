# My Personal Project

## Movie Ticket Booking

The purpose of this application is to make it easier for 
movie lovers to:

- Buy movie tickets online 
- Post ratings for the movies that they watch
- See which movies are worth watching by seeing the ratings

This application will be used by anyone who enjoys watching
movies at the cinema and wants a better experience when buying tickets
and deciding which movie to watch. This project interests me
because I am a big movie lover and a useful app that makes it
easy to buy tickets and find which movies
are good, makes the experience much more enjoyable.


## User Stories
- As a user, I want to be able to purchase a movie ticket
- As a user, I want to be able to add more tickets to my list of tickets
- As a user, I want to be able to view information from my ticket
- As a user, I want to be able to see how many tickets I have bought
- As a user, I want to be able to post a rating for a movie
- As a user, I want to be able to see a movie rating
- As a user, I want to be able to save my purchase information
- As a user, I want to be able to load my saved information

## Phase 4: Task 2
Ticket for Encanto added to Tickets on Fri Apr 01 16:02:59 PDT 2022

Ticket for Uncharted added to Tickets on Fri Apr 01 16:02:59 PDT 2022

Ticket for The Batman added to Tickets on Fri Apr 01 16:02:59 PDT 2022

Ticket for Uncharted added to Tickets on Fri Apr 01 16:02:59 PDT 2022

Ticket for Uncharted removed from Tickets on Fri Apr 01 16:03:03 PDT 2022
## Phase 4: Task 3
- If I had more time to refactor my design, I would reduce coupling because
many of my classes are associated with the same classes and are called individually.
For example, the MovieTheater class uses the Ticket and Movie classes, so it would
be a better design choice in my UI to get a ticket and movie using methods from
the field for the MovieTheater class, rather than making separate fields for Ticket
and Movie and calling them directly. 

- Another thing I would change is to reduce reduplication
of code and create methods for that code and call those methods where needed. For example,
I have 4 different booking methods for different movies in the MovieBookingApp class which are all similar.
It would be better design to have one method which could handle the booking for different movies.