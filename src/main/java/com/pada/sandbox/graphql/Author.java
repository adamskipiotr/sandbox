package com.pada.sandbox.graphql;
import java.util.Arrays;
import java.util.List;

record Author(String id, String firstName, String lastName){

    private static List<Author> authors = Arrays.asList(
            new Author("A1", "Name", "Lastname"),
            new Author("A2", "Unknown", "Noname"),
            new Author("A3", "XYZ", "ABC")
    );

    public static Author getById(String id){
        return authors.stream().filter(author -> author.id().equals(id)).findFirst().orElse(null);
    }
}