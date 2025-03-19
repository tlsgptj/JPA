package hyeseo.boot.repository;

import hyeseo.boot.domain.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> list();
    Address insert(Address address);
    boolean delete(long seq);

}
