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


# Mock UML Diagram

```mermaid
---
title: TriviaApp Initial Design
---
classDiagram
   direction LR

   TriviaApp --> ITriviaCollection : creates UserTriviaCollection
   TriviaApp --> ITriviaCollection : creates APITriviaCollection
   TriviaApp --> IMainView : creates
   TriviaApp --> IMainController : creates



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

   IMainController <|-- MainController : implements
   IMainView <|-- MainView : implements

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

   class TriviaApp {
      + main(String[] args) : void
   }

   class IMainController {
      <<interface>>
     + generateApiList(List~Category~ selectedCategories) : void
     + getAllCategories() Set~Category~
     + getFormattedApiQuestions(Set~QuestionType~ typeFilters, Set~Difficulty~ difficultyFilters, Set~Category~ categoryFilters) : List~TriviaQuestion~
     + getFormattedApiQuestions() : List~TriviaQuestion~
     + getFormattedUserQuestions(Field field, Boolean sortOrder) : List~TriviaQuestion~
     + loadTriviaQuestions(String filePath) : List~TriviaQuestion~
     + saveTrivia(String folderPath) : void
     + moveToUserCollection(TriviaQuestion question) : void
     + moveToApiCollection(TriviaQuestion question) : void
     + getApiQuestions() : List~TriviaQuestion~
     + getUserQuestions() : List~TriviaQuestion~
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
     originalCollection : #Set~TriviaQuestion~ 

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

       +addQuestions(Collection~TriviaQuestion~ questions) : void
   }


  class TriviaQuestion {
    <<record>>

  }



   class APITriviaCollection {
      - collection : Set<TriviaQuestion>
      + setAPICollection() : void
      + callAPICollection() : Set<TriviaQuestion>
      + callAPI(Enum, int) : Set<TriviaQuestion>
   }



   class Filter {

   }

   class Sort {

   }

   class ModelHelpers {
       
   }

   class IMainView{
      <<interface>>
   }

   class MainView {
      - IMainController : controller
   }

   class ViewHelpers {
       
   }

```
