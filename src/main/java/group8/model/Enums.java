package group8.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enums {
    public enum QuestionType {
        @JsonProperty("multiple")
        MULTIPLE,
        @JsonProperty("boolean")
        BOOLEAN
    }

    public enum Difficulty {
        @JsonProperty("easy")
        EASY,
        @JsonProperty("medium")
        MEDIUM,
        @JsonProperty("hard")
        HARD
    }

    public enum Category {
        @JsonProperty("sports")
        SPORTS,
        @JsonProperty("art")
        ART,
        @JsonProperty("celebrities")
        CELEBRITIES,
        @JsonProperty("history")
        HISTORY,
        @JsonProperty("geology")
        GEOLOGY
    }

    public enum Field {
        TYPE,
        DIFFICULTY,
        CATEGORY
    }
}
