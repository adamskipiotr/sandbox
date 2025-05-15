package com.pada.sandbox.widecolumn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class CyclistCategoryService {

    private final CyclistCategoryRepository cyclistCategoryRepository;

    public CyclistCategoryService(CyclistCategoryRepository cyclistCategoryRepository) {
        this.cyclistCategoryRepository = cyclistCategoryRepository;
    }

    public void createCyclistCategory() {
        CyclistCategory cyclistCategory = CyclistCategory.builder()
                .category("Category")
                .points(11)
                .lastname("lastname")
                .build();
        cyclistCategoryRepository.save(cyclistCategory);
    }

    public List<CyclistCategory> getAllCyclistCategories() {
        return cyclistCategoryRepository.findAll();
    }

}
