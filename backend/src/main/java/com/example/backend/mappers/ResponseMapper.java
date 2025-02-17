package com.example.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.backend.dtos.ResponseDto;
import com.example.backend.utils.ResponseGen;

@Mapper
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    ResponseGen responseDtotoResMapper(ResponseDto responseDto);
    ResponseDto responseToResDtoMapper (ResponseGen response);
}
