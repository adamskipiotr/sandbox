package com.pada.sandbox.graph;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/by-name-with-friends")
    public Person getByName(@RequestBody String name) {
        return personService.findByName(name);
    }

    @GetMapping("/by-name-with-friends-since")
    public Person getByName(@RequestBody FindPersonWithSinceRequest findPersonWithSinceRequest) {
        return personService.findWithFriendsSince(findPersonWithSinceRequest);
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        personService.createPersonWithFriends(person);
        return ResponseEntity.ok("Person with friends created");
    }

    @PostMapping("/relate")
    public ResponseEntity<String> relateExistingPersons(@RequestParam String personName,
                                                        @RequestParam String friendName,
                                                        @RequestParam int since) {
        personService.createRelationshipBetweenExistingNodes(personName, friendName, since);
        return ResponseEntity.ok("Relationship created");
    }

}
