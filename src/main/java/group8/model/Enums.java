package group8.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A collection of enums representing various types, difficulties, categories,
 * and fields used in trivia questions.
 */
public class Enums {

    /**
     * Enum representing the type of a trivia question.
     */
    public enum QuestionType {

        @JsonProperty("boolean")
        BOOLEAN("boolean"),

        @JsonProperty("multiple")
        MULTIPLE("multiple");

        private final String value;

        /**
         * Constructor to initialize the string value for the question type.
         *
         * @param value The string value associated with the question type.
         */
        QuestionType(String value) {
            this.value = value;
        }

        /**
         * Gets the string value associated with the question type.
         *
         * @return The string value of the question type.
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string value to the corresponding QuestionType enum constant.
         *
         * @param value The string value to convert.
         * @return The corresponding QuestionType enum constant.
         * @throws IllegalArgumentException If the string value does not match any
         *                                  QuestionType.
         */
        public static QuestionType fromValue(String value) {
            for (QuestionType type : QuestionType.values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

    /**
     * Enum representing the difficulty level of a trivia question.
     */
    public enum Difficulty {

        @JsonProperty("easy")
        EASY("easy"),

        @JsonProperty("medium")
        MEDIUM("medium"),

        @JsonProperty("hard")
        HARD("hard");

        private final String value;

        /**
         * Constructor to initialize the string value for the difficulty level.
         *
         * @param value The string value associated with the difficulty level.
         */
        Difficulty(String value) {
            this.value = value;
        }

        /**
         * Gets the string value associated with the difficulty level.
         *
         * @return The string value of the difficulty level.
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string value to the corresponding Difficulty enum constant.
         *
         * @param value The string value to convert.
         * @return The corresponding Difficulty enum constant.
         * @throws IllegalArgumentException If the string value does not match any
         *                                  Difficulty.
         */
        public static Difficulty fromValue(String value) {
            for (Difficulty difficulty : Difficulty.values()) {
                if (difficulty.value.equals(value)) {
                    return difficulty;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

    /**
     * Enum representing various categories for trivia questions.
     */
    public enum Category {

        @JsonProperty("Animals")
        ANIMALS("Animals"),

        @JsonProperty("Art")
        ART("Art"),

        @JsonProperty("Celebrities")
        CELEBRITIES("Celebrities"),

        @JsonProperty("Entertainment: Board Games")
        ENTERTAINMENT_BOARD_GAMES("Entertainment: Board Games"),

        @JsonProperty("Entertainment: Books")
        ENTERTAINMENT_BOOKS("Entertainment: Books"),

        @JsonProperty("Entertainment: Cartoon & Animations")
        ENTERTAINMENT_CARTOON_ANIMATIONS("Entertainment: Cartoon & Animations"),

        @JsonProperty("Entertainment: Comics")
        ENTERTAINMENT_COMICS("Entertainment: Comics"),

        @JsonProperty("Entertainment: Film")
        ENTERTAINMENT_FILM("Entertainment: Film"),

        @JsonProperty("Entertainment: Japanese Anime & Manga")
        ENTERTAINMENT_JAPANESE_ANIME_MANGA("Entertainment: Japanese Anime & Manga"),

        @JsonProperty("Entertainment: Music")
        ENTERTAINMENT_MUSIC("Entertainment: Music"),

        @JsonProperty("Entertainment: Musicals & Theatres")
        ENTERTAINMENT_MUSICALS_THEATRES("Entertainment: Musicals & Theatres"),

        @JsonProperty("Entertainment: Television")
        ENTERTAINMENT_TELEVISION("Entertainment: Television"),

        @JsonProperty("Entertainment: Video Games")
        ENTERTAINMENT_VIDEO_GAMES("Entertainment: Video Games"),

        @JsonProperty("General Knowledge")
        GENERAL_KNOWLEDGE("General Knowledge"),

        @JsonProperty("Geography")
        GEOGRAPHY("Geography"),

        @JsonProperty("History")
        HISTORY("History"),

        @JsonProperty("Mythology")
        MYTHOLOGY("Mythology"),

        @JsonProperty("Politics")
        POLITICS("Politics"),

        @JsonProperty("Science & Nature")
        SCIENCE_NATURE("Science & Nature"),

        @JsonProperty("Science: Computers")
        SCIENCE_COMPUTERS("Science: Computers"),

        @JsonProperty("Science: Gadgets")
        SCIENCE_GADGETS("Science: Gadgets"),

        @JsonProperty("Science: Mathematics")
        SCIENCE_MATHEMATICS("Science: Mathematics"),

        @JsonProperty("Sports")
        SPORTS("Sports"),

        @JsonProperty("Vehicles")
        VEHICLES("Vehicles");

        private final String value;

        /**
         * Constructor to initialize the string value for the category.
         *
         * @param value The string value associated with the category.
         */
        Category(String value) {
            this.value = value;
        }

        /**
         * Gets the string value associated with the category.
         *
         * @return The string value of the category.
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string value to the corresponding Category enum constant.
         *
         * @param value The string value to convert.
         * @return The corresponding Category enum constant.
         * @throws IllegalArgumentException If the string value does not match any
         *                                  Category.
         */
        public static Category fromValue(String value) {
            for (Category category : Category.values()) {
                if (category.value.equals(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

    /**
     * Enum representing different fields used for sorting or filtering trivia
     * questions.
     */
    public enum Field {

        TYPE("type"),
        DIFFICULTY("difficulty"),
        CATEGORY("category");

        private final String value;

        /**
         * Constructor to initialize the string value for the field.
         *
         * @param value The string value associated with the field.
         */
        Field(String value) {
            this.value = value;
        }

        /**
         * Gets the string value associated with the field.
         *
         * @return The string value of the field.
         */
        public String getValue() {
            return value;
        }

        /**
         * Converts a string value to the corresponding Field enum constant.
         *
         * @param value The string value to convert.
         * @return The corresponding Field enum constant.
         * @throws IllegalArgumentException If the string value does not match any
         *                                  Field.
         */
        public static Field fromValue(String value) {
            for (Field field : Field.values()) {
                if (field.value.equals(value)) {
                    return field;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}