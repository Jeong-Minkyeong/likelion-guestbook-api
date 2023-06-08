package lion.like.guestbookapi.dto;


import lion.like.guestbookapi.domain.Article;
import lion.like.guestbookapi.domain.Owner;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private Long id;
    private String ownerId;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.ownerId = article.getOwner().getId();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.createdAt = article.getCreatedAt();
    }

}
