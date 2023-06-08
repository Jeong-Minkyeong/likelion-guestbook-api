package lion.like.guestbookapi.dto;

import lion.like.guestbookapi.domain.Article;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String body;
}
