package spring.webflex.SpringWebflexMongoDB.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.webflex.SpringWebflexMongoDB.model.Account;
@Repository
public interface AccountCrudRepository extends ReactiveCrudRepository<Account,String> {
    Flux<Account> findAllByValue(String value);
    Mono<Account> findFirstByOwner(Mono<String> owner);
}
