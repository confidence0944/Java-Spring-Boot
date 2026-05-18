package training.web.mapper;

import org.mapstruct.Mapper;

import training.web.dto.CurrencyRequestDto;
import training.web.dto.CurrencyResponseDto;
import training.web.entity.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyResponseDto toDto(Currency entity);

    Currency toEntity(CurrencyRequestDto dto);
}
