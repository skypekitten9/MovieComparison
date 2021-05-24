package com.example.ass3;

import java.util.List;

public class TemplateResponse {
    private String type;
    Value value;

    public Value getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

class Value
{
    private int id;
    private String joke;
    List<Object> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public String getJoke() {
        return joke;
    }
}
