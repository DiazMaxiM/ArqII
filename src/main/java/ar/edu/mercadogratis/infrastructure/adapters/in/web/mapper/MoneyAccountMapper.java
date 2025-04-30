package ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper;

import ar.edu.mercadogratis.domain.model.MoneyAccount;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.MoneyAccountDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyAccountMapper {
    MoneyAccount toDomain(MoneyAccountDocument doc);
    MoneyAccountDocument toDocument(MoneyAccount domain);
}