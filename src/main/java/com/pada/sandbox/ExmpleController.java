package com.pada.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExmpleController {

    private static final Logger logger = LoggerFactory.getLogger(ExmpleController.class);
    @Autowired
    private ExampleService exampleService;

    @PostMapping("/add-example")
    public ExampleClass addNewExample(@RequestBody ExampleClass exampleClass){
      logger.info("Add new Example");
      ExampleClass exampleClass1 = exampleService.saveExample(exampleClass);
      return exampleClass1;
    }
}
