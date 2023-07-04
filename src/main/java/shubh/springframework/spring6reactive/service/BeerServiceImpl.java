package shubh.springframework.spring6reactive.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import shubh.springframework.spring6reactive.mappers.BeerMapper;
import shubh.springframework.spring6reactive.model.BeerDTO;
import shubh.springframework.spring6reactive.repositories.BeerRepository;

@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;
    private BeerMapper beerMapper;
    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }
}
