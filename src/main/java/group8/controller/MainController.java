package group8.controller;

import group8.model.ITriviaCollection;
import group8.model.helpers.APIUtils;

public class MainController {

    private ITriviaCollection user;
    private ITriviaCollection api;

    public MainController(ITriviaCollection userCollection, ITriviaCollection apiCollection) throws Exception {

        this.user = userCollection;
        this.api = apiCollection;

        try {
            APIUtils.requestToken();
            APIUtils.requestCategories();
        } catch (Exception e) {
            e.getCause();
        }

    }

    //     Generate API List

    public void GenerateAPIList() throws Exception {}


// ````````````Listeners`````````
//     Generate API List
//     Load
//     Save
//     Sort
//     Filter
//     MoveToUser
//     MoveToApi
//     Reset Filter



}
