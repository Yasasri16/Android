package com.example.truthordaregame;

public class Model {
    private String id;
    private String type;
    private String rating;
    private String question;

    public Model(String id, String type, String rating, String question) {
        this.id = id;
        this.type = type;
        this.rating = rating;
        this.question = question;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
}

