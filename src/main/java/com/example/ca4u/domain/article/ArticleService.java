package com.example.ca4u.domain.article;

import com.example.ca4u.domain.article.dto.ArticleDetailDto;
import com.example.ca4u.domain.article.dto.ArticleDto;
import com.example.ca4u.domain.article.dto.ArticleLikeDto;
import com.example.ca4u.domain.articleHashtag.ArticleHashtagRepository;
import com.example.ca4u.domain.hashtag.Hashtag;
import com.example.ca4u.domain.hashtag.HashtagDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ArticleHashtagRepository articleHashtagRepository;
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
       List<HashtagDto> hashtagDtoList = articleHashtagRepository.findByArticleId(articleId);
       //게시글 정보 불러오기
       ArticleDetailDto articleDetailDto = ArticleDetailDto.of(articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다. id=" + articleId)));
       //게시글 detail Dto에 해쉬태그 정보 넣어주기
       articleDetailDto.setHashtagDtoList(hashtagDtoList);
       return articleDetailDto;
    }

    public List<ArticleDto> getArticlesByHashtags(Long hashtagId){
        return articleHashtagRepository.findArticlesByHashtagId(hashtagId).stream().map(ArticleDto::of).toList();
    }

    public List<ArticleDto> getPersonalArticle(String articleIds){
        //articleIds에 따라서 쿼리를 다르게 가져가기 (쿼리 한 3개 생성하면 되지 않을까)
        if(articleIds.equals("123")){
            return articleRepository.findPersonalArticles_1().stream().map(ArticleDto::of).toList();
        } else if (articleIds.equals("456")) {
            return articleRepository.findPersonalArticles_2().stream().map(ArticleDto::of).toList();
        } else if (articleIds.equals("789")) {
            return articleRepository.findPersonalArticles_3().stream().map(ArticleDto::of).toList();
        }
        return articleRepository.findPersonalArticles_1().stream().map(ArticleDto::of).toList();
    }
}
