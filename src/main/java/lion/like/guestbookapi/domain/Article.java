package lion.like.guestbookapi.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Article {
    public static final int MAX_TITLE_LENGTH = 20;   // 숫자의 의미가 있는 경우에는 상수처리 해주는 것이 직관적으로 좋음 => 모든 원시값과 문자열을 포장한다.
    private Long id;
    private Owner owner;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;

    // Article 생성자
    public Article(Long id, Owner owner, String title, String body) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.body = body;
        this.createdAt = LocalDateTime.now();

        // 예외처리 함수를 생성자에 넣어서 객체가 생성될 때 검증할 수 있도록
        validateTitle(title);
        validateBody(body);
    }

    // 예외 처리
    private void validateTitle(String title) {
        // title이 비어있을 때
        if (title.isBlank()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        // title이 MAX_LENGTH를 넣을 경우
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("글자수 범위를 초과하였습니다.(글자수 20이하)");
        }
    }

    private void validateBody(String body) {
        // body가 비었을 경우
        if (title.isBlank()) {
            throw new IllegalArgumentException("본문이 비어있습니다.");
        }
    }
}


