package shubh.springframework.spring6reactive.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO) {
        return beerRepository.save(beerMapper.beerDTOtobeer(beerDTO))
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    // update properties
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());

                    return foundBeer;
                    // The flatMap operation "flattens" the nested streams, merging them into a single stream.
                }).flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }
}
