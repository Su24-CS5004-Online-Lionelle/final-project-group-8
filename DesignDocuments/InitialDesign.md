# Mock Directory Structure

## Project Root
- **Group8**
  - TriviaApp.java (main())
  - **Controller/**
    - MainController.java
    - ControllerHelper.java
  - **Model/**
    - ModelInterface.java
    - ModelImplement.java
    - ApiFetcher.java
    - ModelHelpers.java
    - TriviaEnums
    - **Utils/**
      - FileUtils.java
      - NetUtils.java
  - **View/**
    - MainView.java
    - ViewHelpers.java


# Mock UML Diagram

```mermaid
---
title: TriviaApp Initial Design
---
classDiagram
   direction LR
   TriviaApp --> IMainController : creates
   IMainController <|-- MainController : implements
   TriviaApp --> IMainView : creates
   IMainView <|-- MainView : implements
   TriviaApp --> IMainModel : creates
   IMainModel <|-- MainModel : implements

   MainController <--> MainModel : uses
   MainView <--> MainController : uses
   MainView --> ViewHelpers : uses
   MainModel --> APIFetcher : uses
   MainModel --> Filtering : uses
   MainModel --> Sorting : uses
   MainController --> ControllerHelpers : uses

   IMainModel --> TriviaQuestion : contains
   MainModel --> ModelHelpers : uses

   class TriviaApp {
      + main(String[] args) : void
   }

   class IMainController {
      <<interface>>
   }

   class MainController {
      - IMainModel : model
      + getHelp() : String
      + parseArguments(String[] args) : void
      + run() : void
   }

   class IMainModel {
      <<interface>>
   }

   class MainModel {
      - mainCollection : ?
      - userCollection : Set<TriviaQuestion>
      + saveQuestion(TriviaQuestion question)
      + removeQuestion(TriviaQuestion question)
      + loadUserCollection(InputStream in)
      + saveUserCollection(OutputStream out)
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

   }

   class APIFetcher {

   }
   
   class TriviaAttrEnum {

   }

   class Filtering {

   }

   class Sorting {

   }

```
