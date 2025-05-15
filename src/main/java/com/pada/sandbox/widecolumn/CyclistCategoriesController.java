package com.pada.sandbox.widecolumn;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cyclist-categories")
@RequiredArgsConstructor
public class CyclistCategoriesController {

    private final CyclistCategoryService cyclistCategoryService;


    @PostMapping
    public ResponseEntity<CyclistCategory> createCyclistCategory() {
        cyclistCategoryService.createCyclistCategory();
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<CyclistCategory>> getAllCyclistCategories() {
        List<CyclistCategory> categories = cyclistCategoryService.getAllCyclistCategories();
        return ResponseEntity.ok(categories);
    }

}
