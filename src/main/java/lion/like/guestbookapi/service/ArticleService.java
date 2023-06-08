package lion.like.guestbookapi.service;

import lion.like.guestbookapi.domain.Article;
import lion.like.guestbookapi.domain.ArticleRepository;
import lion.like.guestbookapi.domain.OwnerRepository;
import lion.like.guestbookapi.domain.Owner;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final OwnerRepository ownerRepository;

    public ArticleService(ArticleRepository articleRepository, OwnerRepository ownerRepository) {
        this.articleRepository = articleRepository;
        this.ownerRepository = ownerRepository;
    }

    public Article createArticle(String ownerId, String title, String body) {
        /**
         * 1. ownerId에 해당하는 owner 가 이미 생성되어 있으면? =>  그 owner 꺼내서 그대로 사용
         * 2. 없으면? => 생성해서 저장하고 사용
         */
        Owner responseOwner;
        Owner foundOwner = ownerRepository.findByOwnerId(ownerId);
        if (foundOwner == null) {
            responseOwner = new Owner(ownerId);
            ownerRepository.save(responseOwner);
        } else {
            responseOwner = foundOwner;
        }

        Article article = new Article(null, responseOwner, title, body);
        return articleRepository.save(article);
    }

    public List<Article> findAllByOwnerId(String ownerId){
        return articleRepository.findAllByOwnerId(ownerId);
    }

    public Article findByArticleId(Long id){
        Article foundArticle = articleRepository.findByArticleId(id);
        if (foundArticle == null){
            throw new NoSuchElementException("해당 방명록이 존재하지 않습니다.");
        }
        return foundArticle;
    }

    public Article updateArticle(Long id, String title, String body){

        // 아티클 id로 아티클 객체를 찾는다
        Article targetArticle = findByArticleId(id);

        // 찾아온 아티클 객체의 오너 id를 뽑는다
        String targetOwnerId = targetArticle.getOwner().getId();

        // 뽑아온 오너 id의 오너 객체를 뽑는다
        Owner owner = ownerRepository.findByOwnerId(targetOwnerId);

        // 오너 객체를 새로 생성한 아티클 객체의 파라미터로 넣어준다
        Article article = new Article(id, owner, title, body);
        return articleRepository.updateArticle(article);
    }

    public void deleteById(Long id) {
        Article deletedArticle = articleRepository.deleteById(id);
        if (deletedArticle == null) {
            throw new NoSuchElementException("해당 방명록이 존재하지 않습니다.");
        }
    }
}
