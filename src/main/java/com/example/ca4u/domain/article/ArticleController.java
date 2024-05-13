package com.example.ca4u.domain.article;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.domain.article.dto.ArticleDetailDto;
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

    @Operation(summary = "전체 게시글 불러오기", description = "홈 화면에서 전체 게시글을 불러오는 API", parameters = {
            @Parameter(name = "keyword", description = "검색어", in = ParameterIn.QUERY, required = false)
    })
    @GetMapping("/articles")
    public ApiResponse<List<ArticleDto>> getArticles(@RequestParam(name = "keyword", required = false)String keyword){
        return ApiResponse.ok(articleService.getAllArticles(keyword), "게시글 조회 성공");
    }

    @Operation(summary = "게시글 클릭했을 때 정보 불러오기", description = "게시글 디테일 불러오는 API", parameters = {
            @Parameter(name = "articleId", description = "게시글 아이디", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/articles/{articleId}")
    public ApiResponse<ArticleDetailDto> getArticlesDetails(@PathVariable Long articleId){
        return ApiResponse.ok(articleService.getArticlesDetails(articleId), "게시글 디테일 조회 성공");
    }


    @Operation(summary = "게시글 즐겨찾기 버튼", description = "게시글 즐겨찾기 버튼 API", parameters = {
            @Parameter(name = "articleId", description = "게시글 아이디", in = ParameterIn.PATH, required = true)
    })
    @PatchMapping("/articles/{articleId}/likes")
    public ApiResponse<String> likeArticle(@PathVariable Long articleId) {

        articleService.likeArticle(articleId);
        return ApiResponse.ok("좋아요 성공");
    }

    @Operation(summary = "즐겨찾기한 게시글들 불러오기", description = "즐겨찾기 해놓은 게시글들을 불러오는 API")
    @GetMapping("/articles/likes")
    public ApiResponse<List<ArticleLikeDto>> getArticlesLike(){
        return ApiResponse.ok(articleService.getArticlesLike(), "즐겨찾기 게시글 조회 성공");
    }
}
