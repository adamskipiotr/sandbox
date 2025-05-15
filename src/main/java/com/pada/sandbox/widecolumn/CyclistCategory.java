package com.pada.sandbox.widecolumn;

import lombok.*;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "cyclist_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CyclistCategory {

   @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
   private String category;

   @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
   private int points;
   private UUID id;
   private String lastname;
}
