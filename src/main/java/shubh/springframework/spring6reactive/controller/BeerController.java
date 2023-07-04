package shubh.springframework.spring6reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shubh.springframework.spring6reactive.model.BeerDTO;
import shubh.springframework.spring6reactive.service.BeerService;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";
    public static final String  BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> createNewBeer(@RequestBody BeerDTO beerDTO){
       return beerService.saveNewBeer(beerDTO)
               .map(savedDto -> ResponseEntity.created(UriComponentsBuilder
                       .fromHttpUrl("http://localhost:8080" + BEER_PATH
                            + "/" +savedDto.getId())
                       .build().toUri())
                       .build());

    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId)
    {
        return beerService.getBeerById(beerId);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }
}
