package spring.webflex.SpringWebflexMongoDB;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import spring.webflex.SpringWebflexMongoDB.Services.AccountService;
import spring.webflex.SpringWebflexMongoDB.model.Account;
import spring.webflex.SpringWebflexMongoDB.model.AccountEvent;
import spring.webflex.SpringWebflexMongoDB.repo.AccountReactiveRepository;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping(value = "/rest/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/getAccounts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountEvent> getAccountById() {
        return accountService.findAllAccounts();

    }

    @GetMapping("/getAccounts/v1/{owner}")
    public Mono<Account> getAccountByOwner(@PathVariable("owner") String owner) {
        return accountService.getByOwner(owner);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/createAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }


    @PutMapping(value = "/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Account> updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = "/deleteAccount/{id}")
    public void deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(id);
    }
    /*@GetMapping(value = "/{id}/events" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountEvent> getEvents(@PathVariable("id") String id){

        return  accountReactiveRepository.findById(id)
                .flatMapMany(account -> {
                   Flux<AccountEvent> accountFlux=Flux.fromStream(
                           Stream.generate(()->{System.out.println("Account:"+account);
                           return new AccountEvent(account.getId(),account.getOwner(),account.getValue(), new Date());})
                   );
                   return Flux.zip(Flux.interval(Duration.ofSeconds(10)),accountFlux).map(Tuple2::getT2);
                });
    }*/
}
