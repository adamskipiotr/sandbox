package com.pada.sandbox;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class ExampleClass {

    @Id
    private String id;
    private String name;
    private String secondName;
    private Integer age;
    private ExampleEnum exampleEnum;
    private List<SecondClass> secondClassList;

}
