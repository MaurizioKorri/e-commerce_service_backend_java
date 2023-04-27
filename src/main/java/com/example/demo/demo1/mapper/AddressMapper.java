package com.example.demo.demo1.mapper;

import com.example.demo.demo1.dto.AddressDto;
import com.example.demo.demo1.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    public AddressDto toDto(Address source);
    public Address toEntity(AddressDto destination);
}
