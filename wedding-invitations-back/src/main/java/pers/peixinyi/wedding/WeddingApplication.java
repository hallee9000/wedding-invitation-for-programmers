package pers.peixinyi.wedding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.peixinyi.wedding.service.WeddingService;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-20 16:21
 */
@SpringBootApplication
public class WeddingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeddingApplication.class);
    }
}
