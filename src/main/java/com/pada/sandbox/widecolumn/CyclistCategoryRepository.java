package com.pada.sandbox.widecolumn;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;

public interface CyclistCategoryRepository extends MapIdCassandraRepository<CyclistCategory> {
}
