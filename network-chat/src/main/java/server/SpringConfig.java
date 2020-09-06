package server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import server.auth.AuthService;
import server.auth.AuthServiceJdbcImpl;
import server.persistance.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Value("${database.driver.class}")
    private String driverClassName;

    @Value("${database.userName}")
    private String userName;

    @Value("${database.password}")
    private String password;

    @Value("${database.url}")
    private String databaseUrl;

    @Bean
    public ChatServer chatServer(){
        return new ChatServer();
    }
    @Bean
    public AuthService authService(UserRepository userRepository){
        return new AuthServiceJdbcImpl(userRepository);
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setUrl(databaseUrl);
    return ds;
    }
}
