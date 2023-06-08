package lion.like.guestbookapi.infra;

import lion.like.guestbookapi.domain.Owner;
import lion.like.guestbookapi.domain.OwnerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InmemoryOwnerRepository implements OwnerRepository {

    private Map<String, Owner> store = new HashMap<>();

    @Override
    public Owner save(Owner owner){
        store.put(owner.getId(), owner);
        return owner;
    }

    @Override
    public Owner findByOwnerId(String ownerId){
        return store.get(ownerId);
    }

}
