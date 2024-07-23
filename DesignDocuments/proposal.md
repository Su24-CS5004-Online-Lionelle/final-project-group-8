### What are you building?

Our team is proposing to build an application that allows a user to manage a collection of trivia questions. Upon entry, the user will be displayed two panels. The panel on the left will display the programs main collection of trivia questions (offers filter, filter-reset functionality); the panel on the right will display the current subset of trivia questions that the users selects from the main collection; this subset will be sortable / exportable (i.e., pretty). Questions can be moved between panels by putting (singular / multiple) questions into focus, selecting the appropriate arrow, of which will trigger the directional exchange of the selected questions. The main collection can be generated / re-generated (randomized) using API calls.

### What are the initial features for the application?

- Making API requests, to populate randomized trivia questions within the program's main collection pane. This should support multiple requests, in the event that the user wants to view an entirely new set of questions to choose from. NOTE: session token needs to be reset if all questions are retrieved in a single question. Default will be 100 questions per request.

- Handling the API requests, including processing the trivia questions, storing metadata, etc. (this is important for supporting sorting / filtering logic).

- Building the GUI; supports main/user collection views, buttons, etc.

- Exporting user collection to pretty-print formatted file.

## What are the minimum additional features you plan to implement?

- Sorting user collection.

- Filtering main collection (category, difficulty level, question type)

- Loading previously outputted user collections.

## What are your stretch goals (features beyond the minimum)?

- Interactive trivia player.

Go over your initial design.
Special emphasis should be placed on how you plan to break it up
MVC, presenter, file management, different input validation, testing, documentation, etc.
How do you plan to break up the work?
What is your teams timeline and major check-in points?