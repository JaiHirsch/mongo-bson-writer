package org.jpa.mysql;

import org.jpa.mysql.domain.Foo;
import org.jpa.mysql.domain.FooRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MySqlToBsonRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MySqlToBsonRunner.class, args);
		FooRepository repository = context.getBean(FooRepository.class);
		
		Iterable<Foo> customers = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Foo customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
	}

}
