package lion.like.guestbookapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 서버의 모든 API를 모든 도메인에서 접속할 수 있도록 허용   /* = /hello (o) /hello/world (x) /** = /hello (o) /hello/world (o)
        registry.addMapping("/**")
                .allowedMethods("*")                        // access-control-allow-methods: * -> 모든 http 메서드를 허용
                .allowedOrigins("*");                       // access-control-allow-origins: * -> 모든 도메인
    }
}