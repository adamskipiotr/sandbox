package com.pada.sandbox.widecolumn;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
  basePackages = "com.pada.sandbox.widecolumn")
public abstract class CassandraConfig extends AbstractCassandraConfiguration {
    //
}