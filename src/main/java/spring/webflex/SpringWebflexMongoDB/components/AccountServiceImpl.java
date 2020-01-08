package spring.webflex.SpringWebflexMongoDB.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import spring.webflex.SpringWebflexMongoDB.Services.AccountService;
import spring.webflex.SpringWebflexMongoDB.model.Account;
import spring.webflex.SpringWebflexMongoDB.model.AccountEvent;
import spring.webflex.SpringWebflexMongoDB.repo.AccountCrudRepository;
import spring.webflex.SpringWebflexMongoDB.repo.AccountReactiveRepository;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountCrudRepository crudRepository;
    @Autowired
    AccountReactiveRepository accountReactiveRepository;



    @Override
    public Mono<Account> createAccount(Account account) {
        return accountReactiveRepository.save(account);
    }

    @Override
    public Mono<Account> updateAccount(Account account) {
        return accountReactiveRepository.save(account);
    }

    @Override
    public void deleteAccount(String id) {
        accountReactiveRepository.deleteAll();

    }

    @Override
    public Flux<AccountEvent> findAllAccounts() {
        return accountReactiveRepository.findAll().flatMap(account -> {
            Flux<AccountEvent> accountFlux=Flux.fromStream(
                    Stream.generate(()->{/*System.out.println("Account:"+account);*/
                        return new AccountEvent(account.getId(),account.getOwner(),account.getValue(), new Date());})
            );
            return Flux.zip(Flux.interval(Duration.ofSeconds(10)),accountFlux).map(Tuple2::getT2);

        });
    }

    @Override
    public Mono<Account> getByOwner(String owner) {
        return accountReactiveRepository.findAccountByOwner(owner);
    }

}
