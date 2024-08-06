package com.example.ca4u.domain.article;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글", description = "게시글 즐겨찾기 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class ArticleController {
    private final ArticleService articleService;


}
