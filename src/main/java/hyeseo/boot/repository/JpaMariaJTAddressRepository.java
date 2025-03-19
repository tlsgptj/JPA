package hyeseo.boot.repository;

import jakarta.persistence.EntityManager;
import hyeseo.boot.domain.Address;

import java.util.List;

//@Repository
public class JpaMariaJTAddressRepository implements JTAddressRepository {
    //@Autowired
    private final EntityManager entityManager;
    //@Autowired
    public JpaMariaJTAddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Address> list() {
        List<Address> list = entityManager.createQuery(
                "SELECT a FROM Address a ORDER BY a.seq DESC", Address.class).getResultList();
        return list;
    }
    @Override
    public boolean insert(Address address) { //insert, update, delete 기능은 jpql이 필요없음
        entityManager.persist(address);
        return true;
    }
    @Override
    public boolean delete(long seq) { //그냥 interface에 void delete(long seq)할 껄
        entityManager.remove(entityManager.find(Address.class, seq));
        return true;
    }
}