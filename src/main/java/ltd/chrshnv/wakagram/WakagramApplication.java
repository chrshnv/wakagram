package ltd.chrshnv.wakagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WakagramApplication {

	static void main(String[] args) {
		SpringApplication.run(WakagramApplication.class, args);
	}

}
