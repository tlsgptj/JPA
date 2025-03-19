package hyeseo.boot;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@Configuration
@MapperScan("hyeseo.boot.mapper")  // Mapper가 위치한 패키지 지정
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 모든 mybatis XML 매퍼 파일을 로드 (하위 폴더 포함)
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        // SqlSessionFactory를 이용해 SqlSessionTemplate 생성
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
