package LibraryProject.librarydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		//(exclude = {DataSourceAutoConfiguration.class})
public class LibraryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDemoApplication.class, args);

	}

}
