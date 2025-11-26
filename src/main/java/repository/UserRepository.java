package repository;

import model.dto.UserDTO;

public interface UserRepository {
    UserDTO findPerson(String email, String password);
}
