package spring.webflex.SpringWebflexMongoDB;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import spring.webflex.SpringWebflexMongoDB.model.Account;
import spring.webflex.SpringWebflexMongoDB.repo.AccountReactiveRepository;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringWebfluxMongoDbApplication extends AbstractReactiveMongoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxMongoDbApplication.class, args);
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "Reactive";
    }
    @Bean
    public MongoClient mongoClient(){
        return reactiveMongoClient();
    }

    @Bean
    CommandLineRunner accounts(AccountReactiveRepository accountReactiveRepository){

        return args -> {
            accountReactiveRepository.deleteAll().subscribe(null,null,()->{
                Stream.of(new  Account("5e131bf570f3f51b490632ab","venkatesh",10.00),
                          new  Account("5e131bf570f3f51b490632ac","vijju",11.00),
                          new  Account("5e131bf570f3f51b490632ad","swet",12.00)).forEach(
                                  account->{accountReactiveRepository.save(account).subscribe(System.out::println);}
                );
            });
        };
    }
}
