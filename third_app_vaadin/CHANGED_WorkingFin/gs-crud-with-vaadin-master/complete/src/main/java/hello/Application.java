package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Vlad", "Kuz","4","10","50"));
			repository.save(new Customer("Vasya", "Abricov","3","10","150"));
			repository.save(new Customer("Madha", "Buzinov","5","10","150"));
			repository.save(new Customer("Dasha", "Globina","11","50","250"));
			repository.save(new Customer("Vlas", "Epimova","2","10","150"));
			repository.save(new Customer("Mixa", "Lev","55","61","1050"));
			repository.save(new Customer("Andrey", "Zynin","6","10","350"));
			repository.save(new Customer("Olya", "Repona","4","10","450"));
			repository.save(new Customer("Danya", "Jenkins","8","10","850"));
			repository.save(new Customer("Katya", "Nopina","22","30","650"));
			repository.save(new Customer("Katerina", "Nopalova","5","10","550"));
			repository.save(new Customer("Vladlen", "Nomirov","4","10","850"));




			///*************************************************************************
			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Customer bauer : repository
//					.findByLastNameStartsWithIgnoreCase("Bauer")) {
//				log.info(bauer.toString());
//			}
//			log.info("");
		};
	}

}
