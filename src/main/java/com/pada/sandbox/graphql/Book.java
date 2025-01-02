package com.pada.sandbox.graphql;
import java.util.Arrays;
import java.util.List;

record Book(String id, String name, List<String> chapters, int pageCount, String authorId ){

    private static List<Book> books = Arrays.asList(
        new Book("1", "First book", List.of("Chapter 1", "Chapter 2"), 250, "A1"),
        new Book("2", "Other Book", List.of("Ch1", "Ch2"), 270, "A2"),
            new  Book("3", "The newest book", List.of("1st Chapter", "2nd Chapter"), 195, "A3")
    );


    public static Book getById(String id){
        return books.stream().filter(book -> book.id().equals(id)).findFirst().orElse(null);
    }
}