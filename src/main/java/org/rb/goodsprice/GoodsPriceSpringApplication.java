package org.rb.goodsprice;

import org.rb.goodsprice.model.Good;

import org.rb.goodsprice.repositories.IGoodsCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GoodsPriceSpringApplication {
	private static final Logger log= LoggerFactory.getLogger(GoodsPriceSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GoodsPriceSpringApplication.class, args);
	}

	/**/
	@Bean
	public CommandLineRunner demo(IGoodsCrudRepository crudRepo){

		return args -> {
			log.info("Init Some Goods data.....");
			crudRepo.save(new Good(new Date(),"Good_1","Shop_1",12.0,0.0));
			crudRepo.save(new Good(new Date(),"Good_2","Shop_2",13.0,0.0));
			crudRepo.save(new Good(new Date(),"Good_3","Shop_3",14.0,0.0));
                        for(int i=4;i<=10;i++){
                         crudRepo.save(new Good(new Date(),"Good_"+i,"Shop_"+i,1.40,0.0));
                        }

		};
	}
	/**/
}
