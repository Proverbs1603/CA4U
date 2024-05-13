package com.example.ca4u.domain.category;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFilterResponseDto {
    private String fstCategoryFilter;
    private List<CategoryDto> secCategoryFilter;
}
