package co.edu.escuelaing.cvds.lab7;

import co.edu.escuelaing.cvds.lab7.config.CorsConfig;
import co.edu.escuelaing.cvds.lab7.model.Configuration;
import co.edu.escuelaing.cvds.lab7.model.Product;
import co.edu.escuelaing.cvds.lab7.model.User;
import co.edu.escuelaing.cvds.lab7.model.UserRole;
import co.edu.escuelaing.cvds.lab7.repository.UserRepository;
import co.edu.escuelaing.cvds.lab7.service.ConfigurationService;
import co.edu.escuelaing.cvds.lab7.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
@Import(CorsConfig.class)
public class Lab7Application {
	private final ConfigurationService configurationService;

	private final UserRepository userRepository;

	private final ProductService productService;

	@Autowired
	public Lab7Application(
			ConfigurationService configurationService,
			UserRepository userRepository,
			ProductService productService
	) {
		this.configurationService = configurationService;
		this.userRepository = userRepository;
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			log.info("Adding Employees....");
			productService.addProduct(new Product(1, "Papa pastusa", "Papa", Product.Category.Food, 5, 2000, 100));

			log.info("Adding Configurations....");
			configurationService.addConfiguration(new Configuration("premio", "810000"));
			configurationService.addConfiguration(new Configuration("descuento", "0.1"));
			configurationService.addConfiguration(new Configuration("app-name", "Miraculous: Las Aventuras de Ladybug"));

			log.info("\nGetting all configurations....");
			configurationService.getAllConfigurations().forEach(configuration -> System.out.println(configuration));

			log.info("\nAdding admin@site.org user with Password: admin");
			userRepository.save(new User("admin@site.org", "admin", Arrays.asList(UserRole.ADMINISTRADOR, UserRole.CLIENTE)));
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}