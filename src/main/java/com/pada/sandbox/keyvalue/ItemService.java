package com.pada.sandbox.keyvalue;

import com.pada.sandbox.document.Product;
import com.pada.sandbox.document.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        var aaa =  itemRepository.findAll();
        aaa.forEach(items::add);
        return items;
    }
}
