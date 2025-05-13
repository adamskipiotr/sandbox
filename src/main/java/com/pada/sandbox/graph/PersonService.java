package com.pada.sandbox.graph;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    public Person findWithFriendsSince(FindPersonWithSinceRequest findPersonWithSinceRequest) {
        return personRepository.findWithFriendsSince(findPersonWithSinceRequest.getName(), findPersonWithSinceRequest.getSince());
    }

    @Transactional
    public void createPersonWithFriends(Person person) {
        for (Knows knows : person.getFriends()) {
            Person friend = knows.getFriend();
            if (friend.getId() == null) {
                friend = personRepository.save(friend);
            }
            knows.setFriend(friend);
        }
        personRepository.save(person);
    }

    @Transactional
    public void createRelationshipBetweenExistingNodes(String personName, String friendName, int since) {
        personRepository.createKnowsRelationship(personName, friendName, since);
    }
}
