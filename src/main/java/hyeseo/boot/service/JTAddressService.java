package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface JTAddressService {
    List<Address> listS();
    boolean insertS(Address address);
    boolean deleteS(long seq);
}