package lion.like.guestbookapi.domain;

import java.util.List;

public interface ArticleRepository {

    Article save(Article article);

    List<Article> findAllByOwnerId(String ownerId);

    Article findByArticleId(Long id);

    Article updateArticle(Article article);

    Article deleteById(Long id);
}
