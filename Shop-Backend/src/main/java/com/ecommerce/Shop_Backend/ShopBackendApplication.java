    package com.ecommerce.Shop_Backend;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.data.web.config.EnableSpringDataWebSupport;

    @SpringBootApplication
    @EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
    public class ShopBackendApplication {

        public static void main(String[] args) {
            SpringApplication.run(ShopBackendApplication.class, args);
        }

    }
