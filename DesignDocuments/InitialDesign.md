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
   }

   class MainController {
      - user : ITriviaCollection
      - api : ITriviaCollection
      + getHelp() : String
      + parseArguments(String[] args) : void
      + run() : void
   }

   class UserTriviaCollection {
      - collection : Set<TriviaQuestion>
      + saveQuestion(TriviaQuestion question)
      + removeQuestion(TriviaQuestion question)
      + loadUserCollection(InputStream in)
      + saveUserCollection(OutputStream out)
   }

   class APITriviaCollection {
      - collection : Set<TriviaQuestion>
   }

   class ITriviaCollection {
      <<interface>>

   }

   class TriviaCollection {

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
   
   class TriviaQuestion {
      <<record>>

   }

   class Filtering {

   }

   class Sorting {

   }

```
