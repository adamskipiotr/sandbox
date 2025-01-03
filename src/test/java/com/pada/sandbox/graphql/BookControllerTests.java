package com.pada.sandbox.graphql;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookControllerTest {

    @Autowired
    private GraphQlTester tester;

    @Test
    public void createBook() {
        String query = """
                mutation addBook {
                  createBook(name: "Test", chapters: ["1", "2"], pageCount: 245) {
                    id
                    name
                    chapters
                    pageCount
                  }
                }
                """;
        Book book = tester.document(query)
                .execute()
                .path("data.createBook")
                .entity(Book.class)
                .get();

        assertNotNull(book);
    }
}