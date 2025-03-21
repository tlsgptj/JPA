package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import java.util.List;

public interface AddressAjaxService {
    List<Address> listS();
    Address insertS(Address address);
    void deleteS(long seq);

    //for Ajax
    Address getBySeqS(long seq);
    List<Address>getListByNames(String name);

}
