package com.pada.sandbox.keyvalue;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Item item) {
        itemService.addItem(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
