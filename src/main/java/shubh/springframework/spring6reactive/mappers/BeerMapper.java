package shubh.springframework.spring6reactive.mappers;

import org.mapstruct.Mapper;
import shubh.springframework.spring6reactive.domain.Beer;
import shubh.springframework.spring6reactive.model.BeerDTO;

@Mapper
public interface BeerMapper {
    Beer beertoDTObeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
