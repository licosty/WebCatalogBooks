package com.webcatalogbooks.controller;

public enum BookOrder {
    ID("id"),
    AUTHOR("author"),
    TITLE("title"),
    YEAR("year"),
    PUBLISHER("publisher"),
    ISBN("isbn"),
    PAGES("pages");

    private String fieldName;

    BookOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
