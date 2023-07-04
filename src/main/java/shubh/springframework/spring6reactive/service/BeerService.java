package shubh.springframework.spring6reactive.service;

import reactor.core.publisher.Flux;
import shubh.springframework.spring6reactive.model.BeerDTO;

public interface BeerService {
    Flux<BeerDTO> listBeers();
}
