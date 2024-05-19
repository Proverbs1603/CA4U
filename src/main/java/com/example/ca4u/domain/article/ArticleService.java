package com.example.ca4u.domain.article;

import com.example.ca4u.domain.article.dto.ArticleDetailDto;
import com.example.ca4u.domain.article.dto.ArticleDto;
import com.example.ca4u.domain.article.dto.ArticleFilterRequestDto;
import com.example.ca4u.domain.article.dto.ArticleLikeDto;
import com.example.ca4u.domain.articleHashtag.ArticleHashtagRepository;
import com.example.ca4u.domain.hashtag.Hashtag;
import com.example.ca4u.domain.hashtag.HashtagDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleHashtagRepository articleHashtagRepository;
    @Transactional
    public void likeArticle(Long articleId) {
        //좋아요 +1
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId));
        article.likeArticle();
        articleRepository.save(article);
    }

    public List<ArticleLikeDto> getArticlesLike(){
        return articleRepository.findByLike().stream().map(ArticleLikeDto::of).toList();
    }

    //개선필요
    public List<ArticleDto> getAllArticles(String keyword, String type){//검색키워드, //게시글 타입(동아리 or 학회)
        //둘 다 없으면 전체 return
        if(keyword == null && type == null){
            return articleRepository.findAll().stream().map(ArticleDto::of).toList();
        } else if (keyword != null && type == null) {
            //전체에서 검색
            return articleRepository.findWithKeyword(keyword).stream().map(ArticleDto::of).toList();
        } else if (keyword == null && type != null) {
            //동아리 or 학회 선택할 때
            //1. 동아리 C
            if(type.equals("C")){
                return articleRepository.findAll().stream().filter(article -> article.getArticleType().equals("C")).map(ArticleDto::of).toList();
            } else if (type.equals("A")) {
                //2. 학회 A
                return articleRepository.findAll().stream().filter(article -> article.getArticleType().equals("A")).map(ArticleDto::of).toList();
            }else {
                //이상한 값 들어오면 에러
                new EntityNotFoundException("해당 type은 없습니다");
            }
        } else if (keyword != null && type != null) {
            //학회나 동아리에서 검색할 때
            if(type.equals("C")){
                return articleRepository.findWithKeyword(keyword).stream().filter(article -> article.getArticleType().equals("C")).map(ArticleDto::of).toList();
            } else if (type.equals("A")) {
                return articleRepository.findWithKeyword(keyword).stream().filter(article -> article.getArticleType().equals("A")).map(ArticleDto::of).toList();
            }else {
                //이상한 값 들어오면 에러
                new EntityNotFoundException("해당 type은 없습니다");
            }
        }
        

        return articleRepository.findAll().stream().map(ArticleDto::of).toList();
    }

    public ArticleDetailDto getArticlesDetails(Long articleId){
        //게시글 안에 해쉬태그들 불러오기
       List<HashtagDto> hashtagDtoList = articleHashtagRepository.findByArticleId(articleId).stream().map(HashtagDto::of).toList();
       //게시글 정보 불러오기
       ArticleDetailDto articleDetailDto = ArticleDetailDto.of(articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId)));
       //게시글 detail Dto에 해쉬태그 정보 넣어주기
       articleDetailDto.setHashtagDtoList(hashtagDtoList);
       return articleDetailDto;
    }

    public List<ArticleDto> getArticlesByHashtags(Long hashtagId) {
        List<Long> ids = new ArrayList<>();

        if (hashtagId == 303) { // 날파람(26) - 대회(303)
            ids = Arrays.asList(30L, 29L, 68L, 69L, 32L, 56L, 28L, 88L, 71L, 76L, 27L, 55L, 75L, 74L, 10L, 5L, 25L);
        } else if (hashtagId == 309) { // 날파람(26) - 박람회(309)
            ids = Arrays.asList(49L, 55L, 74L);
        } else if (hashtagId == 308) { // 날파람(26) - 부스
            ids = Arrays.asList(29L, 49L, 33L, 62L);
        } else if (hashtagId == 306) { // 날파람(26) - 선수
            ids = Arrays.asList(30L, 72L, 70L, 71L);
        } else if (hashtagId == 305) { // 날파람(26) - 심사
            ids = Arrays.asList(15L);
        } else if (hashtagId == 301) { // 날파람(26) - 운동
            ids = Arrays.asList(30L, 29L, 85L, 76L);
        } else if (hashtagId == 236) { // 인액터스 - 개발
            ids = Arrays.asList(12L, 3L, 60L, 61L, 9L);
        } else if (hashtagId == 234) { // 인액터스 - 경험
            ids = Arrays.asList(12L, 62L);
        } else if (hashtagId == 235) { // 인액터스 - 교류
            ids = Arrays.asList(68L, 29L, 55L, 32L, 76L, 69L, 28L, 70L, 62L);
        } else if (hashtagId == 233) { // 인액터스 - 기업
            ids = Arrays.asList(13L, 8L, 21L, 10L);
        } else if (hashtagId == 238) { // 인액터스 - 발표
            ids = Arrays.asList(8L, 3L, 23L, 11L, 17L, 46L, 4L);
        } else if (hashtagId == 231) { // 인액터스 - 프로젝트
            ids = Arrays.asList(13L, 12L, 7L, 60L, 14L, 59L, 61L);
        } else {
            return new ArrayList<>();
        }

        return articleRepository.findAllById(ids).stream()
                .map(ArticleDto::of)
                .toList();
    }

    public List<ArticleDto> getPersonalArticle(String articleIds){
        //articleIds에 따라서 쿼리를 다르게 가져가기 (쿼리 한 3개 생성하면 되지 않을까)
        if(articleIds.equals("2632497")){
            return articleRepository.findPersonalArticles_1().stream().map(ArticleDto::of).toList();
        } else if (articleIds.equals("263249713")) {
            return articleRepository.findPersonalArticles_2().stream().map(ArticleDto::of).toList();
        }
        return new ArrayList<ArticleDto>();
    }

    public List<ArticleDto> getNewArticles(){
        return articleRepository.findNewArticles().stream().map(ArticleDto::of).toList();
    }

    public List<ArticleDto> getFilteringArticles(ArticleFilterRequestDto articleFilterRequestDto){
        List<Article> articles = articleRepository.findAll();

        // 관심분과 필터링
        List<String> departmentList = articleFilterRequestDto.getDepartmentList();
        if (departmentList != null && !departmentList.isEmpty()) {
            articles = articles.stream()
                    .filter(article -> departmentList.contains(article.getDepartment()))
                    .toList();
        }

        // 활동요일 필터링
        List<String> actDayList = articleFilterRequestDto.getActDayList();
        if (actDayList != null && !actDayList.isEmpty()) {
            articles = articles.stream()
                    .filter(article -> actDayList.contains(article.getActDay()))
                    .toList();
        }

        // 활동시간 필터링
        List<String> actHourList = articleFilterRequestDto.getActHourList();
        if (actHourList != null && !actHourList.isEmpty()) {
            articles = articles.stream()
                    .filter(article -> actHourList.contains(article.getActHour()))
                    .toList();
        }

        // 회비 필터링
        Integer cost = articleFilterRequestDto.getCost();
        if (cost != null) {
            articles = articles.stream()
                    .filter(article -> article.getCost() <= cost)
                    .toList();
        }

//        // 지원방법 필터링
//        List<String> applyFormList = articleFilterRequestDto.getApplyFormList();
//        if (applyFormList != null && !applyFormList.isEmpty()) {
//            articles = articles.stream()
//                    .filter(article -> applyFormList.contains(article.getApplyForm()))
//                    .toList();
//        }

        // Article을 ArticleDto로 매핑하여 반환
        return articles.stream()
                .map(ArticleDto::of)
                .toList();
    }
}
