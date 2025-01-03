package com.pada.sandbox.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookControllerTests {

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
        Book expected = new Book("1","Test", List.of("1","2"), 245, null);

        Book book = tester.document(query)
                .execute()
                .path("data.createBook")
                .entity(Book.class)
                .get();

        assertNotNull(book);
        assertThat(book)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }
}