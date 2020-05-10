package com.boot.hibernate.trying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// @ComponentScan({ "com.boot.hibernate.trying" })

// Spring Boot mặc định sẽ tự động cấu hình JPA, và tạo ra các Spring BEAN liên quan tới JPA
// Mục đích trong ứng dụng này chúng ta sẽ sử dụng Hibernate, vì vậy chúng ta cần vô hiệu hóa 
// các cấu hình tự động nói trên của Spring Boot
@EnableAutoConfiguration(exclude = { 
	DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class, 
    HibernateJpaAutoConfiguration.class 
})
public class HibernateTryingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateTryingApplication.class, args);
	}

}

