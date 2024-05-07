package com.example.ca4u.domain.article;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.domain.article.dto.ArticleDto;
import com.example.ca4u.domain.article.dto.ArticleLikeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글", description = "게시글 즐겨찾기 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class ArticleController {
    private final ArticleService articleService;

    @Operation(summary = "전체 게시글 불러오기", description = "홈 화면에서 전체 게시글을 불러오는 API")
    @GetMapping("/articles")
    public ApiResponse<List<ArticleDto>> getAllArticles(){
        return ApiResponse.ok(articleService.getAllArticles());
    }

    @Operation(summary = "게시글 즐겨찾기 버튼", description = "게시글 즐겨찾기 버튼 API", parameters = {
            @Parameter(name = "articleId", description = "게시글 아이디", in = ParameterIn.PATH, required = true)
    })
    @PatchMapping("/articles/{articleId}/likes")
    public ApiResponse<String> likeArticle(@PathVariable Long articleId) {

        articleService.likeArticle(articleId);
        return ApiResponse.ok("좋아요 성공");
    }

    @Operation(summary = "즐겨찾기한 게시글들", description = "즐겨찾기 해놓은 게시글들을 불러오는 API")
    @GetMapping("/articles/likes")
    public ApiResponse<List<ArticleLikeDto>> getArticlesLike(){
        return ApiResponse.ok(articleService.getArticlesLike());
    }
}
