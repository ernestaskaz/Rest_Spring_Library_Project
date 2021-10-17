package LibraryProject.librarydemo;

import LibraryProject.librarydemo.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
		//(exclude = {DataSourceAutoConfiguration.class})
public class LibraryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDemoApplication.class, args);

	}

}
