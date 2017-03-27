package api.config;

import api.utils.MessagesConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by nolesuk on 27-Mar-17.
 */
@Configuration
public class AppConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(MessagesConstants.UTF_8);
        filter.setForceEncoding(true);
        return filter;
    }
}
