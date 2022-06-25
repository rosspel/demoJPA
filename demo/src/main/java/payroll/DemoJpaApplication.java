package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class DemoJpaApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(DemoJpaApplication.class, args);
	}

}
