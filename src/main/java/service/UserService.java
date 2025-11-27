package service;

import model.dto.UserDTO;

public interface UserService {
    UserDTO login(String name, String email, String password);
    boolean register(UserDTO user);
}
