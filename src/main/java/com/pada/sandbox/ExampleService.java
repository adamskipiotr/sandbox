package com.pada.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Transactional
    public ExampleClass saveExample(ExampleClass e){
        e = exampleRepository.save(e);
        return e;
    }

    public List<ExampleClass> getAllExamples() {
       return exampleRepository.findAll();
    }

    public ExampleClass getExampleByName(String name){
        return exampleRepository.findByName(name);
    }
}
