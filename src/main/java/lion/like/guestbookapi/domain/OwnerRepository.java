package lion.like.guestbookapi.domain;

public interface OwnerRepository {

    Owner save(Owner owner);
    Owner findByOwnerId(String ownerId);
}
