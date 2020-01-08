package spring.webflex.SpringWebflexMongoDB.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.webflex.SpringWebflexMongoDB.model.Account;

public interface AccountReactiveRepository extends ReactiveMongoRepository<Account, String> {

    public Mono<Account> findAccountByOwner(String owner);
}
