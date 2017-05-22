package com.enel.tinamica.api.models.casasegura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by user on 3/05/17.
 */

@EnableDiscoveryClient

@SpringBootApplication
@ComponentScan("com.enel.tinamica.api.models.casasegura")
@EnableAutoConfiguration
@Configuration

public class Application {
    public static void main (String[]args){SpringApplication.run(Application.class, args);}

}

/** End of class **/

