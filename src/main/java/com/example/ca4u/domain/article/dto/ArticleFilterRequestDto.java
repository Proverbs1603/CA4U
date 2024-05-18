package com.example.ca4u.domain.article.dto;


import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleFilterRequestDto {
    //관심분과
    private List<String> departmentList;
    //활동요일
    private List<String> actDayList;
    //활동시간
    private List<String> actHourList;
    //회비
    private Integer cost;
    //지원방법
    private List<String> applyFormList;
}
