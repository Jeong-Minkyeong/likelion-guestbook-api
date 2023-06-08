package lion.like.guestbookapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 3.0으로 넘어오면서 @EnableSwagger2 는 사용하지 않아도 됨
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)  // Swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면 true
                .select()                                 // select() 메소드는 ApiSelectorBuilder 클래스의 인스턴스를 반환함. 해당 인스턴스를 통해 Swagger의 end-point를 제어할 수 있음
                .apis(RequestHandlerSelectors.basePackage("lion.like.guestbookapi.controller")) // controller가 들어있는 패키지
                .paths(PathSelectors.any())               // 위 패키지 안의 api 중 지정된 path만 보여줌. (any()로 설정 시 모든 api가 보여짐)
                .build();
    }

    // ApiInfoBuilder()를 통해 API에 대한 정보를 설정할 수 있음. Swagger 상단에 표시될 제목, 설명, 버전, 작성자 정보 등을 표시함.
}