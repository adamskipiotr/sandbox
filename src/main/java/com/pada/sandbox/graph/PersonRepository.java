package com.pada.sandbox.graph;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findByName(String string);

    @Query("MATCH (p:Person)-[:KNOWS]->(friend:Person) WHERE p.name = $name RETURN friend")
    List<Person>  findFriendsByName(@Param("name") String name);

    @Query("""
        MATCH (p:Person {name: $name})-[r:KNOWS]->(f:Person)
        WHERE r.since >= $year
        RETURN p, collect(r), collect(f)
    """)
    Person findWithFriendsSince(String name, int year);


    @Query("""
        MATCH (p:Person {name: $personName})
        MATCH (f:Person {name: $friendName})
        MERGE (p)-[:KNOWS {since: $since}]->(f)
        """)
    void createKnowsRelationship(@Param("personName") String personName,
                                 @Param("friendName") String friendName,
                                 @Param("since") int since);
}