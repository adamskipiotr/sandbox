package com.pada.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
    @Autowired
    private ExampleService exampleService;

    @GetMapping("/all")
    public List<ExampleClass> getAllExamples(){
        return exampleService.getAllExamples();
    }


    @PostMapping
    public ExampleClass addNewExample(@RequestBody ExampleClass exampleClass){
      logger.info("Add new Example");
      ExampleClass exampleClass1 = exampleService.saveExample(exampleClass);
      return exampleClass1;
    }

    @GetMapping("/{name}")
    public ExampleClass getExampleByName(@PathVariable String name) {
        return exampleService.getExampleByName(name);
    }
}
