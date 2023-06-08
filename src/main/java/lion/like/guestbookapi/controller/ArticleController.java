package lion.like.guestbookapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lion.like.guestbookapi.domain.Article;
import lion.like.guestbookapi.dto.ArticleRequest;
import lion.like.guestbookapi.dto.ArticleResponse;
import lion.like.guestbookapi.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "ArticleController")
@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary="특정 유저 방명록 생성", description="특정 유저의 방명록을 생성합니다.")
    @ApiResponse(code = 200, message="ok")
    @PostMapping("/{ownerId}/articles")
    public ArticleResponse save(@PathVariable String ownerId, @RequestBody ArticleRequest request) {
        String title = request.getTitle();
        String body = request.getBody();

        Article article = articleService.createArticle(ownerId, title, body);
        return new ArticleResponse(article);
    }

    @Operation(summary="특정 유저 방명록 목록 조회", description="특정 유저가 작성한 방명록 목록을 조회합니다.")
    @GetMapping("/{ownerId}/articles")
    public List<ArticleResponse> findAllByOwnerId(@PathVariable String ownerId) {
        return articleService.findAllByOwnerId(ownerId)
                .stream()
                .map(ArticleResponse::new)
                .toList();
    }

    @Operation(summary="특정 id 방명록 글 정보 조회", description="특정 방명록을 조회합니다.")
    @GetMapping("/articles/{id}")
    public ArticleResponse findByArticleId(@PathVariable Long id){
        Article article = articleService.findByArticleId(id);
        return new ArticleResponse(article);
    }

    @Operation(summary="특정 id 방명록 글 수정", description="특정 방명록을 수정합니다.")
    @PutMapping("/articles/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request){
        String title = request.getTitle();
        String body = request.getBody();
        Article article = articleService.updateArticle(id, title, body);
        return new ArticleResponse(article);
    }

    @Operation(summary="특정 id 방명록 글 삭제", description="특정 방명록을 삭제합니다.")
    @DeleteMapping("/articles/{id}")
    public void deleteById(@PathVariable Long id){
        articleService.deleteById(id);
    }
}