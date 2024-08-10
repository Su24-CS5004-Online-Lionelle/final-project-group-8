# Mock Directory Structure

## Project Root
- **Group8**
  - TriviaApp.java (main())
  - **Controller/**
    - IMainController.java
    - MainController.java
    - ControllerHelpers.java
  - **Model/**
    - ITriviaCollection.java
    - TriviaCollection.java
    - APITriviaCollection.java
    - UserTriviaCollection.java
    - Filtering.java
    - Sorting.java
    - ModelHelpers.java
    - **Utils/**
  - **View/**
    - IMainView.java
    - MainView.java
    - ViewHelpers.java


# Final UML Diagram

```mermaid
---
title: TriviaApp Final Design
---
classDiagram
   direction LR
   
%%   TriviaApp ..> MainController : creates
%%   TriviaApp ..> MainView : creates
%%   MainView --> MainController : has

%%  MainController ..|> IMainController
%%  MainController o-- ITriviaCollection : user
%%  MainController o-- ITriviaCollection : api
%%  MainController o-- QuestionExchange
%%  MainController ..> APIUtils : uses
%%  MainController ..> FileUtilities : uses
%%  MainController ..> TriviaQuestion : uses
%%  UserTriviaCollection ..|> ITriviaCollection
%%  APITriviaCollection ..|> ITriviaCollection


%%  TriviaCollection ..|> ITriviaCollection
%%  TriviaCollection o-- TriviaQuestion
%%  TriviaCollection ..> Filters : uses
%%  TriviaCollection ..> Field : uses
%%  TriviaCollection ..> Sorter : uses

%%  ITriviaCollection ..> TriviaQuestion : uses
%%  ITriviaCollection ..> Filters : uses
%%  ITriviaCollection ..> Field : uses

%%  TriviaCollection <|-- UserTriviaCollection : extends
%%  UserTriviaCollection o-- TriviaQuestion : contains
%%  TriviaCollection o-- TriviaQuestion : contains

%%  TriviaQuestion ..|> Comparable~TriviaQuestion~ : implements
%%  TriviaQuestion --> QuestionType : uses
%%  TriviaQuestion --> Difficulty : uses
%%  TriviaQuestion --> Category : uses

%%  FileUtilities ..> ObjectMapper : uses
%%  FileUtilities ..> TriviaQuestion : uses

%%  TriviaCollection <|-- APITriviaCollection : extends
%%  APITriviaCollection o-- TriviaQuestion : contains
%%  APITriviaCollection ..> Enums.Category : uses

%%  MainView --|> JFrame
%%  MainView o-- MainController
%%  MainView o-- MainViewState
%%  MainView o-- TriviaQuestion
%%  MainView ..> Enums.Category

   Enums --> QuestionType : contains
   Enums --> Difficulty : contains
   Enums --> Category : contains
   Enums --> Field : contains
    
   TriviaCollection --> Filtering
   TriviaCollection --> Sorting
   APITriviaCollection  --|> TriviaCollection : extends
   UserTriviaCollection --|> TriviaCollection : extends
   ITriviaCollection <|-- TriviaCollection : implements

   MainController --> ITriviaCollection : uses

   MainView <--> MainController : uses
   MainView --> ViewHelpers : uses
   ModelHelpers --> APIFetcher : uses
   MainController --> ControllerHelpers : uses

   TriviaCollection --> TriviaQuestion : contains

  TriviaApp --> ITriviaCollection : creates UserTriviaCollection
  TriviaApp --> ITriviaCollection : creates APITriviaCollection
  TriviaApp --> IMainView : creates
  TriviaApp --> IMainController : creates

   class TriviaApp {
      + main(String[] args) : void
   }

   class MainController {
      - user : ITriviaCollection
      - api : ITriviaCollection
      - questionExchange : QuestionExchange
      
     + generateApiList(List~Category~ selectedCategories) : void
     + getAllCategories() : Set~Category~
     + getFormattedApiQuestions(Set~QuestionType~ typeFilters, Set~Difficulty~ difficultyFilters, Set~Category~ categoryFilters) : List~TriviaQuestion~
     + getFormattedApiQuestions() : List~TriviaQuestion~
     + getFormattedUserQuestions(Field field, Boolean sortOrder) : List~TriviaQuestion~
     + loadTriviaQuestions(String filePath) : List~TriviaQuestion~
     - isDuplicateQuestion(TriviaQuestion newQuestion) : boolean
     + saveTrivia(String folderPath) : void
     + moveToUserCollection(TriviaQuestion question) : void
     + moveToApiCollection(TriviaQuestion question) : void
     + getApiQuestions() : List~TriviaQuestion~
     + getUserQuestions() : List~TriviaQuestion~
   }

   class ITriviaCollection {
      <<interface>>
     +getAllQuestions() : Set~TriviaQuestion~
     +addQuestion(TriviaQuestion question) : void
     +addQuestions(Collection~TriviaQuestion~ questions) : void
     +removeQuestion(TriviaQuestion question) : void
     +filterQuestions(Filters filter) : Set~TriviaQuestion~
     +sortQuestions(Field field, boolean ascending) : List~TriviaQuestion~
     +reset() : void
   }

   class TriviaCollection {
     <<abstract>>
     + originalCollection : #Set~TriviaQuestion~ 

     + getAllQuestions() : Set~TriviaQuestion~
     + addQuestion(TriviaQuestion question) : void
     + removeQuestion(TriviaQuestion question) : void
     + filterQuestions(Filters filters) : Set~TriviaQuestion~
     + sortQuestions(Field field, boolean ascending) : List~TriviaQuestion~
     + reset() : void
     + contains(TriviaQuestion question) : boolean
   }

   class UserTriviaCollection { 
     - userCollection : Set~TriviaQuestion~

     + addQuestions(Collection~TriviaQuestion~ questions) : void
   }

  class APITriviaCollection {
    - apiCollection : Set~TriviaQuestion~

    + APITriviaCollection(Collection~TriviaQuestion~ questions) : void
    + addQuestions(Collection~TriviaQuestion~ questions) : void
    + getAllCategories() : Set~Enums.Category~
  }

  class TriviaQuestion {
    <<record>>
    + type : QuestionType
    + difficulty : Difficulty
    + category : Difficulty
    + question : String
    + correctAnswer : String
    + incorrectAnswers : List~String~
    
    + toString() : String
    + compareTo(TriviaQuestion other) : int
  }

  class FileUtilities {
    <<utility>>
    - mapper$ : ObjectMapper 
    - QUESTION_HEADER$ : String
    - ANSWER_HEADER$ : String
    - JSON_FILE$ : String
    - QUESTION_FILE$ : String
    - ANSWER_FILE$ : String

    + saveTrivia(Collection~TriviaQuestion~ questions, String folderName)$ : void
    + loadTrivia(String jsonFilePath)$ : List~TriviaQuestion~
    - saveAsJson(Collection~TriviaQuestion~ questions, String filePath)$ : void
    - saveAsText(Collection~TriviaQuestion~ questions, String questionsFilePath, String answersFilePath)$ : void
  }

  class ObjectMapper {
    <<external>>
  }
  
  class Enums {
    <<static>>
  }

  class QuestionType {
    <<enumeration>>
    BOOLEAN
    MULTIPLE
    - value : String 
    
    + getValue() : String
    + fromValue(String value)$ : QuestionType
  }

  class Difficulty {
    <<enumeration>>
    EASY
    MEDIUM
    HARD
    - value : String
    + getValue() : String
    + fromValue(String value)$ : Difficulty
  }

  class Category {
    <<enumeration>>
    ANIMALS
    ART
    CELEBRITIES
    ENTERTAINMENT_BOARD_GAMES
    ENTERTAINMENT_BOOKS
    ENTERTAINMENT_CARTOON_ANIMATIONS
    ENTERTAINMENT_COMICS
    ENTERTAINMENT_FILM
    ENTERTAINMENT_JAPANESE_ANIME_MANGA
    ENTERTAINMENT_MUSIC
    ENTERTAINMENT_MUSICALS_THEATRES
    ENTERTAINMENT_TELEVISION
    ENTERTAINMENT_VIDEO_GAMES
    GENERAL_KNOWLEDGE
    GEOGRAPHY
    HISTORY
    MYTHOLOGY
    POLITICS
    SCIENCE_NATURE
    SCIENCE_COMPUTERS
    SCIENCE_GADGETS
    SCIENCE_MATHEMATICS
    SPORTS
    VEHICLES
    
    - value : String
    + getValue() : String
    + fromValue(String value)$ : Category
  }

  class Field {
    <<enumeration>>
    TYPE
    DIFFICULTY
    CATEGORY
    
    - value : String 
    + getValue() : String
    + fromValue(String value)$ : Field
  }

  class MainView {
    - frame : JFrame
    - apiList : JList~TriviaQuestion~
    - userList : JList~TriviaQuestion~
    - apiListModel : DefaultListModel~TriviaQuestion~
    - userListModel : DefaultListModel~TriviaQuestion~
    - toUserButton : JButton
    - toApiButton : JButton
    - state : MainViewState
    - selectedCategories : List~Enums.Category~
    - controller : MainController
    - createLeftPanel() : JPanel
    - createRightPanel() : JPanel
    - createCenterPanel() : JPanel
    - createApiTopPanel() : JPanel
    - createApiBottomPanel() : JPanel
    - createUserTopPanel() : JPanel
    - createUserBottomPanel() : JPanel
    - createIconButton(String path, int width, int height) : JButton
    + updateApiListModel(List~TriviaQuestion~ questions) : void
    + updateUserListModel(List~TriviaQuestion~ questions) : void
    + getCheckboxState() : MainViewState
    + getFrame() : JFrame
    + updateButtons() : void
    - showCategorySelection() : void
  }

  class JFrame {
    <<external>>
  }
  
  
```
