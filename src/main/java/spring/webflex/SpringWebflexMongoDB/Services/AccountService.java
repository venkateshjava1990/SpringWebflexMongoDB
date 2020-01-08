package spring.webflex.SpringWebflexMongoDB.Services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.webflex.SpringWebflexMongoDB.model.Account;
import spring.webflex.SpringWebflexMongoDB.model.AccountEvent;

@Service
public interface AccountService {
    public Mono<Account> createAccount(Account account);
    public Mono<Account> updateAccount(Account account);
    public void deleteAccount(String id);
    public Flux<AccountEvent> findAllAccounts();
    public Mono<Account> getByOwner(String owner);
}
