package hyeseo.boot.repository;

import hyeseo.boot.domain.Address;

import java.util.List;

public interface JTAddressRepository {
    List<Address> list();
    boolean insert(Address address);
    boolean delete(long seq);

}