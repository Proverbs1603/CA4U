package com.example.ca4u.domain.category;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
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

    public List<CategoryFilterResponseDto> getAllFiltersCategory(String type){
        //변수 리스트 선언
        List<CategoryFilterResponseDto> categoryFilterResponseDtos = new LinkedList<>();

        //대분류 다 불러오기
        List<CategoryDto> categoryDtos = categoryRepository.findOnlyFstCategories().stream().map(CategoryDto::of).toList();
        
        //대분류 이름 담으면서 대분류에 해당하는 중분류 불러와서 담기
        for(CategoryDto categoryDto : categoryDtos){

            //카테고리 필터 선언(대분류 개수만큼 필요함)
            CategoryFilterResponseDto categoryFilterResponseDto = new CategoryFilterResponseDto();

            String categoryNm = categoryDto.getCategoryNm();

            //학회는 관심분과 넣지않음.
            if(type != null && type.equals("A") && categoryNm.equals("관심분과")){
                continue;
            }

            //카테고리 대분류 집어넣기
            categoryFilterResponseDto.setFstCategoryFilter(categoryNm);

            //카테고리 대분류ID에 따른 중분류 불러오기
            List<CategoryDto> secCategoryList = categoryRepository.findAllSecCategoryByFstId(categoryDto.getId()).stream().map(CategoryDto::of).toList();

            if(type != null && type.equals("C") && categoryNm.equals("관심분과")){
                secCategoryList = secCategoryList.stream().filter(s -> !s.getCategoryNm().equals("학술탐구분과")).toList();
            }

            //카테고리 중분류 집어넣기 (List<CategoryDto>형)
            categoryFilterResponseDto.setSecCategoryFilter(secCategoryList);

            categoryFilterResponseDtos.add(categoryFilterResponseDto);
        }

        return categoryFilterResponseDtos;
    }

}
