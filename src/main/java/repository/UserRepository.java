package repository;

import model.dto.UserDTO;

public interface UserRepository {
    UserDTO findPerson(String name, String email, String password);
    boolean insertUser(UserDTO user);
}