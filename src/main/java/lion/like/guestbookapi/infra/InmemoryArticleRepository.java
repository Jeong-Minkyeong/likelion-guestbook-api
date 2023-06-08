package lion.like.guestbookapi.infra;

import lion.like.guestbookapi.domain.Article;
import lion.like.guestbookapi.domain.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InmemoryArticleRepository implements ArticleRepository {

    private Map<Long, Article> store = new HashMap<>();
    private Long sequence = 0L;

    // 특정 유저 방명록 생성
    @Override
    public Article save(Article article){
        article.setId(++sequence);
        store.put(article.getId(), article);
        return article;
    }

    // 특정 유저 방명록 목록 조회
    @Override
    public List<Article> findAllByOwnerId(String ownerId){
        return store.values()
                .stream()
                .filter(article -> article.getOwner().getId().equals(ownerId))
                .toList();
    }

    // 특정 id 방명록 글 정보 조회
    @Override
    public Article findByArticleId(Long id){
        return store.get(id);
    }

    // 특정 id 방명록 글 수정
    @Override
    public Article updateArticle(Article article){
        return store.put(article.getId(), article);
    }

    // 특정 id 방명록 글 삭제
    @Override
    public Article deleteById(Long id){
        return store.remove(id);
    }
}
