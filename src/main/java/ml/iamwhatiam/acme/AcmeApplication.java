package ml.iamwhatiam.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application
 * @author iMinusMinus
 * @date 2019-10-13
 */
@SpringBootApplication(scanBasePackages = {"ml.iamwhatiam.acme"})
public class AcmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmeApplication.class, args);
    }
}
