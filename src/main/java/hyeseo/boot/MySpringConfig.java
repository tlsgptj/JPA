package hyeseo.boot;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import hyeseo.boot.mapper.AddressMapper;
import hyeseo.boot.repository.*;
import hyeseo.boot.service.*;

import javax.sql.DataSource;

@Configuration
public class MySpringConfig {
    /*@Autowired
    DataSource dataSource;
    @Bean
    public AddressRepository addressRepository() {
        return new JdbcMariaAddressRepository(dataSource);
    }
    @Bean
    public AddressService addressService() {
        return new JdbcAddressService(addressRepository());
    }*/

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EntityManager entityManager;
    @Bean
    public JTAddressRepository jtAddressRepository() {
        //return new JdbcTemplateMariaAddressRepository(jdbcTemplate); //JdbcTemplate
        return new JpaMariaJTAddressRepository(entityManager); //JPA
    }
    //@Autowired
    AddressMapper addressMapper;
    @Bean
    public JTAddressService jtAddressService(){
        //return new JdbcTemplateAddressService(jtAddressRepository()); //JdbcTemplate
        return new MybatisMariaAddressService(addressMapper); //Mybatis
        //return new JpaMariaJTAddressService(jtAddressRepository()); //JPA
    }
}
