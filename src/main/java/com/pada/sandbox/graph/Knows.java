package com.pada.sandbox.graph;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Knows {

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Person friend;

    private int since;
}
