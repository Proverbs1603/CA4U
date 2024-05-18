package com.example.ca4u.domain.article;

import com.example.ca4u.apiResponse.ApiResponse;
import com.example.ca4u.domain.article.dto.ArticleDetailDto;
import com.example.ca4u.domain.article.dto.ArticleDto;
import com.example.ca4u.domain.article.dto.ArticleFilterRequestDto;
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

    @Operation(summary = "전체 동아리/학회 불러오기", description = "홈 화면에서 전체 게시글을 불러오는 API(쿼리스트링으로 검색 가능)", parameters = {
            @Parameter(name = "keyword", description = "검색어", in = ParameterIn.QUERY, required = false),
            @Parameter(name = "type", description = "동아리 또는 학회", in = ParameterIn.QUERY, required = false)
    })
    @GetMapping("/articles")
    public ApiResponse<List<ArticleDto>> getArticles(@RequestParam(name = "keyword", required = false)String keyword,
                                                     @RequestParam(name = "type", required = false)String type){
        return ApiResponse.ok(articleService.getAllArticles(keyword, type), "게시글 조회 성공");
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

    @Operation(summary = "해쉬태그에 속한 게시글들 불러오기", description = "게시글 안에서 키워드를 눌렀을 때 뜨는 추천 게시글들 불러오는 API", parameters = {
            @Parameter(name = "hashtagId", description = "해쉬태그(키워드) 아이디", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/articles/hashtags/{hashtagId}")
    public ApiResponse<List<ArticleDto>> getArticlesByKeyword(@PathVariable Long hashtagId){
        return ApiResponse.ok(articleService.getArticlesByHashtags(hashtagId), "키워드 연관 게시글 조회 성공");
    }

    @Operation(summary = "개인맞춤형 추천 학회/동아리 API", description = "즐겨찾기 해놓은 동아리/학회 기반으로 추천게시글 목록 조회하는 API", parameters = {
            @Parameter(name = "articleIds", description = "즐겨찾기한 게시글 아이디들을 단순 문자열로 더해서 쿼리스트링으로 전해주세요", in = ParameterIn.QUERY, required = false)
    })
    @GetMapping("/articles/personal")
    public ApiResponse<List<ArticleDto>> getPersonalArticles(@RequestParam(name = "articleIds", required = true)String articleIds){
        //우선은
        return ApiResponse.ok(articleService.getPersonalArticle(articleIds), "개인맞춤형 동아리/학회 조회 성공");
    }

//    @Operation(summary = "필터링 옵션 선택 후 학회/동아리 조회 API", description = "여러 필터링을 선택한 후 학회/동아리를 조회하는 API", parameters = {
//            @Parameter(name = "filterOptions", description = "관심분과(List), 활동요일(List), 활동시간(List), 회비(Integer), 지원방법(List) 등")
//    })
//    @GetMapping("/articles/filters")
//    public ApiResponse<List<ArticleDto>> getFilteringArticles(@ResponseBody ArticleFilterRequestDto articleFilterRequestDto){
//
//    }

}
