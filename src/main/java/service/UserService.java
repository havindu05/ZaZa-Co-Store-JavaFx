package service;

import model.dto.UserDTO;

public interface UserService {
    UserDTO login(String email,String password);
}
