package ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper;

import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.UserDocument;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User           toDomain(UserDocument document);
    UserDocument   toDocument(User domain);
}
