package com.example.ca4u.domain.category;

import com.example.ca4u.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ApiResponse.ok(categoryService.getSecondCategories(fstCategoryId));
    }

    @Operation(summary = "대분류 목록 조회", description = "대분류 목록 조회 API")
    @GetMapping("/first-categories")
    public ApiResponse<List<CategoryDto>> getFirstCategories() {
        return ApiResponse.ok(categoryService.getFirstCategories());
    }
}
