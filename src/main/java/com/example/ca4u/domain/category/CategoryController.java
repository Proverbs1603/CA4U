package com.example.ca4u.domain.category;

import com.example.ca4u.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "카테고리", description = "카테고리 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "중분류 목록 조회", description = "대분류 아이디에 해당하는 중분류 목록 조회 API", parameters = {
            @Parameter(name = "fstCategoryId", description = "대분류 아이디", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/{fstCategoryId}/second-categories")
    public ApiResponse<List<CategoryDto>> getSecondCategories(@PathVariable Long fstCategoryId) {
        return ApiResponse.ok(categoryService.getSecondCategories(fstCategoryId), "대분류에 따른 중분류 목록 조회 성공");
    }

    @Operation(summary = "대분류 목록 조회", description = "대분류 목록 조회 API")
    @GetMapping("/first-categories")
    public ApiResponse<List<CategoryDto>> getFirstCategories() {
        return ApiResponse.ok(categoryService.getFirstCategories(), "대분류 목록 조회 성공");
    }

    @Operation(summary = "필터링 목록 조회", description = "필터링 목록 한방 조회 API", parameters = {
            @Parameter(name = "type", description = "동아리(C) 또는 학회(A)", in = ParameterIn.QUERY, required = false)
    })
    @GetMapping("/filters")
    public ApiResponse<List<CategoryFilterResponseDto>> getFilters(@RequestParam(name = "type", required = false) String type) {
        return ApiResponse.ok(categoryService.getAllFiltersCategory(type), "필터링 전체 목록 조회 성공");
    }
}
