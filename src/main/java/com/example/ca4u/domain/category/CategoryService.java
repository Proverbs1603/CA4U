package com.example.ca4u.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public List<CategoryFilterResponseDto> getAllFiltersCategory(){
        //변수 리스트 선언
        List<CategoryFilterResponseDto> categoryFilterResponseDtos = new LinkedList<>();

        //대분류 다 불러오기
        List<CategoryDto> categoryDtos = getFirstCategories();

        //대분류 이름 담으면서 대분류에 해당하는 중분류 불러와서 담기
        for(CategoryDto categoryDto : categoryDtos){
            //카테고리 필터 선언(대분류 개수만큼 필요함)
            CategoryFilterResponseDto categoryFilterResponseDto = new CategoryFilterResponseDto();
            //카테고리 대분류 집어넣기
            categoryFilterResponseDto.setFstCategoryFilter(categoryDto.getCategoryNm());

            //카테고리 대분류ID에 따른 중분류 불러오기
            List<CategoryDto> secCategoryList = getSecondCategories(categoryDto.getId());

            //카테고리 중분류 집어넣기 (List<CategoryDto>형)
            categoryFilterResponseDto.setSecCategoryFilter(secCategoryList);
        }


        return categoryFilterResponseDtos;
    }

}
