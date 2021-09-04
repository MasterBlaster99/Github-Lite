package com.example.githublite;

public class Repo {
    private String name;
    private String description;
    private String numStars;
    private String language;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNumStars() {
        return numStars;
    }

    public String getLanguage() {
        return language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumStars(String numStars) {
        this.numStars = numStars;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
