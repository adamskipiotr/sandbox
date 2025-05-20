package com.pada.sandbox.keyvalue;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@RedisHash("Item")
public class Item implements Serializable {
    @Id
    private String id;
    private String name;
    private String subname;

    // Constructors, getters, setters
}
