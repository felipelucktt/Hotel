package com.plaza.hotel.mapper;

import com.plaza.hotel.dto.AccommodationDTO;
import com.plaza.hotel.entity.Accommodation;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public AccommodationMapper() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());
    }

    public Accommodation map(final AccommodationDTO accommodationDTO, final Accommodation accommodation) {
        this.modelMapper.map(accommodationDTO, accommodation);
        return accommodation;
    }
}
