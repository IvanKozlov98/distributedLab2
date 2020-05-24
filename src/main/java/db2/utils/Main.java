package db2.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class Main
{

    public static void main( String[] args )
    {
        SpringApplication.run( Main.class, args );
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor()
    {
        return new MethodValidationPostProcessor();
    }

}
