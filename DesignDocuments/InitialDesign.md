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
   TriviaApp --> MainController : creates
   TriviaApp --> MainView : creates
   MainController --> ModelInterface : creates
   MainController <--> MainView : uses
   ModelInterface --> TriviaQuestions : contains
   ModelImplement --> NetUtils : uses
   ModelImplement --> FileUtils : uses
   ModelImplement --> ModelHelpers : uses
   ModelHelpers --> TriviaQuestions : processes
   ApiFetcher --> NetUtils : uses
   NetUtils --> TriviaEnums : uses
   ModelInterface <|-- ModelImplement
   MainView --> ViewHelpers : uses

   class TriviaApp {
      +main(String[] args) : void
   }

   class MainController {
      -Model model
      +getHelp() : String
      +parseArguments(String[] args) : void
      +run() : void
   }

   class ModelInterface {
      <<interface>>
   }
   
   class ModelImplement {

   }
   
   class ModelHelpers {
       
   }
   

```
