package com.pada.sandbox.keyvalue;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
}
