package com.example.ca4u.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public List<CategoryDto> getFirstCategories() {
        List<Category> fstCategoryList = categoryRepository.findOnlyFstCategories();
        List<CategoryDto> fstCategoryDtoList = fstCategoryList.stream()
                .map(CategoryDto::of)
                .toList();
        return fstCategoryDtoList;
    }

    public List<CategoryDto> getSecondCategories(Long fstCategoryId) {
        List<Category> categoryList = categoryRepository.findAllSecCategoryByFstId(fstCategoryId);

        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(CategoryDto::of)
                .toList();
        return categoryDtoList;
    }
}
