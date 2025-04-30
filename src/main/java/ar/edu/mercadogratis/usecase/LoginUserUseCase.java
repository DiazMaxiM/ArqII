package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.User;

public interface LoginUserUseCase {
    Boolean login(User user);
}
