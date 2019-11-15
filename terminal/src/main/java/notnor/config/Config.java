package notnor.config;

import lombok.extern.slf4j.Slf4j;
import notnor.Main;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class Config implements ApplicationRunner {


  public static void main(String... args)
  {
    SpringApplication.run(Config.class, args);
  }

  @Override
  public void run(ApplicationArguments args)  {

    Main.main(args.getSourceArgs());

  }
}
