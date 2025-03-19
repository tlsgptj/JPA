package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import java.util.List;

public interface AddressService {
    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);
}
