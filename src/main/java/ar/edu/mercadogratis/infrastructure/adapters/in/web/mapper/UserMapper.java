package ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper;

import ar.edu.mercadogratis.infrastructure.adapters.in.web.dto.UserDTO;
import ar.edu.mercadogratis.domain.model.User;

public class UserMapper {

    private UserMapper() {
        // Constructor privado para evitar instanciación
    }

    public static User toDomain(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
