package shubh.springframework.spring6reactive.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shubh.springframework.spring6reactive.domain.Beer;
import shubh.springframework.spring6reactive.domain.Customer;
import shubh.springframework.spring6reactive.repositories.BeerRepository;
import shubh.springframework.spring6reactive.repositories.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();

        beerRepository.count().subscribe(count -> {
            System.out.println("Beer Count is " + count);
        });

        customerRepository.count().subscribe(count -> {
            System.out.println("customer count is " + count);
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                customerRepository.save(Customer.builder()
                        .customerName("nanu")
                        .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                        .customerName("ronit")
                        .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                        .customerName("shubh")
                        .build())
                        .subscribe();
            }
        });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                beerRepository.save(beer1).subscribe(); // subscribe puts back pressure
                // on without back pressure nothing happens
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }
}
