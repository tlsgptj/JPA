package hyeseo.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hyeseo.boot.domain.Address;

import java.util.List;

public interface SpringDataJpaMariaAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByName(String name);
    //JPQL: select a from Address a where a.name=:name
    List<Address> findByNameAndAddr(String name, String addr); //And, Or, ..
    List<Address> findByNameContaining(String name); //XXX
}