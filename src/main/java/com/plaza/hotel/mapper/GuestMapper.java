package com.plaza.hotel.mapper;

import com.plaza.hotel.dto.GuestDTO;
import com.plaza.hotel.entity.Guest;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public GuestMapper() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());
    }

    public Guest map(final GuestDTO guestDTO, final Guest guest) {
        this.modelMapper.map(guestDTO, guest);
        return guest;
    }
}
