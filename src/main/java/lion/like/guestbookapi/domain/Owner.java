package lion.like.guestbookapi.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Owner {
    public static final int MAX_NAME_LENGTH = 10;
    private final String id;
    private final LocalDateTime createdAt;

    // Owner 생성자
    public Owner(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();

        validateName(id);
    }

    // 예외 처리
    private void validateName(String id){
        // ownerId의 길이가 길 경우
        if (id.length() > MAX_NAME_LENGTH){
            throw new IllegalArgumentException("더 짧은 이름을 사용해주세요.");
        }
    }
}
