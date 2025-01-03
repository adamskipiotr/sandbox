package com.pada.sandbox.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    // without this mapping bookById returns null for Author
    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @SchemaMapping(typeName = "Query", field = "author")
    public Author getAuthorById(@Argument String id) {
        return Author.getById(id);
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument List<String> chapters, @Argument Integer pageCount) {
        return Book.addBook(name, chapters, pageCount);
    }

    @MutationMapping
    public Book createBasicBook(@Argument String name, @Argument List<String> chapters) {
        return Book.addBook(name, chapters, 100);
    }


    @MutationMapping
    public Book createBookFromInput(@Argument BookInput input) {
        return Book.addBook(input);
    }

}
